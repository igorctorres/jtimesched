# The project

The project is a time tracking widget where users can add projects and then start/pause time tracking for each project.

# Source code structure

The code is separated in 2 main packages:

- `project`: defines the model of a project and the project table structure
- `gui`: user interface components using `swing`

Controller logic is placed in GUI components as `JTimeSchedFrame`.

# Static testing

Static testing is a method to find possible faults without executing the code. It provides a quick feedback to the developer about possible faults in early stages of the development.

# Static testing tools

## CheckStyle

CheckStyle is a tool to check code style standards.

Once this tool was added to the project, 1359 errors were reported.

Since CheckStyle only stands for code style standards, the severity property was changed to “warning” instead of “error”.

### Bugs

#### Line has trailing spaces 

This bug occurred 464 times. 
It stands for whitespaces left in the end of lines.
Since there is no simple way to remove all trailing spaces in the project, the `RegexpSingleline` rule was disabled.

#### File contains tab characters

This bug occurs 19 times.
Since the whole project uses tabs for indentation, the rule `FileTabCharacter` was disabled.

#### Parameter ... should be final.

This bug occurred 145 times.
Many developers don't see this rule as important since parameters are always passed by copy. Rule `FinalParameters` were disabled.

#### Line is longer than 80 characters 

This bug occurred 241 times. 
Since so many lines are longer than 80, the rule `LineLength` was modified to accept a maximum of 100 characters, reducing the errors to 74 times.

#### Expression can be simplified

The expression `(e.getFirstChild().getNodeValue().equals("yes")) ? true : false` occurred at lines 153 and 156. 

It can be simplified to `e.getFirstChild().getNodeValue().equals("yes")` since “equals” already returns a boolean.

## SpotBugs

SpotBugs is a static testing tool which checks for more than 400 bug patterns in Java code.

By adding this tool with the default configuration, it reported 32 bugs.

We configured SpotBugs to filter only 4 categories of bugs: `BAD_PRACTICE`, `PERFORMANCE`, `CORRECTNESS` and `MALICIOUS_CODE`. 
The other categories did not apply to this project since it does not have internationalization, it does not use multi-thread, and it is not a data mining project. 
The STYLE category was also removed to avoid duplicated rules with CheckStyle.

### Bugs

#### [BAD_PRACTICE] Exceptional return value of java.io.File.mkdir() ignored in de.dominik_geyer.jtimesched.JTimeSchedApp.main(String[])

The code ignored the fact that `.mkdir()` returns a value.
The code was modified to exit if the program is unable to create the conf directory.
```
if (!dirConf.isDirectory()) {
    if (!dirConf.mkdir()) {
        System.exit(1);
    }
}
```

#### [MALICIOUS_CODE] new de.dominik_geyer.jtimesched.project.ProjectTableModel(ArrayList) may expose internal representation by storing an externally mutable object into ProjectTableModel.arPrj

The constructor was assigning the property directly with the parameter value.
As `ArrayList` is a mutable object, it can be dangerous.
```
public ProjectTableModel(ArrayList<Project> arPrj) {
    this.arPrj = arPrj;
}
```
The code was modified to create a new array list.
```
public ProjectTableModel(ArrayList<Project> arPrj) {
    this.arPrj = new ArrayList<>(arPrj);
}
```

#### [PERFORMANCE] de.dominik_geyer.jtimesched.project.ProjectTableModel.getValueAt(int, int) invokes inefficient new Integer(int) constructor; use Integer.valueOf(int) instead

```
o = new Integer(prj.getSecondsOverall());
```
The code was modified to remove unnecessary boxing.
```
o = prj.getSecondsOverall();
```

#### [PERFORMANCE] de.dominik_geyer.jtimesched.project.ProjectTableModel.getValueAt(int, int) invokes inefficient Boolean constructor; use Boolean.valueOf(...) instead

```
o = (prj.isChecked()) ? new Boolean(true) : new Boolean(false);
```

The code was modified to replace deprecated use of Boolean constructor.

```
o = (prj.isChecked()) ? Boolean.TRUE : Boolean.FALSE;
```
#### ? is null guaranteed to be dereferenced in de.dominik_geyer.jtimesched.gui.table.TimeCellRenderer.getTableCellRendererComponent(JTable, Object, boolean, boolean, int, int) on exception path
```
TimeCellComponent tcc = null;
		
switch (modelColumn) {
case ProjectTableModel.COLUMN_TIMETODAY:
    tcc = new TimeCellComponent(prj.getSecondsToday(), prj.getQuotaToday());
    tcc.setToolTipText(prj.getQuotaToday() > 0 ?
            String.format("Quota today: %s", ProjectTime.formatSeconds(prj.getQuotaToday())) :
            null);
    break;
case ProjectTableModel.COLUMN_TIMEOVERALL:
    tcc = new TimeCellComponent(prj.getSecondsOverall(), prj.getQuotaOverall());
    tcc.setToolTipText(prj.getQuotaOverall() > 0 ?
            String.format("Quota overall: %s", ProjectTime.formatSeconds(prj.getQuotaOverall())) :
            null);
    break;
}
```

The variable `tcc` is instantiated with `null` and its value is assigned in a `switch` block, so it is possible that this variable remains `null` after the `switch` block.
Using the variable can lead to a `NullPointerException`, so a new if condition was added after the switch block to stop the method if `tcc` is still `null`.

```
TimeCellComponent tcc = null;
		
switch (modelColumn) {
  ...
}

if (tcc == null) return null;
```