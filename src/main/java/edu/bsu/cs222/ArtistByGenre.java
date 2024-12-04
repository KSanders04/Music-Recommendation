package edu.bsu.cs222;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.io.IOException;


public class ArtistByGenre {

    RequestToken requestToken = new RequestToken();
    ArtistParser artistParser = new ArtistParser();

    public String getArtistByGenre(String genre) throws IOException {
        String accessToken = requestToken.getAccessToken();

        String url = "https://api.jamendo.com/v3.0/tracks/?client_id=" + accessToken +
                "&format=json&limit=200&fuzzytags=" + genre + "&groupby=artist_id";

        HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
        connection.setRequestMethod("GET");

        int responseCode = connection.getResponseCode();

        if (responseCode == HttpURLConnection.HTTP_OK) {
            try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                StringBuilder response = new StringBuilder();
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    response.append(line);
                }

                JSONObject jsonResponse = new JSONObject(response.toString());
                JSONArray results = jsonResponse.optJSONArray("results");

                if (results != null && results.length() > 0) {
                    return artistParser.printArtists(response.toString());
                } else {
                    return "No tracks found for the specified genre.";
                }
            }
        } else {
            return "Failed to fetch tracks with response code: " + responseCode;
        }
    }

}
