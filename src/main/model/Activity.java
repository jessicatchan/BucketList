package model;

import org.json.JSONObject;
import persistence.Writable;

import java.util.Objects;

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

    // REQUIRES: activity is not an empty string
    // EFFECTS: constructs an activity with a description, and marks activity as true if completed, false otherwise
    public Activity(String description, Boolean completed) {
        this.description = description;
        this.completed = completed;
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
        return completed;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("description", description);
        json.put("completed", completed);
        return json;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Activity activity = (Activity) o;
        return description.equals(activity.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(description);
    }
}
