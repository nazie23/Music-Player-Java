package model;

import java.util.ArrayList;

public class Queue {
    private ArrayList<Song> queue;
    private ArrayList<Song> recentlyPlayed;

    public Queue() {
        queue = new ArrayList<>();
        recentlyPlayed = new ArrayList<>();
    }

    public void addToQueue(Song song) {
        this.queue.add(song);
    }

    public void removeFromQueue(String name) {
        for (Song s : queue) {
            if (s.getTitle() == name) {
                this.queue.remove(s);
            }
        }
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

}
