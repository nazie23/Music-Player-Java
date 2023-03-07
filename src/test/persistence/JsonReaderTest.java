package persistence;

import model.Queue;
import model.Song;
import org.junit.jupiter.api.Test;
import persistence.JsonReader;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            Queue queue = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmpty() {
        JsonReader reader = new JsonReader("./data/testReaderEmpty.json");
        try {
            Queue queue = reader.read();
            assertEquals("Untitled", queue.getName());
            assertEquals(0, queue.getQueueLength());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderPlaylist() {
        JsonReader reader = new JsonReader("./data/testReaderPlaylist.json");
        try {
            Queue queue = reader.read();
            assertEquals("persona 5", queue.getName());
            assertEquals(1, queue.getRecentlyPlayedLength());
            assertEquals(1, queue.getQueueLength());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

}