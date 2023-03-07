package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;

public class Queue implements Writable {
    private String name;
    private ArrayList<Song> queue;
    private ArrayList<Song> recentlyPlayed;

    public Queue(String name) {
        this.name = name;
        this.queue = new ArrayList<>();
        this.recentlyPlayed = new ArrayList<>();
    }

    // EFFECTS: returns name of queue
    public String getName() {
        return this.name;
    }

    // EFFECTS: returns queue
    public ArrayList<Song> getQueue() {
        return this.queue;
    }

    // REQUIRES: name of playlist
    // MODIFIES: name
    public void setName(String name) {
        this.name = name;
    }

    // REQUIRES: a song
    // MODIFIES: queue
    // EFFECTS: adds the given song to the queue
    public void addToQueue(Song song) {
        this.queue.add(song);
    }

    // REQUIRES: name of a song
    // MODIFIES: queue
    // EFFECTS: removes the song with the given name from the queue
    public boolean removeFromQueue(String name) {
        boolean found = false;

        for (Song s : queue) {
            if (s.getTitle() == name) {
                found = true;
                this.queue.remove(s);
            }
        }

        return found;
    }

    // REQUIRES: queue.size() > 0
    // MODIFIES: queue, recentlyPlayed
    // EFFECTS: removes the first song from the queue and adds to the end of recentlyPlayed
    public void skipSong() {
        this.recentlyPlayed.add(this.queue.get(0));
        this.queue.remove(0);
    }

    // REQUIRES: recentlyPlayed.size() > 0
    // MODIFIES: queue, recentlyPlayed
    // EFFECTS: removes the last song from recentlyPlayed and adds to the beginning of queue
    public void prevSong() {
        this.queue.add(0, this.recentlyPlayed.get(0));
        this.recentlyPlayed.remove(0);
    }

    // EFFECTS: returns length of queue
    public int getQueueLength() {
        return queue.size();
    }

    // EFFECTS: returns length of recentlyPlayed
    public int getRecentlyPlayedLength() {
        return recentlyPlayed.size();
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", this.name);
        json.put("queue", queueToJson());
        json.put("recentlyPlayed", recentToJson());

        return json;
    }

    private JSONArray queueToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Song s : this.queue) {
            jsonArray.put(s.toJson());
        }

        return jsonArray;
    }

    private JSONArray recentToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Song s : this.recentlyPlayed) {
            jsonArray.put(s.toJson());
        }

        return jsonArray;
    }

}
