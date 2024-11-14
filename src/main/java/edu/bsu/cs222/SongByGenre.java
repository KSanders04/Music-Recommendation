package edu.bsu.cs222;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@SuppressWarnings("ALL")
public class SongByGenre {

    private final RequestToken requestToken = new RequestToken();
    private final SongParser songParser = new SongParser();

    public String[][] getSongByGenreWithPreviews(String genre) throws Exception {
        String accessToken = requestToken.getAccessToken();
        String url = "https://api.spotify.com/v1/recommendations?limit=5&seed_genres=" + genre + "&min_popularity=20";

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
                return songParser.getSongsWithPreviews(response.toString());
            }
        }
        return new String[0][0];
    }
}
