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

## Class de.dominik_geyer.jtimesched.project.ProjectTime.formatSeconds

### Why this function was selected

This function was chosen because it has clear rules about an integer parameter.

### What is the purpose of this function

It formats a number of seconds in H:MM:SS.

### Boundary Value Analysis

The partitions are related with the format separation.
It separates seconds, minutes and hours.

We can analise the possible numbers of seconds and their partitions:
- Negative: not a valid value
- Zero: all zero
- Greater than zero and lower than 60: only seconds
- Greater than 60 and lower than 3600: minutes and seconds
- Greater than 3600: hours, minutes and seconds

Boundaries and off-points:
- -1 is the first boundary
  - on point: -1
  - off point: 0
- 59 is the third boundary
  - on point: 59
  - off point: 60
- 3599 is the last boundary
  - on point: 3599
  - off point: 3600

### Unit tests

The negative number should throw an error, so we need one test for that case.
As we only have 1 parameter, we can use a single parametrized test with all other points with expected formats.

### Outcome

- -1
  - Should throw an error
- 0
  - 0:00:00
- 59
  - 0:00:59
- 60
  - 0:01:00
- 3599
  - 0:59:59
- 3600
  - 1:00:00

The first case failed because we expect that the method should throw an error, but it didn't.

## Class de.dominik_geyer.jtimesched.project.ProjectTime.parseSeconds

### Why this function was selected

This function was chosen because it has a different parameter type (string), but it still can be categorized because the string is built by integers. 

### What is the purpose of this function

Convert a formatted string (H:MM:SS) to the corresponding number of seconds.

### Boundary Value Analysis

The partitions are based on the format separation: seconds, minutes and hours.

For seconds and minutes, we can have:
- Negative numbers
- Greater than -1 and lower or equal than 59
- Greater than 59
And for hours, we can have:
- Negative numbers
- Greater than zero

Boundaries and off-points:
- Seconds:
  - -1
    - on point: -1
    - off point: 0
  - 59
    - on point: 59
    - off point: 60
- Minutes:
  - -1
    - on point: -1
    - off point: 0
  - 59
    - on point: 59
    - off point: 60
- Hours:
  - -1
    - on point: -1
    - off point: 0

### Unit tests

It is a combination of all possible points of seconds, minutes and hours.
We can have 2 sets of parametrized tests for exceptions: one with negatives values and other with seconds and minutes greater than 59.
And one more parametrized test with valid values.

### Outcome

0:-1:-1 -> Error
0:-1:00 -> Error
0:-1:59 -> Error
0:-1:60 -> Error

0:00:-1 -> Error
0:00:00 -> 0
0:00:59 -> 59
0:00:60 -> Error

0:59:-1 -> Error
0:59:00 -> 3540
0:59:59 -> 3599
0:59:60 -> Error

0:60:-1 -> Error
0:60:00 -> Error
0:60:59 -> Error
0:60:60 -> Error

1:-1:-1 -> Error
1:-1:00 -> Error
1:-1:59 -> Error
1:-1:60 -> Error

1:00:-1 -> Error
1:00:00 -> 3600
1:00:59 -> 3659
1:00:60 -> Error

1:59:-1 -> Error
1:59:00 -> 7140
1:59:59 -> 7199
1:59:60 -> Error

1:60:-1 -> Error
1:60:00 -> Error
1:60:59 -> Error
1:60:60 -> Error

All tests passed.
