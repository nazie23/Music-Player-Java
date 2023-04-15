package persistence;

import model.Queue;
import model.Song;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JsonWriterTest extends JsonTest {

    @Test
    void testWriterInvalidFile() {
        try {
            Queue queue = new Queue("My playlist");
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyPlaylist() {
        try {
            Queue queue = new Queue("My playlist");
            JsonWriter writer = new JsonWriter("./data/testWriterEmpty.json");
            writer.open();
            writer.write(queue);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmpty.json");
            queue = reader.read();
            assertEquals("My playlist", queue.getName());
            assertEquals(0, queue.getRecentlyPlayedLength());
            assertEquals(0, queue.getQueueLength());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneral() {
        try {
            Queue queue = new Queue("My playlist");
            queue.addToQueue(new Song("wake up, get up, get out there", "lyn", 240));
            queue.addToQueue(new Song("life will change", "lyn", 120));
            queue.skipSong();
            JsonWriter writer = new JsonWriter("./data/testWriterPlaylist.json");
            writer.open();
            writer.write(queue);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterPlaylist.json");
            queue = reader.read();
            assertEquals("My playlist", queue.getName());
            assertEquals(1, queue.getQueueLength());
            assertEquals(1, queue.getRecentlyPlayedLength());

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}