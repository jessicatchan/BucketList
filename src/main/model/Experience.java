package model;

// Represents an experience with an activity, activity type, location, price (in dollars), and whether it is completed.
public class Experience {
    private String activity;
    private String activityType;
    private String location;
    private int price;
    private boolean completed;

    // REQUIRES: price >= 0
    // EFFECTS: constructs an experience with a descriptive activity, location, price, and is incomplete
    public Experience(String activity, String activityType, String location, int price) {
        this.activity = activity;
        this.activityType = activityType;
        this.location = location;
        this.price = price;
        this.completed = false;
    }

    // EFFECTS: returns descriptive activity name
    public String getActivity() {
        return activity;
    }

    // EFFECTS: returns activity type
    public String getActivityType() {
        return activityType;
    }

    // EFFECTS: returns location of experience
    public String getLocation() {
        return location;
    }

    // EFFECTS: returns price (in dollars) of experience
    public int getPrice() {
        return price;
    }

    // EFFECTS: returns boolean, true if experience is complete, false otherwise
    public boolean getCompleted() {
        return completed;
    }

}
