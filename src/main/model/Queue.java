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

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addToQueue(Song song) {
        this.queue.add(song);
    }

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
    public void skipSong() {
        this.recentlyPlayed.add(this.queue.get(0));
        this.queue.remove(0);
    }

    // REQUIRES: recentlyPlayed.size() > 0
    public void prevSong() {
        this.queue.add(0, this.recentlyPlayed.get(0));
        this.recentlyPlayed.remove(0);
    }

    public void printQueue() {
        if (queue.isEmpty()) {
            System.out.println("The queue is empty!");
        } else {
            for (Song s : this.queue) {
                System.out.println(s.getTitle() + " by " + s.getArtist());
            }
        }
    }

    public int getQueueLength() {
        return queue.size();
    }

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
