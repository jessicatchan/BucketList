package persistence;

import model.Activity;

import static java.lang.Boolean.compare;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {
    protected void checkActivity(String description, boolean completed, Activity activity) {
        assertEquals(description, activity.getDescription());
        compare(completed, activity.getCompleted());
    }
}
