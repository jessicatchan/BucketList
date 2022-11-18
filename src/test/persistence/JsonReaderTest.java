package persistence;

import model.Activity;
import model.BucketList;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            BucketList bucketList = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyBucketList() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyBucketList.json");
        try {
            BucketList bucketList = reader.read();
            assertEquals(0, bucketList.getBucketList().size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralBucketList() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralBucketList.json");
        try {
            BucketList bucketList = reader.read();
            List<Activity> activities = bucketList.getBucketList();
            assertEquals(3, activities.size());
            checkActivity("ziplining", true, activities.get(0));
            checkActivity("surfing in Maui", false, activities.get(1));
            checkActivity("cliff-jumping", false, activities.get(2));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}
