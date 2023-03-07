package persistence;

import model.Queue;
import model.Song;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.json.*;

// Represents a reader that reads a queue from JSON data stored in file
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads workroom from file and returns it;
    // throws IOException if an error occurs reading data from file
    public Queue read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseQueue(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses queue from JSON object and returns it
    private Queue parseQueue(JSONObject jsonObject) {
        Queue queue = new Queue(jsonObject.getString("name"));
        addSongsRecentlyPlayed(queue, jsonObject);
        addSongs(queue, jsonObject);
        return queue;
    }

    // MODIFIES: queue
    // EFFECTS: parses songs from JSON object and adds them to queue
    private void addSongsRecentlyPlayed(Queue queue, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("recentlyPlayed");
        for (Object json : jsonArray) {
            JSONObject nextSong = (JSONObject) json;
            addSong(queue, nextSong);
        }

        for (int i = 0; i < jsonArray.length(); i++) {
            queue.skipSong();
        }
    }

    // MODIFIES: queue
    // EFFECTS: parses songs from JSON object and adds them to queue
    private void addSongs(Queue queue, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("queue");
        for (Object json : jsonArray) {
            JSONObject nextSong = (JSONObject) json;
            addSong(queue, nextSong);
        }
    }

    // MODIFIES: queue
    // EFFECTS: parses song from JSON object and adds it to queue
    private void addSong(Queue queue, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        String artist = jsonObject.getString("artist");
        Integer duration = jsonObject.getInt("duration");
        Song song = new Song(name, artist, duration);
        queue.addToQueue(song);
    }
}
