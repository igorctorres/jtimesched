# Assignment #4

Igor Torres, Robert Metzinger
--- 04.11.2022

## Use case #1: Sort projects by title

### Why this use case was selected

This use case was selected because it has three clear states: unsorted, sorted ascending, sorted descending.

Please note that every state and transition refers to the order of the projects regarding only the project title. 

### What is the purpose of this function

By clicking on the column header 'title' the projects get sorted in alphanumeric order.
The possible orders are 
- unsorted: no order
- ascending: lower numbers before higher numbers before lower letters before higher letters
- descending: higher letters before lower letters before higher numbers before lower numbers

### pre-conditions
Create four projects: 1st Project, Project B, 2nd Project, Project A in this order.
The projects are initially not sorted by the title, but in the order of creation.
We call this state **unsorted**.

### State machine

![use case 1 state machine](/assets/assignment4-assets/use-case-1_sorting_state-machine.svg)

The initial state is **unsorted**.
It means the projects are not sorted in any order regarding the project title. 
Example:
1. Project A
2. 2nd Project
3. Project B
4. 1st Project

By pressing the column header 'title' while in state **unsorted**, the projects become **sorted ascending** by the project title.
Example:
1. 1st Project
2. 2nd Project
3. Project A
4. Project B

The title column is now marked with an up facing arrow to show that the projects are sorted ascending.

By pressing any other column header than 'title' the projects become **unsorted** again.

By pressing the column header 'title' while in state **sorted ascending**, the projects become **sorted descending** by the project title.
Example:
1. Project B
2. Project A
3. 2nd Project
4. 1st Project

The title column is now marked with a down facing arrow to show that the projects are sorted descending.

By pressing the column header while in state **sorted descending**, the project become sorted ascending again.

By pressing any other column header than 'title' the projects become **unsorted** again.

### Transition tree
![use case 1 transition tree](/assets/assignment4-assets/use-case-1_sorting_transition_tree.svg)

From the initial state **unsorted** we can only transition into the state **sorted ascending**.
From there we can transition back to **unsorted** or **sorted descending**.
We already know the behaviour in state **unsorted**, do we don't have to replicate transitions from here.
From **sorted descending** we can also transition back to **unsorted** or back to **sorted ascending**.
Both of them are already in the transition tree, so we can stop here.
This means we have 3 leaves in the transition tree: unsorted_1, unsorted_2 and sorted_ascending_1.
So, in conclusion we have to write 3 test cases, one for each leaf.

### Transition table
| States/Events          | sort ascending   | sort descending   | unsort   |
|------------------------|------------------|-------------------|----------|
| **unsorted**           | sorted ascending |                   | unsorted |
| **sorted ascending**   |                  | sorted descending | unsorted |
 | **sorted descending**  | sorted ascending |                   | unsorted |

The transition table shows the 6 possible transitions described above. 
There are 3 empty cells represent the possible sneak paths.

### Description of QF test cases
We developed 3 test cases for this use case according to the transition tree.\
For each test case we created a setup sequence, that opens the application and creates the 4 test projects (see pre-conditions).\
For this application we could not create a cleanup sequence, because the application does not close properly when closing the window.\
We always have to close the application manually in the task manager.

1. test case: unsorted - sorted ascending - unsorted
   - Steps: Click on 'title' column -> Click on 'created' column
   - Assert this project order: 1st project, Project B, 2nd Project, Project A
   - What we see: projects being sorted ascending, projects being sorted by creation (=unsorted)
   - Outcome: No errors, 100% test-cases passed


2. test case: unsorted - sorted ascending - sorted descending - unsorted
   - Steps: Click on 'title' column -> Click on 'title' column -> Click on 'created' column
   - Assert this project order: 1st project, Project B, 2nd Project, Project A
   - What we see: projects being sorted ascending, projects being sorted descending, projects being sorted by creation (=unsorted)
   - Outcome: No errors, 100% test-cases passed


3. test case: unsorted - sorted ascending - sorted descending - sorted ascending
   - Steps: Click on 'title' column -> Click on 'title' column -> Click on 'title' column
   - Assert this project order: 1st project, 2nd Project, Project A, Project B
   - What we see: projects being sorted ascending, projects being sorted descending, projects being sorted ascending
   - Outcome: No errors, 100% test-cases passed

## Use case #2: Project time tracking

### Why this use case was selected

This use case was selected because it is the main purpose of the application.

