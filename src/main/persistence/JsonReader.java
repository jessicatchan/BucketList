package persistence;

import model.Activity;
import model.BucketList;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

// Represents a reader that reads bucketlist from JSON data stored in file
// Source: adapted from cpsc 210 workroom for JsonReader class
public class JsonReader {
    private final String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads bucketlist from file and returns it;
    // throws IOException if an error occurs reading data from file
    public BucketList read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseBucketList(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }
        return contentBuilder.toString();
    }

    // EFFECTS: parses bucket list from JSON object and returns it
    private BucketList parseBucketList(JSONObject jsonObject) {
        BucketList bucketList = new BucketList();
        addActivities(bucketList, jsonObject);
        return bucketList;
    }

    // MODIFIES: bucketList
    // EFFECTS: parses activities from JSON object and adds them to bucketlist
    private void addActivities(BucketList bucketList, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("activities");
        for (Object json: jsonArray) {
            JSONObject nextActivity = (JSONObject) json;
            addActivity(bucketList, nextActivity);
        }
    }

    // MODIFIES: bucketList
    // EFFECTS: parses activity from JSON object and adds it to bucketList
    private void addActivity(BucketList bucketList, JSONObject jsonObject) {
        String description = jsonObject.getString("description");
        boolean completed = jsonObject.getBoolean("completed");
        Activity activity = new Activity(description, completed);
        bucketList.addActivity(activity);
    }
}
