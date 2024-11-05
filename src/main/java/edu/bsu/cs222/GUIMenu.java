package edu.bsu.cs222;

import javax.swing.*;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.*;

public class GUIMenu extends JFrame {
    private final JComboBox<String> genreComboBox;
    private final JTextPane outputPane;

    public GUIMenu() {
        setTitle("Music Genre Menu");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Initialize components
        genreComboBox = createGenreComboBox();
        outputPane = createOutputPane();

        // Setup panels and layout
        setupTopPanel();
        setupButtonPanel();
        setupOutputPane();

        setVisible(true);
    }

    private JComboBox<String> createGenreComboBox() {
        String[] genres = { "acoustic", "afrobeat", "alt-rock", "alternative", "ambient", "anime", "black-metal",
                "bluegrass", "blues", "bossanova", "brazil", "breakbeat", "british", "cantopop",
                "chicago-house", "children", "chill", "classical", "club", "comedy", "country",
                "dance", "dancehall", "death-metal", "deep-house", "detroit-techno", "disco", "disney",
                "drum-and-bass", "dub", "dubstep", "edm", "electro", "electronic", "emo", "folk", "forro",
                "french", "funk", "garage", "german", "gospel", "goth", "grindcore", "groove", "grunge",
                "guitar", "happy", "hard-rock", "hardcore", "hardstyle", "heavy-metal", "hip-hop",
                "holidays", "honky-tonk", "house", "idm", "indian", "indie", "indie-pop", "industrial",
                "iranian", "j-dance", "j-idol", "j-pop", "j-rock", "jazz", "k-pop", "kids", "latin",
                "latino", "malay", "mandopop", "metal", "metal-misc", "metalcore", "minimal-techno",
                "movies", "mpb", "new-age", "new-release", "opera", "pagode", "party", "philippines-opm",
                "piano", "pop", "pop-film", "post-dubstep", "power-pop", "progressive-house", "psych-rock",
                "punk", "punk-rock", "r-n-b", "rainy-day", "reggae", "reggaeton", "road-trip", "rock",
                "rock-n-roll", "rockabilly", "romance", "sad", "salsa", "samba", "sertanejo", "show-tunes",
                "singer-songwriter", "ska", "sleep", "songwriter", "soul", "soundtracks", "spanish",
                "study", "summer", "swedish", "synth-pop", "tango", "techno", "trance", "trip-hop",
                "turkish", "work-out", "world-music" };

        JComboBox<String> comboBox = new JComboBox<>(genres);
        comboBox.setForeground(Color.BLACK);
        comboBox.setBackground(Color.GREEN);
        comboBox.setFont(new Font("Serif", Font.PLAIN, 20));
        comboBox.setPreferredSize(new Dimension(150, 40));
        return comboBox;
    }

    private JTextPane createOutputPane() {
        JTextPane pane = new JTextPane();
        pane.setEditable(false);
        pane.setBackground(Color.GRAY);
        pane.setFont(new Font("Serif", Font.BOLD, 20));

        StyledDocument doc = pane.getStyledDocument();
        SimpleAttributeSet center = new SimpleAttributeSet();
        StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
        doc.setParagraphAttributes(0, doc.getLength(), center, false);

        return pane;
    }

    private void setupTopPanel() {
        JLabel genreLabel = new JLabel("Select Genre:");
        genreLabel.setFont(new Font("Serif", Font.BOLD, 20));
        genreLabel.setForeground(Color.GREEN);

        JPanel topPanel = new JPanel();
        topPanel.setBackground(Color.GRAY);
        topPanel.add(genreLabel);
        topPanel.add(genreComboBox);

        add(topPanel, BorderLayout.NORTH);
    }

    private void setupButtonPanel() {
        JButton artistButton = createButton("Get Artists", "artist");
        JButton songButton = createButton("Get Songs", "song");
        JButton bothButton = createButton("Get Both", "both");

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));
        buttonPanel.setBackground(Color.GRAY);
        buttonPanel.add(artistButton);
        buttonPanel.add(songButton);
        buttonPanel.add(bothButton);

        add(buttonPanel, BorderLayout.CENTER);
    }

    private JButton createButton(String text, String type) {
        JButton button = new JButton(text);
        button.setPreferredSize(new Dimension(150, 40));
        button.setBackground(Color.GREEN);
        button.addActionListener(e -> getResults(type));
        return button;
    }

    private void setupOutputPane() {
        JScrollPane scrollPane = new JScrollPane(outputPane);
        scrollPane.setPreferredSize(new Dimension(600, 400));
        add(scrollPane, BorderLayout.SOUTH);
    }

    private void getResults(String type) {
        String genre = (String) genreComboBox.getSelectedItem();
        outputPane.setText("");

        try {
            StringBuilder results = new StringBuilder();
            ArtistByGenre artistByGenre = new ArtistByGenre();
            SongByGenre songByGenre = new SongByGenre();

            switch (type) {
                case "artist" -> results.append("Artists:\n").append(artistByGenre.getArtistByGenre(genre)).append("\n");
                case "song" -> results.append("Songs:\n").append(songByGenre.getSongByGenre(genre)).append("\n");
                case "both" -> {
                    results.append("Artists:\n").append(artistByGenre.getArtistByGenre(genre)).append("\n\n");
                    results.append("Songs:\n").append(songByGenre.getSongByGenre(genre)).append("\n");
                }
            }

            outputPane.setText(results.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(GUIMenu::new);
    }
}
