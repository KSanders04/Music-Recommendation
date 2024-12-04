package edu.bsu.cs222;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.io.IOException;
import org.json.JSONObject;

public class ArtistByGenre {

    RequestToken requestToken = new RequestToken(); // Get the API key from CredentialsLoader
    ArtistParser artistParser = new ArtistParser(); // Assuming ArtistParser parses the artist data from JSON response

    // Method to get artists by genre
    public String getArtistByGenre(String genre) throws IOException {
        String accessToken = requestToken.getAccessToken();  // Get the access token (API key)

        String url = "https://api.jamendo.com/v3.0/tracks/?client_id=" + accessToken + "&format=json&limit=all&fuzzytags=lounge+classical+electronic+jazz+pop+hiphop+relaxation+rock+songwriter+world+metal+soundtrack&groupby=artist_id";

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

                System.out.println("Full Response: " + response.toString());

                JSONObject jsonResponse = new JSONObject(response.toString());
                if (jsonResponse.has("results") && jsonResponse.getJSONArray("results").length() > 0) {
                    return artistParser.printArtists(response.toString());
                } else {
                    System.out.println("No tracks found in the response.");
                    return "No tracks found in the response.";
                }
            }
        } else {
            System.out.println("Error: " + responseCode);
            return "Failed to fetch tracks with response code: " + responseCode;
        }


    }
}
