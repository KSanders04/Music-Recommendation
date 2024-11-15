package edu.bsu.cs222;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.junit.jupiter.api.Assertions.*;


public class ArtistByGenreTest {

    @Test
    public void testGetArtistByGenre() {
        ArtistByGenre artistByGenre = new ArtistByGenre();

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        try {
            artistByGenre.getArtistByGenre("pop");

            String output = outContent.toString();
            Assertions.assertTrue(output.contains("Artists:"));

        } catch (Exception e) {
            fail("Exception thrown: " + e.getMessage());
        } finally {
            System.setOut(System.out);
        }
    }
}