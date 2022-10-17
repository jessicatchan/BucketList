package model;

import java.util.ArrayList;
import java.util.List;

// Represents a bucket list to store a users bucket list activities.
public class BucketList {
    private List<Activity> bucketList;

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
        for (Activity a: bucketList) {
            if (a.getDescription().equals(activity)) {
                bucketList.remove(a);
            }
        }
    }

    // EFFECTS: returns a list of all the Activities in the bucket list
    public List<Activity> allActivities() {
        return bucketList;
    }

    // EFFECTS: returns a list of every activity description in the bucket list
    public List<String> allActivityDescr() {
        List<String> listOfDescr = new ArrayList<>();
        for (Activity a: bucketList) {
            listOfDescr.add(a.getDescription());
        }
        return listOfDescr;
    }

    // EFFECTS: marks the Activity in the bucket list which has the same desc as attained
    public void markItemAsAttained(String descr) {
        for (Activity a: bucketList) {
            if (a.getDescription().equals(descr)) {
                a.markAttained();
            }
        }
    }
}