### What is the purpose of this function

The user can keep track of the time he works on a project.

### pre-conditions
none

### State machine

![use case 2 state machine](/assets/assignment4-assets/use-case-2_time-tracking_state-machine.svg)

The initial state is **no project**. It means the project is not in the list.

The button 'Add project' creates a new list entry for the project. It is now in the state **idle**.
The project has initially no time on the clock.

From this state the user can press the start button. This transitions the project into the **running** state.
The project clock now counts up.

The user can also press the delete button, which leads back to the **no project** state.

From the **running** state the user has the option to pause the project by pressing the button and transition it into the **idle** state again.
This stops the project clock.

Also, while **running** the user can delete the project and get back to **no project** state.

### Transition tree
![use case 1 transition tree](/assets/assignment4-assets/use-case-2_time-tracking_transition_tree.svg)

From the initial state **no project** we can only transition into the state **idle**.
From there we can transition back to **no project** or **running**.
We already know the behaviour in state **no project**, do we don't have to replicate transitions from here.
From **running** we can also transition back to **idle** or back to **no project**.
Both of them are already in the transition tree, so we can stop here.
This means we have 3 leaves in the transition tree: no_project_1, no_project_2 and idle_1.
So, in conclusion we have to write 3 test cases, one for each leaf.

### Transition table
| States/Events  | create | start   | pause | delete     |
|----------------|--------|---------|-------|------------|
| **no project** | idle   |         |       |            |
| **idle**       |        | running |       | no project | 
| **running**    |        |         | idle  | no project |

The transition table shows the 5 possible transitions described above.
There are 7 empty cells represent the possible sneak paths.

### Description of QF test cases
We developed 3 test cases for this use case according to the transition tree.\
For each test case we created a setup sequence, that opens the application.\
For this application we could not create a cleanup sequence, because the application does not close properly when closing the window.\
We always have to close the application manually in the task manager.

1. test case: no project - idle - no project
   - Steps: Click on 'Add project' button -> Double-click on 'delete' button
   - Assert no project entry in the list
   - What we see: the projects list is empty, a new projects is being created, the projects list is empty again
   - Outcome: No errors, 100% test-cases passed


2. test case: no project - idle - running - no project
   - Steps: Click on 'Add project' button -> Click on 'start' button (optional: wait for a few seconds to see if the project is really running) -> Double-click on 'delete' button
   - Assert no project entry in the list
   - What we see: the projects list is empty, a new project is being created, the clock of the project counts up, the projects list is empty again
   - Outcome: No errors, 100% test-cases passed


3. test case: no project - idle - running - idle
   - Steps: Click on 'Add project' button -> Click on 'start' button (optional: wait for a few seconds to see if the project is really running) -> Click on 'pause' button
   - Assert an existing project entry (i.e. assert the time today = the time we waited)
   - What we see: the projects list is empty, a new project is being created, the clock of the project counts up, the clock of the project stops counting
   - Outcome: No errors, 100% test-cases passed

## Use case #3: Daily Quota

### Why this use case was selected
This use case was selected because it is a major purpose of the application and it has 3 clear states. 

### What is the purpose of this function
The user can set a quota for a project to limit the time he spends on this project.
The quota is the available time to work on the project.\
The time and the quota can both be updated manually. 
The time can also be updated automatically by running the project, but the behaviour is the same as updating the time manually.\
The quota can be set for the time overall or for the time today.

Please note that in this use case we are going to refer only to the daily quota and the time today.

### pre-conditions
Create a new project with no quota set and no time on the clock.

### State machine
![use case 3 state machine](/assets/assignment4-assets/use-case-3_quota_state-machine.svg)

In the UI the states are indicated by a color. We are going to use the colors as names of the states.

There are 3 states:
- **white**: the project has no quota (quota = 0)
- **green**: there is time remaining for the project to work on today (time today < quota)
- **red**: the quota for today is exceeded (time today >= quota)

There are two available actions in each state: updating time and updating quota.
For each state this can trigger a transition, if some conditions are met.

The initial state is **white**.\
By updating the time we can only stay in state **white**, because the quota is still 0.\
By updating the quota we can either transition into **green**, if time is less than quota and the new quota is greater than 0.
Or we can transition into **red**, if time is greater or equal than quota and the new quota is greater than 0.

