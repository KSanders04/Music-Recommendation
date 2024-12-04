package edu.bsu.cs222;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class CheckForFiveArtistInGUITest {
    private GUIMenu guiMenu;
    private MusicController musicController;

    @BeforeEach
    public void setUp() {
        guiMenu = new GUIMenu();
        musicController = new MusicController(guiMenu, new ArtistByGenre(), new SongByGenre());
    }

    @Test
    public void testGetResultsArtist() {
        guiMenu.genreComboBox.setSelectedItem("jazz");
        musicController.getResults("artist");
        String outputText = guiMenu.outputPane.getText();
        assertNotNull(outputText, "Output pane should display artists.");

        String[] lines = outputText.split("\n");

        long fullNameCount = 0;
        if (lines.length >= 5) {
            for (String line : lines) {
                String trimmedLine = line.trim();
                if (!trimmedLine.isEmpty() && !trimmedLine.contains("Artists:")) {
                    fullNameCount++;
                    System.out.println("Artist:  " + trimmedLine);
                }
            }
        }

        assertEquals(5, fullNameCount, "Output should contain 5 full names.");
    }
}
