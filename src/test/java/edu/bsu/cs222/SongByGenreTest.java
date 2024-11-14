package edu.bsu.cs222;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SongByGenreTest {

    @Test
    void testGetSongByGenreWithPreviews() throws Exception {
        SongByGenre songByGenre = new SongByGenre();

        String genre = "pop";
        String[][] songs = songByGenre.getSongByGenreWithPreviews(genre);

        assertNotNull(songs, "The songs array should not be null");
        assertTrue(songs.length > 0, "The songs array should contain at least one song");
        assertEquals(2, songs[0].length, "Each song entry should contain two elements (song name and preview URL)");
    }
}
