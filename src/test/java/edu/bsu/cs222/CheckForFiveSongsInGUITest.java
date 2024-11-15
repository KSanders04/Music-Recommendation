package edu.bsu.cs222;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class CheckForFiveSongsInGUITest {
    private GUIMenu guiMenu;

    @BeforeEach
    public void setUp() {
        guiMenu = new GUIMenu();
    }

    @Test
    public void testGetResultsSong() {
        guiMenu.genreComboBox.setSelectedItem("jazz");
        guiMenu.getResults("song");
        String outputText = guiMenu.outputPane.getText();
        assertNotNull(outputText, "Output pane should display songs.");

        String[] lines = outputText.split("\n");

        long songCount = 0;
        if (lines.length >= 5) {
            for (String line : lines) {
                String trimmedLine = line.trim();
                if (!trimmedLine.isEmpty() && !trimmedLine.contains("Songs:")) {
                    songCount += 1;
                }
            }
        }

        assertEquals(5, songCount, "Output should contain 5 songs.");
    }
}
