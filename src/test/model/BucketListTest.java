package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class BucketListTest {
    private BucketList testBucketList;
    private Activity a1;
    private Activity a2;
    private Activity a3;

    @BeforeEach
    public void runBefore() {
        testBucketList = new BucketList();
        a1 = new Activity("Go surfing in Hawaii");
        a2 = new Activity("Go skydiving");
        a3 = new Activity("Run a marathon");
    }

    @Test
    public void testConstructor() {
        List<Activity> listOfActivities = testBucketList.allActivities();
        assertEquals(0, listOfActivities.size());
    }

    @Test
    public void testAddActivity() {
        testBucketList.addActivity(a1);

        List<Activity> listOfActivities = testBucketList.allActivities();

        assertEquals(1, listOfActivities.size());
        assertTrue(listOfActivities.contains(a1));
        assertEquals(a1, listOfActivities.get(0));
    }

    @Test
    public void testAddActivityMultipleTimes() {
        testBucketList.addActivity(a1);
        testBucketList.addActivity(a2);

        List<Activity> listOfActivities = testBucketList.allActivities();

        assertEquals(2, listOfActivities.size());
        assertEquals(a1, listOfActivities.get(0));
        assertEquals(a2, listOfActivities.get(1));
    }

    @Test
    public void testAddActivityAlreadyThere() {
        testBucketList.addActivity(a1);
        testBucketList.addActivity(a1);

        List<Activity> listOfActivities = testBucketList.allActivities();

        assertEquals(1, listOfActivities.size());
        assertTrue(listOfActivities.contains(a1));
    }

    @Test
    public void testRemoveActivity() {
        testBucketList.addActivity(a1);
        testBucketList.addActivity(a2);

        testBucketList.removeActivity("Go surfing in Hawaii");

        List<Activity> listOfActivities = testBucketList.allActivities();

        assertEquals(1, listOfActivities.size());
    }

    @Test
    public void testRemoveActivityMultipleTimes() {
        testBucketList.addActivity(a1);
        testBucketList.addActivity(a2);
        testBucketList.removeActivity("Go surfing in Hawaii");
        testBucketList.addActivity(a3);
        testBucketList.removeActivity("Go skydiving");

        List<Activity> listOfActivities = testBucketList.allActivities();

        assertEquals(1, listOfActivities.size());
        assertTrue(listOfActivities.contains(a3));
    }

    @Test
    public void testRemoveActivityNotThere() {
        testBucketList.removeActivity("Go surfing in Hawaii");
        List<Activity> listOfActivities = testBucketList.allActivities();

        assertEquals(0, listOfActivities.size());
        assertFalse(listOfActivities.contains(a1));
    }

    @Test
    public void testAllActivityDescr() {
        testBucketList.addActivity(a1);
        testBucketList.addActivity(a2);

        List<String> listofActivities = testBucketList.allActivityDescr();

        assertEquals(2, listofActivities.size());
        assertEquals("Go surfing in Hawaii", listofActivities.get(0));
        assertEquals("Go skydiving", listofActivities.get(1));
    }

    @Test
    public void testMarkItemAsAttained() {
        testBucketList.addActivity(a1);
        testBucketList.addActivity(a2);
        testBucketList.addActivity(a3);
        testBucketList.markItemAsAttained("Run a marathon");

        assertTrue(a3.getIsAttained());
        assertFalse(a1.getIsAttained());
        assertFalse(a2.getIsAttained());
    }
}
