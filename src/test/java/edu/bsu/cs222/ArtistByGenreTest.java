package edu.bsu.cs222;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class ArtistByGenreTest {

        @Test
        public void testGetArtistByGenre() {
            ArtistByGenre artistByGenre = new ArtistByGenre();
            try {
                artistByGenre.getArtistByGenre("pop");
            } catch (Exception e) {
                fail("Exception thrown: " + e.getMessage());
            }
        }
    }

