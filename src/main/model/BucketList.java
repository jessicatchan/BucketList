package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.List;

// Represents a bucket list to store a users list of activities (bucket list).
public class BucketList implements Writable {
    private final List<Activity> bucketList;

    // EFFECTS: constructs an empty BucketList
    public BucketList() {
        this.bucketList = new ArrayList<>();
    }

    // MODIFIES: this
    // EFFECTS: adds activity into bucket list unless it is already there, in which case do nothing
    //          adds event to EventLog,
    public void addActivity(Activity activity) {
        if (!bucketList.contains(activity)) {
            bucketList.add(activity);
            EventLog.getInstance().logEvent(new Event("Activity added: " + activity.getDescription()));
        }
    }

    // MODIFIES: this
    // EFFECTS: if activity is in the bucket list, remove it. Otherwise, do nothing
    public void removeActivity(String activity) {
        bucketList.removeIf(activity1 -> activity1.getDescription().equals(activity));
    }

    // MODIFIES: this
    // EFFECTS: removes activity at given index
    public void removeActivityAtIndex(int index) {
        String activityName = bucketList.get(index).getDescription();
        bucketList.remove(index);
        EventLog.getInstance().logEvent(new Event("Removed activity: " + activityName));
    }

    // EFFECTS: returns a list of every activity description in the bucket list
    public List<String> allDescriptions() {
        List<String> listOfDescr = new ArrayList<>();
        for (Activity a: bucketList) {
            listOfDescr.add(a.getDescription());
        }
        return listOfDescr;
    }

    // EFFECTS: if an activity in the bucket list has the same description as descr, marks it as complete.
    // Otherwise, do nothing.
    public void markActivityComplete(String descr) {
        for (Activity a: bucketList) {
            if (a.getDescription().equals(descr)) {
                a.markCompleted();
            }
        }
    }

    // EFFECTS: returns all the Activities in the bucket list
    public List<Activity> getBucketList() {
        return bucketList;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("activities", activitiesToJson());
        return json;
    }

    // EFFECTS: returns activities in bucket list as a JSON array
    private JSONArray activitiesToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Activity a: bucketList) {
            jsonArray.put(a.toJson());
        }
        return jsonArray;
    }
}
