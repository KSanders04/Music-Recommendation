package edu.bsu.cs222;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.io.IOException;
import java.util.Scanner;

import org.json.JSONObject;

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
                if (jsonResponse.has("results") && jsonResponse.getJSONArray("results").length() > 0) {
                    return artistParser.printArtists(response.toString());
                } else {
                    System.out.println("No tracks found for the specified genre.");
                    return "No tracks found for the specified genre.";
                }
            }
        } else {
            System.out.println("Error: " + responseCode);
            return "Failed to fetch tracks with response code: " + responseCode;
        }
    }



    public static void main(String[] args) {
        ArtistByGenre artistByGenre = new ArtistByGenre();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter a genre (e.g., jazz, pop, rock, etc.):");
        String genre = scanner.nextLine();

        try {
            String result = artistByGenre.getArtistByGenre(genre);
            System.out.println("Artists found:");
            System.out.println(result);
        } catch (IOException e) {
            System.err.println("An error occurred while fetching artists: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }

}
