package edu.bsu.cs222;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.fail;

public class SongByGenreTest {

    @Test
    public void testGetSongsByGenre() {
        SongByGenre songByGenre = new SongByGenre();
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        try {
            songByGenre.getSongByGenre("pop");

            String output = outContent.toString();
            Assertions.assertTrue(output.contains("Songs"));

        } catch (Exception e) {
            fail("Exception thrown: " + e.getMessage());
        } finally {
            System.setOut(System.out);
        }
    }
}