package edu.bsu.cs222;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class SongByGenre {

    RequestToken requestToken = new RequestToken();
    SongParser songParser = new SongParser();

    public String getSongByGenre(String genre) throws Exception {
        String accessToken = requestToken.getAccessToken();
        String url = "https://api.spotify.com/v1/recommendations?limit=5&seed_genres=" + genre + "&min_popularity=45";
        HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Authorization", "Bearer " + accessToken);

        int responseCode = connection.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                StringBuilder response = new StringBuilder();
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    response.append(line);
                }
                return songParser.printSong(response.toString());
            }
        } else {
            System.out.println("Song request failed");
        }
        return accessToken;
    }
}
