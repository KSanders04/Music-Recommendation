package edu.bsu.cs222;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LookForFiveGenresTest {
    @Test
    public void testFiveGenre() throws IOException {
        SpotifyAPIParser parser = new SpotifyAPIParser();

        InputStream testDataStream = getClass().getResourceAsStream("test.JSON");
        String FiveGenre = parser.parse(testDataStream);
        assertEquals("hip-hop, indie-pop, rock, sad, work-out", FiveGenre);
    }
}
