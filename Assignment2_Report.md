# Assignment 2
Igor Torres, Robert Metzinger
--- 07.10.2022

# class de.dominik_geyer.jtimesched.project.Project

This class has a lot of getter and setter methods.
We did not choose them, because they are not that important
and do not really have functionality, but only refer to a class variable.
So, instead we chose the methods, that have a bit more substance.
We run a setUp method before each unit test to provide a fresh Project instance for each test.

## boolean isRunning():
Indicates if the project is running, which means it was
started and the timer is counting.

### Category-Partition Algorithm
The method has no input parameters, so the algorithm can not
be applied to it.

### Unit test 'isRunningTrue':
The project instance is being started.
Then the `isRunning` method is called to get the project's running state.
The assertion is that the method returns a `true` value.
The test passes successfully.

### Unit test: 'isRunningFalse':
The `isRunning` method is being called.
We assert that it returns `false`, since the projects was not started.
It passes successfully.

## void toggle():
Starts a project that is not running and pauses a running project.

### Category-Partition Algorithm
The method has no input parameters, so the algorithm can not
be applied to it.

### Unit test 'toggleStarted':
We start the project instance.
By calling the `isRunning` method and asserting the value `true` we assure that the project is running.
Then we call the `toggle` method.
Now, we assert that the `isRunning` method returns `false`, which it does, so the test passes.

### Unit test 'toggleNotStarted':
By calling the `isRunning` method and asserting the value `false` we assure that the project is not running.
Then we call the `toggle` method.
Now, we assert that the `isRunning` method returns `true`, which it does, so the test passes.

# class de.dominik_geyer.jtimesched.project.ProjectTime
This class provides some functionalities for formatting and parsing dates or times.
It is a static class and is therefore
independent from other components which makes its methods well suitable for unit tests.
Also, they require input parameters, for which we can apply the Category-Partition algorithm.
We tested different combinations of input parameters,
to cover usual cases with expected and unexpected parameters as well as edge cases.

## String formatSeconds(int):
This method formats a number of seconds into the String format *H:mm:ss*.
It is used to display the time a project is running in a well readable way.

### Category-Partition Algorithm
1. The input value is a primitive integer `seconds` that represents the number of seconds the project is running.
2. The int `seconds` should be a number greater or equal than 0. 
It also must not be `null`, but that's prevented by Java because a primitive `int` can not be `null`.
3. Since there is only one characteristic (the int `seconds`), the only invalid "combination" is testing a negative value.
4. Combinations / Values we tested:
- negative number of seconds (-1)
- 0 seconds
- 1 second
- 60 seconds = exactly 1 minute
- 3600 seconds = exactly 1 hour
- 5025 seconds = some random normal case

### Unit test 'formatSecondsWithNegative':
Here we are testing the method with negative value and expect it throw an exception.
For this, we use `assertThrows` in which we call the method through a lambda expression.
As input we pass the value -1.
We found that the `formatSeconds` method is not implemented to prevent negative values being passed.
So, it does not throw an exception and the test fails.
In our opinion this is a fault in the implementation.

### Unit test 'formatSecondsWithPositive':
We used the `@ParametrizedTest` in combination with `@CsvSource` to test the method with multiple inputs.
We chose the values to test a random normal case as well as edge cases like 0 seconds and exactly 1 second / minute / hour.
For each value the test passes successfully.

## int parseSeconds(String):
This method is the counterpart to the `formatSeconds`.
It calculates the number of seconds from a formatted time String.
The usage of this is probably to make the time Strings comparable, so that the application can calculate the differences between to project runtimes.

### Category-Partition Algorithm
1. The method has one input parameter `strTime` which is a String that represents a project runtime.
2. The `strTime` must be in the format *H:mm:ss*
3. Only one input parameter. We test it with a String that is not in the required format. And also with a time string that represents a negative number of hours.
4. Combinations / Values we tested (equivalent to the tests of `formatSeconds`):
- invalid String ("xyz")
- 'negative' time string "-1:00:00"
- "0:00:00"
- "0:00:01"
- "0:01:00"
- "1:00:00"
- "1:23:45"

### Unit test parseSecondsWithInvalidString:
We used the `assertThrows` construct again to test the `parseSeconds` method with an invalid value,
which is a String that does not follow the format *H:mm:ss*.
The method throws a `ParseException` as expected.
The test is successful.

### Unit test parseSecondsWithNegative:
Again we used `assertThrow` to test the method in a way we expect it to throw an exception.
This time we use a String, which includes a negative number of hours "-1:00:00".
The method throws the expected ParseException and the test passes.

### Unit test parseSecondsWithPositive:
Here we used the `@ParametrizedTest` and `@CsvSource` again to test the method with multiple inputs.
For every input the method returns the expected value so every test run passes.

## String formatDate(Date):
This method brings a date into a readable formatted String.
It is used for displaying the date in the UI.

### Category-Partition Algorithm
1. The method has one input parameter Date `d`.
2. `d` can be any possible Date value.
3. Only one input parameter. We test it with a normal Date and with `null`.
4. Combinations / Values we tested (equivalent to the tests of `formatSeconds`):
- `null`
- Date of the deadline of this assignment

### Unit test formatDate:
We create the specific Date by passing the year, month and day value in the Date constructor.
The method returns the expected String "2022-10-07" and the test passes.

### Unit test formatDateNull:
Once again to test an expected exception we use `assertThrows` and calling the method by a lambda expression.
The invalid value in this case is `null`.
As expected the method throws a `NullPointerException`, so the test passes.