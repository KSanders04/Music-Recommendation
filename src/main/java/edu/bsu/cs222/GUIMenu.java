package edu.bsu.cs222;

import javax.swing.*;
import java.awt.*;

public class GUIMenu extends JFrame {
    private final JComboBox<String> genreComboBox;
    private final JTextArea outputArea;

    public GUIMenu() {
        setTitle("Music Genre Menu");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JLabel genreLabel = new JLabel("Select Genre:");
        genreLabel.setFont(new Font("Serif", Font.BOLD,20));
        genreLabel.setForeground(Color.GREEN);
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
        genreComboBox.setForeground(Color.BLACK);
        genreComboBox.setBackground(Color.GREEN);


        outputArea = createOutputArea();
        JLabel labelArea = new JLabel("Results");
        JScrollPane scrollPane = new JScrollPane(outputArea);
        scrollPane.setPreferredSize(new Dimension(600, 400));

        JButton artistButton = new JButton("Get Artists");
        JButton songButton = new JButton("Get Songs");
        JButton bothButton = new JButton("Get Both");

        artistButton.addActionListener(e -> getResults("artist"));
        songButton.addActionListener(e -> getResults("song"));
        bothButton.addActionListener(e -> getResults("both"));

        artistButton.setBackground(Color.GREEN);
        artistButton.setForeground(Color.BLACK);
        songButton.setBackground(Color.GREEN);
        songButton.setForeground(Color.BLACK);
        bothButton.setBackground(Color.GREEN);
        bothButton.setForeground(Color.BLACK);

        JPanel topPanel = new JPanel();
        topPanel.setBackground(Color.GRAY);
        topPanel.add(genreLabel);
        topPanel.add(genreComboBox);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.GRAY);
        buttonPanel.add(artistButton);
        buttonPanel.add(songButton);
        buttonPanel.add(bothButton);

        JPanel scrollPaneLabel = new JPanel();
        scrollPaneLabel.setBackground(Color.BLACK);
        scrollPaneLabel.add(labelArea);

        add(topPanel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);
        add(scrollPane, BorderLayout.SOUTH);
        add(scrollPaneLabel, BorderLayout.WEST);

        setVisible(true);
    }

    private JTextArea createOutputArea() {
        JTextArea area = new JTextArea();
        area.setEditable(false);
        area.setLineWrap(true);
        area.setWrapStyleWord(true);
        area.setBackground(Color.GRAY);
        area.setFont(new Font("Serif", Font.BOLD, 30));
        return area;
    }

    private void getResults(String type) {
        String genre = (String) genreComboBox.getSelectedItem();
        outputArea.setText("");
        try {
            StringBuilder results = new StringBuilder();
            ArtistByGenre artistByGenre = new ArtistByGenre();
            SongByGenre songByGenre = new SongByGenre();

            if (type.equals("artist")) {
                results.append("Artists:\n").append(artistByGenre.getArtistByGenre(genre)).append("\n");
            } else if (type.equals("song")) {
                results.append("Songs:\n").append(songByGenre.getSongByGenre(genre)).append("\n");
            } else if (type.equals("both")) {
                results.append("Artists:\n").append(artistByGenre.getArtistByGenre(genre)).append("\n\n");
                results.append("Songs:\n").append(songByGenre.getSongByGenre(genre)).append("\n");
            }

            outputArea.setText(results.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new GUIMenu().setVisible(true));
    }
}