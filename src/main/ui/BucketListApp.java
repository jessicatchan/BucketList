package ui;

import model.Activity;
import model.BucketList;

import java.util.Scanner;

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
        String command = null;

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
            markActivityAsAttained();
        } else if (command.equals("v")) {
            viewActivities();
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
        System.out.println("\tv -> view all unattained activities");
    }

    // TODO: implementation
    // MODIFIES: this
    // EFFECTS: adds an activity to the bucket list if not already in bucket list
    private void addActivity() {
        System.out.println("Enter activity description");
        String description = input.next();

        Activity activity = new Activity(description);

        bucketList.addActivity(activity);


        System.out.println("Activity is added to bucket list!");
    }

    // TODO: implementation
    // MODIFIES: this
    // EFFECTS: removes activity from the bucket list
    private void removeThisActivity() {
        System.out.println("Enter activity to be removed from bucket list");
        String description = input.next();

        bucketList.removeActivity(description);

        System.out.println("Activity is not in bucket list");
    }

    // TODO: implementation
    // MODIFIES: this
    // EFFECTS: marks input activity description as attained
    public void markActivityAsAttained() {
        System.out.println("Enter activity that has been attained");
        String description = input.next();

        Activity activity = new Activity(description);
        activity.markAttained();

        System.out.println("Activity as been marked as attained");
    }

    // MODIFIES: this
    // EFFECTS: prints list of activity descriptions that have not been complete yet
    public void viewActivities() {
        System.out.println("Unattained Activities:");
        if (bucketList.unattainedActivities().size() == 0) {
            System.out.println("All activities completed!");
        } else {
            for (String s: bucketList.unattainedActivities()) {
                System.out.println("\n" + s);
            }
        }
    }


}
