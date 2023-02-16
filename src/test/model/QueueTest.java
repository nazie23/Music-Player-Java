package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class QueueTest {
    private Queue playlist;
    private Song s1;
    private Song s2;

    @BeforeEach
    public void setup() {
        playlist = new Queue();
        s1 = new Song("Song1", "Anon", 145);
        s2 = new Song("Song2", "Anon", 190);
    }

    @Test
    public void constructorTest() {
        assertEquals(playlist.getQueue(), new ArrayList<>());
        assertEquals(playlist.getRecentlyPlayed(), new ArrayList<>());
        assertEquals(playlist.getQueueLength(), 0);
        assertEquals(playlist.getRecentlyPlayedLength(), 0);
    }

    @Test
    public void addTest() {
        playlist.addToQueue(s1);
        assertEquals(playlist.getQueueLength(), 1);
        assertEquals(playlist.getRecentlyPlayedLength(), 0);

        playlist.addToQueue(s2);
        assertEquals(playlist.getQueueLength(), 2);
        assertEquals(playlist.getRecentlyPlayedLength(), 0);
    }

    @Test
    public void removeTest() {
        playlist.addToQueue(s1);
        playlist.addToQueue(s2);
        assertEquals(playlist.getQueueLength(), 2);

        playlist.removeFromQueue("Song1");
        assertEquals(playlist.getQueueLength(), 1);
        assertEquals(playlist.getRecentlyPlayedLength(), 0);
    }

    @Test
    public void skipSong() {
        playlist.addToQueue(s1);
        playlist.addToQueue(s2);

        playlist.skipSong();
        assertEquals(playlist.getQueueLength(), 1);
        assertEquals(playlist.getRecentlyPlayedLength(), 1);
    }

    @Test
    public void prevSong() {
        playlist.addToQueue(s1);
        playlist.addToQueue(s2);
        playlist.skipSong();
        playlist.prevSong();

        assertEquals(playlist.getQueueLength(), 2);
        assertEquals(playlist.getRecentlyPlayedLength(), 0);
    }

}
