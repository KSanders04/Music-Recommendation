package edu.bsu.cs222;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GUIPlaylistTest {

    @Test
    void testLikedSongs() {
        List<String> likedSongs = Arrays.asList("Song 1", "Song 2", "Song 3");

        GUIPlaylist guiPlaylist = new GUIPlaylist(likedSongs);

        assertEquals(likedSongs, guiPlaylist.likedSongs);
    }
}
