package model;

public class Song {
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

}
