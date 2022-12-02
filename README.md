# My Personal Project: Bucket List

## Task 2

My application will be a system that stores a person's bucket list. Users will be able
to enter activities that they want to accomplish. The system will keep track 
of the activities that the user enters in chronological order. Users will be able to
mark tasks as complete. This application 
is for anyone who wants to keep track of accomplishments and activities that they want to complete.
I often have a list of ideas and activities that I want to do, however I never write them down and
often forget. For instance, there are many places that I want to travel to and food places to try. 
Thus, this project is interesting to me because I think it will allow for myself and others to 
keep track of fun activities for the future!


## Task 3

**User Stories:**
- As a user, I want to be able to add an activity to my bucket list.
- As a user, I want to be able to delete an activity from my bucket list.
- As a user, I want to be able to mark an activity as complete on my bucket list.
- As a user, I want to be able to view all the activities on my bucket list.
- As a user, I want to be able to view all uncompleted activities on my bucket list.

**P2 User Stories:**
- As a user, I want to be able to save my bucket list to file
- As a user, when I start the application, I want to be given the option to load my bucket list file.

# Instructions for Grader

- You can generate the first required event related to adding Xs to a Y by clicking the button labelled "Add Activity," 
this adds an activity (X) to the bucket list (Y).
- You can generate the second required event related to removing Xs from a Y by clicking on the button labelled "Remove Activity," 
this removes an activity from the bucket list.
- You can locate my visual component by running the main method in the bucketListGUI class, it is located at the top of the frame (an image of the sky titled "bucket list").
- You can save the state of my application by clicking the button labelled "Save", this saves the state of the application to a json file.
- You can reload the state of my application by clicking the button labelled "Load", this loads the state of the application from the json file.

# Phase 4: Task 2

Mon Nov 28 22:36:57 PST 2022\
Activity added: sailing

Mon Nov 28 22:37:00 PST 2022\
Activity added: surfing

Mon Nov 28 22:37:04 PST 2022\
Activity added: bungee jumping

Mon Nov 28 22:37:07 PST 2022\
Removed activity: sailing

Mon Nov 28 22:37:13 PST 2022\
Activity added: skydiving

Mon Nov 28 22:37:16 PST 2022\
Removed activity: surfing

Mon Nov 28 22:37:26 PST 2022\
Activity added: surfing

# Phase 4: Task 3
- Improve coupling: refactor the implementation in the createPanel method inside BucketListGUI class. Introduce a helper function to decrease code duplication.
In this method, there are a few lines of code that add a component to the JPanel which are repeated, I would extract a method from the lines that are repeated, introducing a helper
to decrease code duplication.
- Improve coupling: refactor the implementation in the createButtons method inside BucketListGUI class. Extract method from duplicated code, introducing
a helper method to decrease duplication.
- Increase cohesion in BucketListGUI class by refactoring. Currently, the BucketListGUI is responsible for creating the GUI which includes creating the 
bucketlist, the buttons, a panel and all the acton listener classes. It had multiple points of control, thus refactoring, so it follows the single responsiblity principle will
increase cohesion.