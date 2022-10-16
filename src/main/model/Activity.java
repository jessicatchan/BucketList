package model;

// Represents an activity with a description and whether it is attained.
public class Activity {
    private String description;
    private boolean attained;

    // REQUIRES: activity is not an empty string
    // EFFECTS: constructs an activity with a description, activity is not attained
    public Activity(String description) {
        this.description = description;
        this.attained = false;
    }

    // MODIFIES: this
    // EFFECTS: marks the experience as attained
    public void markAttained() {
        this.attained = true;
    }

    // EFFECTS: returns descriptive activity name
    public String getDescription() {
        return description;
    }

    // EFFECTS: returns boolean, true if experience is complete, false otherwise
    public boolean getIsAttained() {
        return attained;
    }

}
