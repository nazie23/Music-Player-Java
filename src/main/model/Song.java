package model;

import org.json.JSONObject;
import persistence.Writable;

public class Song implements Writable {
    private String title;
    private String artist;
    private int duration; // in seconds
    private boolean liked;

    public Song(String title, String artist, int duration) {
        this.title = title;
        this.artist = artist;
        this.duration = duration;
        this.liked = false;
    }

    public void toggleLike() {
        this.liked = !this.liked;
    }

    public String getTitle() {
        return title;
    }

    public String getArtist() {
        return artist;
    }

    public int getDuration() {
        return duration;
    }

    public boolean isLiked() {
        return liked;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", this.title);
        json.put("artist", this.artist);
        json.put("duration", this.duration);

        return json;
    }
}
