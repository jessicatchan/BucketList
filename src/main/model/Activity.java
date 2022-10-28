package model;

import org.json.JSONObject;
import persistence.Writable;

// Represents an activity with a description and whether it has been completed
public class Activity implements Writable {
    private final String description;
    private boolean completed;

    // REQUIRES: activity is not an empty string
    // EFFECTS: constructs an activity with a description, activity is not completed
    public Activity(String description) {
        this.description = description;
        this.completed = false;
    }

    // MODIFIES: this
    // EFFECTS: marks the activity as completed
    public void markCompleted() {
        this.completed = true;
    }

    // EFFECTS: returns description of activity
    public String getDescription() {
        return description;
    }

    // EFFECTS: returns boolean, true if experience is complete, false otherwise
    public boolean getCompleted() {
        return this.completed;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("description", description);
        json.put("completed", completed);
        return json;
    }

}
