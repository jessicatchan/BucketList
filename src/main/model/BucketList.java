package model;

import java.util.ArrayList;
import java.util.List;

// Represents a bucket list to store a users list of activities (bucket list).
public class BucketList {
    private final List<Activity> bucketList;

    // EFFECTS: constructs an empty BucketList
    public BucketList() {
        this.bucketList = new ArrayList<>();
    }

    // MODIFIES: this
    // EFFECTS: adds activity into bucket list unless it is already there, in which case do nothing
    public void addActivity(Activity activity) {
        if (!bucketList.contains(activity)) {
            bucketList.add(activity);
        }
    }

    // MODIFIES: this
    // EFFECTS: if activity is in the bucket list, remove it. Otherwise, do nothing
    public void removeActivity(String activity) {
        bucketList.removeIf(activity1 -> activity1.getDescription().equals(activity));
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
}
