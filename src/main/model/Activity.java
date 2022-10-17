package model;

// Represents an activity with a description and whether it has been attained.
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
    // EFFECTS: marks the activity as attained, regardless if it has been attained already
    public void markAttained() {
        this.attained = true;
    }

    // EFFECTS: returns description of activity
    public String getDescription() {
        return description;
    }

    // EFFECTS: returns boolean, true if experience is complete, false otherwise
    public boolean getIsAttained() {
        return attained;
    }
}
