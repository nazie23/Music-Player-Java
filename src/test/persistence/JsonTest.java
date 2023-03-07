package persistence;

import model.Queue;
import model.Song;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {
    protected void checkThingy(String name, String artist, Integer dur, Song song) {
        assertEquals(name, song.getTitle());
        assertEquals(artist, song.getTitle());
        assertEquals(dur, song.getDuration());
    }
}
