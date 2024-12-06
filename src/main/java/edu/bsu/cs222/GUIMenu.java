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
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("ALL")
public class GUIMenu extends JFrame {
    public final JComboBox<String> genreComboBox;
    public final JTextPane outputPane;
    private Player player;
    private final List<String> likedSongs = new ArrayList<>();
    private final MusicController musicController;

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
        musicController = new MusicController(this, new ArtistByGenre(), new SongByGenre());
    }

    private void title() {
        JLabel title = new JLabel("🎵   Music Recommender   🎵");
        title.setFont(new Font("Roboto", Font.BOLD, 50));
        title.setForeground(Color.WHITE);

        JPanel titlePanel = new JPanel();
        titlePanel.setBackground(Color.DARK_GRAY);
        titlePanel.add(title);
        titlePanel.setBorder(BorderFactory.createEmptyBorder(0, 100, 0, 0));

        add(titlePanel, BorderLayout.NORTH);
    }

    private JComboBox<String> createGenreComboBox() {
        String[] genres = {
                "lounge",
                "classical",
                "electronic",
                "jazz",
                "pop",
                "hiphop",
                "relaxation",
                "rock",
                "songwriter",
                "world",
                "metal",
                "soundtrack"
        };

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
        button.addActionListener(e -> musicController.getResults(type));
        return button;
    }

    public JButton playButton(String previewUrl) {
        JButton playButton = new JButton("▶️");
        playButton.addActionListener(e -> playPreview(previewUrl));
        return playButton;
    }

    public JButton createLikeButton(String songName) {
        JButton likeButton = new JButton("❤️");
        likeButton.addActionListener(e -> saveLike(songName));
        return likeButton;
    }

    private JButton playlistButton() {
        JButton playlist = new JButton("Playlist");
        playlist.setFont(new Font("Roboto", Font.BOLD, 20));

        playlist.setBounds(470, 440, 150, 40);
        playlist.setFocusable(false);
        playlist.addActionListener(e -> new GUIPlaylist(likedSongs).guiPlaylist());

        return playlist;
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
        topPanel.add(playlistButton());

        JScrollPane scrollPane = new JScrollPane(outputPane);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        scrollPane.setPreferredSize(new Dimension(250, 150));

        centerPanel.add(topPanel, BorderLayout.NORTH);
        centerPanel.add(scrollPane, BorderLayout.CENTER);

        add(centerPanel, BorderLayout.CENTER);
    }

    public void saveLike(String songName) {
        if (!likedSongs.contains(songName)) {
            likedSongs.add(songName);
            JOptionPane.showMessageDialog(this, songName + " has been liked!");
        }else {
            JOptionPane.showMessageDialog(this, songName + " is already in your playlist!");
        }
    }


    public void playPreview(String previewUrl) {
        if (previewUrl == null || previewUrl.isEmpty()) {
            JOptionPane.showMessageDialog(this,"No preview available for this song.");
            return;
        }
        if (player != null) {
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
            JOptionPane.showMessageDialog(this,"Could not play preview.");
        }
    }

    public JComboBox<String> getGenreComboBox() {
        return genreComboBox;
    }

    public JTextPane getOutputPane() {
        return outputPane;
    }

    public JButton CreateplayButton(String previewUrl) {
        return playButton(previewUrl);
    }

    public void showMessage(String message){
        JOptionPane.showMessageDialog(this, message);
    }
}
