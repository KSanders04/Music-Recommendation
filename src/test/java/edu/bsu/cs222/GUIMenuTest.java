package edu.bsu.cs222;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SuppressWarnings("ALL")
public class GUIMenuTest {

    private GUIMenu guiMenu;

    @BeforeEach
    public void setUp() {
        guiMenu = new GUIMenu();
    }

    @Test
    public void testGetResultsArtist() {
        guiMenu.genreComboBox.setSelectedItem("jazz");
        guiMenu.getResults("artist");
        String outputText = guiMenu.outputPane.getText();
        assertNotNull(outputText, "Output pane should display artists.");
        assertEquals("Artists:", outputText.split("\n")[0]);
    }
}
