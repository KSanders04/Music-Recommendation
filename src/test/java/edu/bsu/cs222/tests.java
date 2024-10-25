package edu.bsu.cs222;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;

import org.json.JSONArray;
import org.json.JSONObject;

import static org.junit.jupiter.api.Assertions.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class tests {

        @Test
        public void testGetArtistByGenre() {
            ArtistByGenre artistByGenre = new ArtistByGenre();
            try {
                artistByGenre.getArtistByGenre("pop");  // Replace "pop" with any genre you want to test
            } catch (Exception e) {
                fail("Exception thrown: " + e.getMessage());
            }
        }
    }

