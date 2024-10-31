package edu.bsu.cs222;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class GUIMenu extends JFrame {
    private final JComboBox<String> genreComboBox;
    private final JTextArea outputArea;

    public GUIMenu() {
        setTitle("Music Genre Menu");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Create components
        JLabel genreLabel = new JLabel("Select Genre:");
        String[] genres = {
                "acoustic", "afrobeat", "alt-rock", "alternative", "ambient", "anime", "black-metal", "bluegrass",
                "blues", "bossanova", "brazil", "breakbeat", "british", "cantopop", "chicago-house", "children",
                "chill", "classical", "club", "comedy", "country", "dance", "dancehall", "death-metal", "deep-house",
                "detroit-techno", "disco", "disney", "drum-and-bass", "dub", "dubstep", "edm", "electro", "electronic",
                "emo", "folk", "forro", "french", "funk", "garage", "german", "gospel", "goth", "grindcore", "groove",
                "grunge", "guitar", "happy", "hard-rock", "hardcore", "hardstyle", "heavy-metal", "hip-hop", "holidays",
                "honky-tonk", "house", "idm", "indian", "indie", "indie-pop", "industrial", "iranian", "j-dance", "j-idol",
                "j-pop", "j-rock", "jazz", "k-pop", "kids", "latin", "latino", "malay", "mandopop", "metal", "metal-misc",
                "metalcore", "minimal-techno", "movies", "mpb", "new-age", "new-release", "opera", "pagode", "party",
                "philippines-opm", "piano", "pop", "pop-film", "post-dubstep", "power-pop", "progressive-house",
                "psych-rock", "punk", "punk-rock", "r-n-b", "rainy-day", "reggae", "reggaeton", "road-trip", "rock",
                "rock-n-roll", "rockabilly", "romance", "sad", "salsa", "samba", "sertanejo", "show-tunes",
                "singer-songwriter", "ska", "sleep", "songwriter", "soul", "soundtracks", "spanish", "study", "summer",
                "swedish", "synth-pop", "tango", "techno", "trance", "trip-hop", "turkish", "work-out", "world-music"
        };
        genreComboBox = new JComboBox<>(genres);

        outputArea = createOutputArea();
        JScrollPane scrollPane = new JScrollPane(outputArea);

        JButton artistButton = new JButton("Get Artists");
        JButton songButton = new JButton("Get Songs");
        JButton bothButton = new JButton("Get Both");

        artistButton.addActionListener(e -> getResults("artist"));
        songButton.addActionListener(e -> getResults("song"));
        bothButton.addActionListener(e -> getResults("both"));

        // Layout components
        JPanel topPanel = new JPanel();
        topPanel.add(genreLabel);
        topPanel.add(genreComboBox);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(artistButton);
        buttonPanel.add(songButton);
        buttonPanel.add(bothButton);

        add(topPanel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);
        add(scrollPane, BorderLayout.SOUTH);
    }

    private JTextArea createOutputArea() {
        JTextArea area = new JTextArea("Results");
        area.setEditable(false);
        return area;
    }

    private void getResults(String type) {
        String genre = (String) genreComboBox.getSelectedItem();
        outputArea.setText("");  // Clear previous results
        try {
            ArtistByGenre artistByGenre = new ArtistByGenre();
            SongByGenre songByGenre = new SongByGenre();

            switch (type) {
                case "artist" -> {
                    artistByGenre.getArtistByGenre(genre);
                    outputArea.append("Artists for genre " + genre + "\n");
                }
                case "song" -> {
                    songByGenre.getSongByGenre(genre);
                    outputArea.append("Songs for genre " + genre + "\n");
                }
                case "both" -> {
                    artistByGenre.getArtistByGenre(genre);
                    songByGenre.getSongByGenre(genre);
                    outputArea.append("Artists and Songs for genre " + genre + "\n");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new GUIMenu().setVisible(true));
    }
}
