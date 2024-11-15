package edu.bsu.cs222;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ArtistByGenreTest {

    @Test
    public void testGetArtistByGenre() {
        ArtistByGenre artistByGenre = new ArtistByGenre();

        try {
            String result = artistByGenre.getArtistByGenre("pop");

            Assertions.assertNotNull(result, "Result should not be null");
            Assertions.assertFalse(result.trim().isEmpty(), "Result should not be empty");

            String[] lines = result.split(System.lineSeparator());;
            Assertions.assertEquals(5, lines.length, "Expected Five artist in the result");

        } catch (Exception e) {
            e.printStackTrace();
            Assertions.fail("Exception thrown during API request: " + e.getMessage());
        }
    }
}
