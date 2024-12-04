package edu.bsu.cs222;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class SongByGenre {

    private final RequestToken requestToken = new RequestToken();
    private final SongParser songParser = new SongParser();

    public String[][] getSongByGenreWithPreviews(String genre) throws Exception {
        String accessToken = requestToken.getAccessToken();
        String url = "https://api.jamendo.com/v3.0/tracks/?client_id=b2320882&format=json&limit=200&order=popularity_month_desc&fuzzytags=" + genre;

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

                return songParser.getSongNamesWithPreviews(response.toString());
            }
        }
        return new String[0][0];
    }


}
