package persistence;

import model.Activity;

import static java.lang.Boolean.compare;
import static org.junit.jupiter.api.Assertions.*;

public class JsonTest {
    protected void checkActivity(String description, boolean completed, Activity activity) {
        assertEquals(description, activity.getDescription());
        assertEquals(0, compare(completed, activity.getCompleted()));
    }
}
