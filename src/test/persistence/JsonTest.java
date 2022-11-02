package persistence;

import model.Activity;

import static org.junit.jupiter.api.Assertions.*;

public class JsonTest {
    protected void checkActivity(String description, boolean completed, Activity activity) {
        assertEquals(description, activity.getDescription());
        assertEquals(0, Boolean.compare(completed, activity.getCompleted()));
    }
}
