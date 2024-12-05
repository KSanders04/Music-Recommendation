package edu.bsu.cs222;

import javax.swing.*;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

public class MusicController {
    private final GUIMenu view;
    private final ArtistByGenre artistByGenre;
    private final SongByGenre songByGenre;

    public MusicController(GUIMenu view, ArtistByGenre artistByGenre, SongByGenre songByGenre) {
        this.view = view;
        this.artistByGenre = artistByGenre;
        this.songByGenre = songByGenre;
    }

    public void getResults(String type) {
        String genre = (String) view.getGenreComboBox().getSelectedItem();
        view.getOutputPane().setText("");

        try {
            StyledDocument doc = view.getOutputPane().getStyledDocument();

            SimpleAttributeSet boldStyle = new SimpleAttributeSet();
            StyleConstants.setBold(boldStyle, true);
            StyleConstants.setFontSize(boldStyle, 40);

            SimpleAttributeSet normalStyle = new SimpleAttributeSet();
            StyleConstants.setFontSize(normalStyle, 20);

            switch (type) {
                case "artist":
                    doc.insertString(doc.getLength(), "Artists:\n\n", boldStyle);
                    doc.insertString(doc.getLength(), artistByGenre.getArtistByGenre(genre) + "\n", normalStyle);
                    break;

                case "song":
                    doc.insertString(doc.getLength(), "Songs:\n", boldStyle);
                    String[][] songs = songByGenre.getSongByGenreWithPreviews(genre);
                    for (String[] song : songs) {
                        String songName = song[0];
                        String previewUrl = song[1];

                        doc.insertString(doc.getLength(), "\n" + songName + "   ", normalStyle);

                        JButton playButton = view.CreateplayButton(previewUrl);
                        view.getOutputPane().insertComponent(playButton);

                        JButton likeButton = view.createLikeButton(songName);
                        view.getOutputPane().insertComponent(likeButton);
                    }
                    break;

                case "both":
                    doc.insertString(doc.getLength(), "Artists:\n", boldStyle);
                    doc.insertString(doc.getLength(), artistByGenre.getArtistByGenre(genre) + "\n", normalStyle);

                    doc.insertString(doc.getLength(), "Songs:\n", boldStyle);
                    songs = songByGenre.getSongByGenreWithPreviews(genre);
                    for (String[] song : songs) {
                        String songName = song[0];
                        String previewUrl = song[1];

                        doc.insertString(doc.getLength(), "\n" + songName + "   ", normalStyle);

                        JButton playButton = view.CreateplayButton(previewUrl);
                        view.getOutputPane().insertComponent(playButton);

                        JButton likeButton = view.createLikeButton(songName);
                        view.getOutputPane().insertComponent(likeButton);
                    }
                    break;
            }
        } catch (Exception e) {
            view.showMessage("Error: " + e);
        }
    }
}


