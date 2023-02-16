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
        this.liked = this.liked != true;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public boolean isLiked() {
        return liked;
    }

}
