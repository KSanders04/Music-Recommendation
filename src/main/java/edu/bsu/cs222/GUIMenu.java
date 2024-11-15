package edu.bsu.cs222;

import javazoom.jl.player.Player;

import javax.swing.*;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.*;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

@SuppressWarnings("ALL")
public class GUIMenu extends JFrame {
    public final JComboBox<String> genreComboBox;
    public final JTextPane outputPane;
    private Player player;

    public GUIMenu() {
        setTitle("Music Genre Menu");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        genreComboBox = createGenreComboBox();
        outputPane = createOutputPane();

        title();
        setupTopPanel();
        setupButtonPanel();
        setupOutputPane();

        setVisible(true);
    }

    private void title() {
        JLabel title = new JLabel("Music Recommender");
        title.setFont(new Font("Roboto", Font.BOLD, 50));
        title.setForeground(Color.WHITE);

        JPanel titlePanel = new JPanel();
        titlePanel.setBackground(Color.DARK_GRAY);
        titlePanel.add(title);
        titlePanel.setBorder(BorderFactory.createEmptyBorder(0, 100, 0, 0));

        add(titlePanel, BorderLayout.NORTH);
    }

    private JComboBox<String> createGenreComboBox() {
        String[] genres = {"acoustic", "afrobeat", "alt-rock", "alternative", "ambient", "anime", "black-metal",
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
                "turkish", "work-out", "world-music"};

        JComboBox<String> comboBox = new JComboBox<>(genres);
        comboBox.setForeground(Color.WHITE);
        comboBox.setBackground(Color.decode("#1338BE"));
        comboBox.setFont(new Font("Serif", Font.PLAIN, 20));
        comboBox.setPreferredSize(new Dimension(150, 40));
        return comboBox;
    }

    private JTextPane createOutputPane() {
        JTextPane pane = new JTextPane();
        pane.setEditable(false);
        pane.setBackground(Color.DARK_GRAY);
        pane.setForeground(Color.WHITE);
        pane.setFont(new Font("Serif", Font.PLAIN, 20));

        StyledDocument doc = pane.getStyledDocument();
        SimpleAttributeSet center = new SimpleAttributeSet();
        StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
        doc.setParagraphAttributes(0, doc.getLength(), center, false);

        return pane;
    }

    private void setupTopPanel() {
        JLabel genreLabel = new JLabel("Select Genre:");
        genreLabel.setFont(new Font("Roboto", Font.BOLD, 20));

        JPanel topPanel = new JPanel();
        topPanel.add(genreLabel);
        topPanel.add(genreComboBox);

        add(topPanel, BorderLayout.CENTER);
    }

    private void setupButtonPanel() {
        JButton artistButton = createButton("Get Artists", "artist");
        JButton songButton = createButton("Get Songs", "song");
        JButton bothButton = createButton("Get Both", "both");

        artistButton.setFont(new Font("Roboto", Font.BOLD, 22));
        songButton.setFont(new Font("Roboto", Font.BOLD, 22));
        bothButton.setFont(new Font("Roboto", Font.BOLD, 22));

        artistButton.setBackground(Color.decode("#57A0D3"));
        songButton.setBackground(Color.decode("#0147AB"));
        bothButton.setBackground(Color.decode("#101D6B"));

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(3, 2));

        buttonPanel.setBackground(Color.WHITE);
        buttonPanel.setForeground(Color.WHITE);

        buttonPanel.add(artistButton);
        buttonPanel.add(songButton);
        buttonPanel.add(bothButton);

        add(buttonPanel, BorderLayout.WEST);
    }

    private JButton createButton(String text, String type) {
        JButton button = new JButton(text);
        button.setPreferredSize(new Dimension(150, 40));
        button.setForeground(Color.WHITE);
        button.addActionListener(e -> getResults(type));
        return button;
    }

    private JButton playButton(String previewUrl){
        JButton playButton = new JButton("Play");
        playButton.addActionListener(e -> playPreview(previewUrl));
        return playButton;
    }

    private void setupOutputPane() {

        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BorderLayout());
        centerPanel.setBackground(Color.GRAY);

        JPanel topPanel = new JPanel();
        topPanel.setBackground(Color.DARK_GRAY);
        JLabel genreLabel = new JLabel("Select Genre:");
        genreLabel.setFont(new Font("Serif", Font.BOLD, 20));
        genreLabel.setForeground(Color.WHITE);
        topPanel.add(genreLabel);
        topPanel.add(genreComboBox);

        JScrollPane scrollPane = new JScrollPane(outputPane);
        scrollPane.setPreferredSize(new Dimension(250, 150));

        centerPanel.add(topPanel, BorderLayout.NORTH);
        centerPanel.add(scrollPane, BorderLayout.CENTER);

        add(centerPanel, BorderLayout.CENTER);
    }

    public void getResults(String type) {
        String genre = (String) genreComboBox.getSelectedItem();
        outputPane.setText("");

        try {
            StyledDocument doc = outputPane.getStyledDocument();
            SimpleAttributeSet boldStyle = new SimpleAttributeSet();
            StyleConstants.setBold(boldStyle, true);
            StyleConstants.setFontSize(boldStyle, 40);

            SimpleAttributeSet normalStyle = new SimpleAttributeSet();
            StyleConstants.setFontSize(normalStyle, 20);

            ArtistByGenre artistByGenre = new ArtistByGenre();
            SongByGenre songByGenre = new SongByGenre();

            switch (type) {
                case "artist" -> {
                    doc.insertString(doc.getLength(), "Artists:\n\n", boldStyle);
                    doc.insertString(doc.getLength(), artistByGenre.getArtistByGenre(genre) + "\n", normalStyle);
                }
                case "song" -> {
                    doc.insertString(doc.getLength(), "Songs:\n", boldStyle);
                    String[][] songs = songByGenre.getSongByGenreWithPreviews(genre);
                    for (String[] song : songs) {
                        String songName = song[0];
                        String previewUrl = song[1];

                        doc.insertString(doc.getLength(),"\n" + songName + "   ", normalStyle);

                        JButton playButton = playButton(previewUrl);
                        outputPane.insertComponent(playButton);
                    }
                }
                case "both" -> {
                    doc.insertString(doc.getLength(), "Artists:\n", boldStyle);
                    doc.insertString(doc.getLength(), artistByGenre.getArtistByGenre(genre) + "\n", normalStyle);

                    doc.insertString(doc.getLength(), "Songs:", boldStyle);
                    String[][] songs = songByGenre.getSongByGenreWithPreviews(genre);
                    for (String[] song : songs) {
                        String songName = song[0];
                        String previewUrl = song[1];

                        doc.insertString(doc.getLength(),"\n" + songName + "   ", normalStyle);

                        JButton playButton = playButton(previewUrl);
                        outputPane.insertComponent(playButton);
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }

    private void playPreview(String previewUrl) {
        if (previewUrl == null || previewUrl.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No preview available for this song.");
            return;
        }
        if (player != null){
            player.close();
        }

        try {
            HttpURLConnection connection = (HttpURLConnection) new URL(previewUrl).openConnection();
            connection.setRequestMethod("GET");

            InputStream inputStream = connection.getInputStream();
            player = new Player(inputStream);
            new Thread(() -> {
                try {
                    player.play();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "Error: " + ex);
                }
            }).start();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Could not play preview.");
        }
    }

}

