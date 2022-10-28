package persistence;

import model.Activity;
import model.BucketList;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.fail;

public class JsonWriterTest extends JsonTest {

    @Test
    void testWriterInvalidFile() {
        try {
            BucketList bucketList = new BucketList();
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IO Exception was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterGeneralBucketList() {
        try {
            BucketList bucketList = new BucketList();
            bucketList.addActivity(new Activity("surfing in Maui"));
            bucketList.addActivity(new Activity("ziplining"));
            Activity ziplining = bucketList.getBucketList().get(0);
            ziplining.markCompleted();
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralBucketList.json");
            writer.open();
            writer.write(bucketList);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralBucketList.json");
            bucketList = reader.read();
            List<Activity> activities = bucketList.getBucketList();
            checkActivity("surfing in Maui", false, activities.get(0));
            checkActivity("ziplining", true, activities.get(1));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

}
