# Assignment 3
Igor Torres, Robert Metzinger
--- 07.10.2022

## Class de.dominik_geyer.jtimesched.project.ProjectTableModel.isCellEditable

### Why this function was selected

This function was chosen because it has clear rules.

### What is the purpose of this function

It returns if a cell of the project table is editable. 
It depends on the row, which identifies the project, and the column.

### Boundary Value Analysis

The first param (row) selects the project in the table.
The selected project influences the result depending on if it is running or not.

That way, we have 3 partitions:
- no project;
- project is running; 
- project is not running.

The second param (column) can also have 4 cases:
- 0 is never editable;
- 1, 2, 3 and 4 are always editable;
- 5 and 6 are editable only if the project is not running;
- Any other column is never editable.

We can analise the boundaries of column values and identify "on points" and "off points": 
- 0 is the first boundary
  - on point: 0
  - off point: 1
- 4 is the next boundary
  - on point: 4
  - off point: 5
- 6 is the next boundary
  - on point: 6
  - off point: 7

### Unit tests

We must test every combination of column points with each project partition.
Then we can create 3 sets of parametrized tests for each project partition and test each one of the 6 points.

### Outcome

- No project
  - Should throw an error for all columns
- Project is running
  - 0: false
  - 1: true
  - 4: true
  - 5: false
  - 6: false
  - 7: false
- Project is not running
  - 0: false
  - 1: true
  - 4: true
  - 5: true
  - 6: true
  - 7: false

All tests passed.