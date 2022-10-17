package model;

import java.util.ArrayList;
import java.util.List;

// Represents a bucket list
public class BucketList {
    private List<Activity> bucketList;

    // EFFECTS: constructs an empty BucketList
    public BucketList() {
        bucketList = new ArrayList<>();
    }

    // MODIFIES: this
    // EFFECTS: adds activity into bucket list unless it is already there, in which case do nothing
    public void addActivity(Activity activity) {
        if (!bucketList.contains(activity)) {
            bucketList.add(activity);
        }
    }

    // MODIFIES: this
    // EFFECTS: if experience is in the bucket list, remove it. Otherwise, do nothing
    public void removeActivity(String activity) {
        for (Activity a: bucketList) {
            if (a.getDescription() == activity) {
                bucketList.remove(a);
            }
        }
    }

    // EFFECTS: returns a list of the activities in bucketList that have not been attained yet
    public ArrayList<String> unattainedActivities() {
        ArrayList<String> allActivitiesUnattained = new ArrayList<>();
        for (Activity a: this.bucketList) {
            if (!a.getIsAttained()) {
                allActivitiesUnattained.add(a.getDescription());
            }
        }
        return allActivitiesUnattained;
    }

    // EFFECTS: returns all activities in the bucket list
    public List<Activity> allActivities() {
        return bucketList;
    }

}
