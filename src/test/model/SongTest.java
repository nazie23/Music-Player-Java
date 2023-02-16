package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SongTest {
    private Song s1;
    private Song s2;

    @BeforeEach
    public void setup() {
        s1 = new Song("Song1", "Anon", 145);
        s2 = new Song("Song2", "Anon", 190);
    }

    @Test
    public void constructorTest() {
        assertEquals(s1.getTitle(), "Song1");
        assertEquals(s1.getArtist(), "Anon");
        assertEquals(s1.getDuration(), 145);
        assertFalse(s1.isLiked());

        assertEquals(s2.getTitle(), "Song2");
        assertEquals(s2.getArtist(), "Anon");
        assertEquals(s2.getDuration(), 190);
        assertFalse(s2.isLiked());
    }

    @Test
    public void toggleLikeTest() {
        s1.toggleLike();
        assertTrue(s1.isLiked());

        s2.toggleLike();
        assertTrue(s2.isLiked());
        s2.toggleLike();
        assertFalse(s2.isLiked());
    }

}