In state **green** the time is always less than the quota.\
So, if we update the time we can still be in state **green**, if the new time is still less than quota.
But if the new time is greater or equal than quota, we transition into state **red**.\
By updating the quota we can transition into each of the three states, depending on the conditions.
If the user sets the quota to 0, we transition into state **white**.\
We stay in **green**, if the time is still less than the new quota. The quota also must still be greater than 0.\
And we transition into state **red**, if the time is greater or equal than the new quota The quota also must still be greater than 0.

In state **red** the time is greater or equal than the quota.\
By updating the time we can still be in state **red**, if the new time is still greater or equal than the quota.\
If the user manually sets a new time, which is less than the quota, we transition into state **green**.\
If the user updates the quota, we can transition into each of the 3 states again.\
If the new quota is 0, we go back to the initial state **white**.\
If the time is still greater or equal than the new quota, we stay in state **red**. The quota also must still be greater than 0.\
And if the time is less than the new quota set by the user, we transition into state **green**. The quota also must still be greater than 0.

### Transition tree
![use case 3 transition tree](/assets/assignment4-assets/use-case-3_quota_transition-tree.svg)
The root of the transition tree is the initial state white_0.

From there we can transition into each of the three states white_1, green_0 and red_0.\
Node white_1 is just the same as the root white_0, so we don't have to exercise further from here.

From node green_0, we have 5 possible transitions. Back to white, 2 transitions into green and 2 transitions into red.
This is because we have the two actions update time and update quota, which both can trigger the same transition.
The green_1 and green_2 are the same state as green_0, so we can stop here.\
The red_1 and red_2 are the same as red_0, so we can stop here too.

In node red_0, we also have the same 5 possible transitions due to our two actions update time and update quota.\
Each of the nodes white_3, green_3, green_4, red_3 and red_4 need not to be exercised further, since we already know their behaviours from the previous nodes.

In conclusion we have 11 leave nodes, which result in 11 test cases for this use case.

### Transition table
| States/Events | update time [quota = 0] | update time [time < quota] | update time [time >= quota] | update quota [quota = 0] | update quota [time < quota && quota > 0] | update quota [time >= quota && quota > 0] |
|---------------|-------------------------|----------------------------|-----------------------------|--------------------------|------------------------------------------|-------------------------------------------|
| **white**     | white                   |                            | white                       | white                    | green                                    | red                                       |
| **green**     |                         | green                      | red                         | white                    | green                                    | red                                       |
| **red**       |                         | green                      | red                         | white                    | green                                    | red                                       |

### Description of QF test cases
We developed 11 test cases for this use case according to the transition tree.\
For each test case we created a setup sequence, that opens the application and creates a new project with no time today and no quota.\
For this application we could not create a cleanup sequence, because the application does not close properly when closing the window.\
We always have to close the application manually in the task manager.

1. test case: white - white
   - Steps: Update time today to 0:00:01
   - Assert time today cell has white indicator (image check).
   - What we see: the cell indicator is always white.
   - Outcome: No errors, 100% test-cases passed


2. test case: white - green - white
   - Steps: Update time today to 0:00:01 -> update quota to 0:00:05 - update quota to 0:00:00
   - Assert time today cell has white indicator (image check).
   - What we see: the cell indicator changes from white to green and back to white.
   - Outcome: No errors, 100% test-cases passed


3. test case: white - green - green (1)
   - Steps: Update time today to 0:00:01 -> update quota to 0:00:05 -> update time to 0:00:02
   - Assert time today cell has green indicator (image check).
   - What we see: the cell indicator changes from white to green and stays green.
   - Outcome: No errors, 100% test-cases passed


4. test case: white - green - green (2)
   - Steps: Update time today to 0:00:01 -> update quota to 0:00:05 -> update quota to 0:00:06
   - Assert time today cell has green indicator (image check).
   - What we see: the cell indicator changes from white to green and stays green.
   - Outcome: No errors, 100% test-cases passed


5. test case: white - green - red (1)
   - Steps: Update time today to 0:00:01 -> update quota to 0:00:05 -> update time to 0:00:06
   - Assert time today cell has red indicator (image check).
   - What we see: the cell indicator changes from white to green and then to red.
   - Outcome: No errors, 100% test-cases passed


6. test case: white - green - red (2)
   - Steps: Update time today to 0:00:04 -> update quota to 0:00:05 -> update quota to 0:00:03
   - Assert time today cell has red indicator (image check).
   - What we see: the cell indicator changes from white to green and then to red.
   - Outcome: No errors, 100% test-cases passed


