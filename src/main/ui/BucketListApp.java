package ui;

import model.Activity;
import model.BucketList;

import java.util.Scanner;

// Represents a bucket list application
public class BucketListApp {
    private BucketList bucketList;
    private Scanner input;

    // EFFECTS: runs the bucket list application
    public BucketListApp() {
        runBucketList();
    }

    // MODIFIES: this
    // EFFECTS: processes user input
    private void runBucketList() {
        boolean keepGoing = true;
        String command;

        init();

        while (keepGoing) {
            displayMenu();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("q")) {
                keepGoing = false;
            } else {
                processCommand(command);
            }
        }

        System.out.println("\nGoodbye!");
    }

    // MODIFIES: this
    // EFFECTS: processes user command
    private void processCommand(String command) {
        if (command.equals("a")) {
            addActivity();
        } else if (command.equals("r")) {
            removeThisActivity();
        } else if (command.equals("c")) {
            markActivityAsComplete();
        } else if (command.equals("v")) {
            viewUncompletedActivities();
        } else if (command.equals("all")) {
            viewAllActivities();
        } else {
            System.out.println("Selection not valid...");
        }
    }

    // MODIFIES: this
    // EFFECTS: initializes bucket list
    private void init() {
        bucketList = new BucketList();
        input = new Scanner(System.in);
        input.useDelimiter("\n");
    }

    // EFFECTS: displays menu of options to user
    private void displayMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\ta -> add activity");
        System.out.println("\tr -> remove activity");
        System.out.println("\tc -> check-off activity");
        System.out.println("\tv -> view uncompleted activities");
        System.out.println("\tall -> view complete bucket list");
        System.out.println("\tq -> quit");
    }

    // MODIFIES: this
    // EFFECTS: adds an activity to the bucket list if not already in bucket list
    private void addActivity() {
        System.out.println("Enter activity description");
        String descr = input.next();

        if (!bucketList.allActivityDescr().contains(descr)) {
            Activity activity = new Activity(descr);
            bucketList.addActivity(activity);
            System.out.println("Activity has been added to bucket list!");
        } else {
            System.out.println("Activity is already in bucket list");
        }
    }

    // MODIFIES: this
    // EFFECTS: if activity is in bucket list, removes it. Otherwise, do nothing
    private void removeThisActivity() {
        System.out.println("Enter activity to be removed from bucket list");
        String descr = input.next();

        if (bucketList.allActivityDescr().contains(descr)) {
            bucketList.removeActivity(descr);
            System.out.println("Activity removed from bucket list");
        } else {
            System.out.println("Activity does not exist in bucket list");
        }
    }

    // MODIFIES: this
    // EFFECTS: if activity with description is in bucket list, marks it as complete.
    // Otherwise, do nothing.
    public void markActivityAsComplete() {
        System.out.println("Enter activity that has been completed");
        String descr = input.next();

        if (bucketList.allActivityDescr().contains(descr)) {
            bucketList.markActivityComplete(descr);
            System.out.println("Activity has been marked as completed");
        } else {
            System.out.println("Activity not found");
        }
    }

    // MODIFIES: this
    // EFFECTS: prints a list of activity descriptions that are not completed
    public void viewUncompletedActivities() {
        System.out.println("Uncompleted Activities:");
        for (Activity a: bucketList.getBucketList()) {
            if (!a.getCompleted()) {
                System.out.println(a.getDescription());
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: prints list of all activity descriptions
    public void viewAllActivities() {
        if (bucketList.getBucketList().size() == 0) {
            System.out.println("No activities in your bucket list");
        } else {
            System.out.println("Complete Bucket List:");
            for (String s: bucketList.allActivityDescr()) {
                System.out.println(s);
            }
        }
    }
}
