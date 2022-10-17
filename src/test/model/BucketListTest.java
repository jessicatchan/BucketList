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
        List<Activity> listOfActivities = testBucketList.getBucketList();
        assertEquals(0, listOfActivities.size());
    }

    @Test
    public void testAddActivity() {
        testBucketList.addActivity(a1);

        assertEquals(1, testBucketList.getBucketList().size());
        assertTrue(testBucketList.getBucketList().contains(a1));
        assertEquals(a1, testBucketList.getBucketList().get(0));
    }

    @Test
    public void testAddActivityMultipleTimes() {
        testBucketList.addActivity(a1);
        testBucketList.addActivity(a2);

        assertEquals(2, testBucketList.getBucketList().size());
        assertEquals(a1, testBucketList.getBucketList().get(0));
        assertEquals(a2, testBucketList.getBucketList().get(1));
    }

    @Test
    public void testAddActivityAlreadyThere() {
        testBucketList.addActivity(a1);
        testBucketList.addActivity(a1);

        assertEquals(1, testBucketList.getBucketList().size());
        assertTrue(testBucketList.getBucketList().contains(a1));
    }

    @Test
    public void testRemoveActivity() {
        testBucketList.addActivity(a1);
        testBucketList.addActivity(a2);
        testBucketList.removeActivity("Go surfing in Hawaii");

        assertEquals(1, testBucketList.getBucketList().size());
    }

    @Test
    public void testRemoveActivityMultipleTimes() {
        testBucketList.addActivity(a1);
        testBucketList.addActivity(a2);
        testBucketList.removeActivity(a1.getDescription());
        testBucketList.addActivity(a3);
        testBucketList.removeActivity(a2.getDescription());

        assertEquals(1, testBucketList.getBucketList().size());
        assertTrue(testBucketList.getBucketList().contains(a3));
    }

    @Test
    public void testRemoveActivityNotThere() {
        testBucketList.removeActivity(a1.getDescription());

        assertEquals(0, testBucketList.getBucketList().size());
        assertFalse(testBucketList.getBucketList().contains(a1));
    }

    @Test
    public void testAllActivityDescr() {
        testBucketList.addActivity(a1);
        testBucketList.addActivity(a2);

        assertEquals(2, testBucketList.allActivityDescr().size());
        assertEquals("Go surfing in Hawaii", testBucketList.allActivityDescr().get(0));
        assertEquals("Go skydiving", testBucketList.allActivityDescr().get(1));
    }

    @Test
    public void testMarkItemAsAttained() {
        testBucketList.addActivity(a1);
        testBucketList.addActivity(a2);
        testBucketList.addActivity(a3);
        testBucketList.markActivityComplete("Run a marathon");

        assertTrue(a3.getCompleted());
        assertFalse(a1.getCompleted());
        assertFalse(a2.getCompleted());
    }
}