7. test case: white - red - white
   - Steps: Update time today to 0:00:10 -> update quota to 0:00:05 -> update quota to 0:00:00
   - Assert time today cell has white indicator (image check).
   - What we see: the cell indicator changes from white to red and back to white.
   - Outcome: No errors, 100% test-cases passed


8. test case: white - red - green (1)
   - Steps: Update time today to 0:00:10 -> update quota to 0:00:05 -> update time to 0:00:02
   - Assert time today cell has green indicator (image check).
   - What we see: the cell indicator changes from white to red and then to green.
   - Outcome: No errors, 100% test-cases passed


9. test case: white - red - green (2)
   - Steps: Update time today to 0:00:10 -> update quota to 0:00:05 -> update quota to 0:00:15
   - Assert time today cell has green indicator (image check).
   - What we see: the cell indicator changes from white to red and then to green.
   - Outcome: No errors, 100% test-cases passed


10. test case: white - red - red (1)
    - Steps: Update time today to 0:00:10 -> update quota to 0:00:05 -> update quota to 0:00:06
    - Assert time today cell has red indicator (image check).
    - What we see: the cell indicator changes from white to red stays red.\
    - Outcome: No errors, 100% test-cases passed


11. test case: white - red - red (2)
    - Steps: Update time today to 0:00:15 -> update quota to 0:00:05 -> update quota to 0:00:10
    - Assert time today cell has red indicator (image check).
    - What we see: the cell indicator changes from white to red stays red.
    - Outcome: No errors, 100% test-cases passed

# test

## Sneak paths
1. From use case #1 Sorting we want to test the sneak path in the state **unsorted** and trigger the action *sort descending*.\
This action can only be triggered by clicking the column header 'title'.\
If we do this in the **unsorted** state this action leads always to the **sorted ascending** state.\
So, the application acts in the expected way and the sneak transition is not possible.

- Setup: Create test projects (see [Use case 1](#use-case-1-sort-projects-by-title) pre-conditions)
- Steps: Click on 'title' column
- What we see: projects are unsorted, projects being sorted ascending (!)
- Assert this project order: Project B, Project A, 2nd Project, 1st project\
- Outcome: 4 errors, 0% test-cases passed

2. From use case #2 Time tracking the sneak path we want to test is in state **no project** to trigger the event *start*.\
To trigger the *start* action we need to click the button 'start', but since it is not visible in the state **no project**, it can not be clicked.\
Therefore, the sneak path is not possible to execute in the application.

- Setup: open application
- Steps: Click on 'start' button (The place where it is supposed to be)
- What we see: empty list of projects
- Assert visible project entry in the list
- Outcome: 1 error, 0% test-cases passed

3. In use case #3 from state **white** we try to trigger the action *update time* with the condition [time < quota].\
Since the quota in state **white** is 0 seconds (0:00:00) by definition, we need to update the time with a negative value.\
We try to update the time with the value 0:00:-01. The application prevents this action and just keeps the previous value 0:00:00.\
So, the developer prevented this sneak path to happen by catching this unwanted action.

- Setup: Create new project (see [Use case 3](#use-case-3-daily-quota) pre-conditions)
- Steps: Update time today with value 0:00:-01
- What we see: new project with time 0:00:00, after entering the negative value the project time is still 0:00:00.
- Assert time today cell has green indicator (image check).
- Outcome: 1 error, 0% test-cases passed

## Feedback/opinion about the QF test tool
First of all, the tool provides all the functionalities we needed for developing our test cases.\
After a short period of time in which we explored the functionalities we were able to work with the tool effectively.\
It's not the most intuitive user interface in our opinion.
For example to drag and drop a sequence into different node, this node must be expanded. Otherwise it does not work.\
We also had big problems with the cleanup sequence, because the task of the application is still running in the background after closing the application window.\
But, we are not sure if it's the fault of the QF test tool or of the application itself, but we suspect the latter.\
That being said, we think the biggest advantage of working with this tool is the scope of functionalities.\
For example, we were able to assert the state of a project, which is just a color indicator in the corner of a cell, by using the image check functionality.\
We also really appreciated the copy/paste as well as the drag and drop functionality. This saved us the time of replicating the same steps over and over for different test cases.\
Overall the QF test tool was relatively convenient to work with and fulfilled all our needs for this assignment.\
So, we can give the tool a positive feedback.

# Appendix
The QF test report is located in assets/assignment4-assets.