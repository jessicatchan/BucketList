package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ActivityTest {
    private Activity testActivity;

    @BeforeEach
    public void runBefore() {
        testActivity = new Activity("Go Surfing in Hawaii");
    }

    @Test
    public void testConstructor() {
        assertEquals("Go Surfing in Hawaii", testActivity.getDescription());
        assertFalse(testActivity.getCompleted());
    }

    @Test
    public void testMarkCompleted() {
        testActivity.markCompleted();
        assertTrue(testActivity.getCompleted());
    }

    @Test
    public void testMarkCompletedMultipleTimes() {
        testActivity.markCompleted();
        testActivity.markCompleted();

        assertTrue(testActivity.getCompleted());
    }
}