package ui;

import model.Activity;
import model.BucketList;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.util.Scanner;

// Represents a bucket list application
public class BucketListApp {
    private static final String JSON_STORE = "./data/bucketList.json";
    private final BucketList bucketList;
    private Scanner input;
    private final JsonWriter jsonWriter;
    private final JsonReader jsonReader;

    // EFFECTS: runs the bucket list application
    public BucketListApp() throws FileNotFoundException {
        input = new Scanner(System.in);
        bucketList = new BucketList();
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        runBucketList();
    }

    // MODIFIES: this
    // EFFECTS: processes user input
    // source: code based on cpsc210 teller application
    private void runBucketList() {
        boolean keepGoing = true;
        String command;
        input = new Scanner((System.in));

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

    // EFFECTS: displays menu of options to user
    private void displayMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\ta -> add activity");
        System.out.println("\tr -> remove activity");
        System.out.println("\tc -> check-off activity");
        System.out.println("\tv -> view uncompleted activities");
        System.out.println("\tall -> view complete bucket list");
        System.out.println("\ts -> save bucket list to file");
        System.out.println("\tl -> load bucket list from file");
        System.out.println("\tq -> quit");
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
//        } else if (command.equals("s")) {
//            saveBucketList();
//        } else if (command.equals("l")) {
//            loadBucketList();
        } else {
            System.out.println("Selection not valid...");
        }
    }

    // MODIFIES: this
    // EFFECTS: adds an activity to the bucket list if not already in bucket list
    private void addActivity() {
        System.out.println("Enter activity description");
        String descr = input.next();

        if (!bucketList.allDescriptions().contains(descr)) {
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

        if (bucketList.allDescriptions().contains(descr)) {
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

        if (bucketList.allDescriptions().contains(descr)) {
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
            for (String s: bucketList.allDescriptions()) {
                System.out.println(s);
            }
        }
    }
}
