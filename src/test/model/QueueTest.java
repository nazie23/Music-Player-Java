package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class QueueTest {
    private Queue playlist;
    private Song s1;
    private Song s2;

    @BeforeEach
    public void setup() {
        playlist = new Queue("Untitled");
        s1 = new Song("Song1", "Anon", 145);
        s2 = new Song("Song2", "Anon", 190);
    }

    @Test
    public void constructorTest() {
        playlist.setName("My playlist");
        assertEquals(playlist.getQueueLength(), 0);
        assertEquals(playlist.getRecentlyPlayedLength(), 0);
        assertEquals("My playlist", playlist.getName());
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

        assertTrue(playlist.removeFromQueue("Song1"));
        assertEquals(playlist.getQueueLength(), 1);
        assertEquals(playlist.getRecentlyPlayedLength(), 0);

        assertFalse(playlist.removeFromQueue("Song3"));
        assertEquals(playlist.getQueueLength(), 1);
        assertEquals(playlist.getRecentlyPlayedLength(), 0);

        assertTrue(playlist.removeFromQueue("Song2"));
        assertEquals(playlist.getQueueLength(), 0);
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
