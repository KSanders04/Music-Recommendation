package edu.bsu.cs222;

import org.json.JSONArray;
import org.json.JSONObject;

public class ArtistParser {
    public void printArtists(String jsonResponse) {
        JSONObject jsonObject = new JSONObject(jsonResponse);
        JSONArray tracks = jsonObject.getJSONArray("tracks");

        if (!tracks.isEmpty()) {
            int numberOfArtists = 0;
            System.out.println("\nArtists:");
            for (int i = 0; i < tracks.length() && numberOfArtists < 5; i++) {
                JSONArray artists = tracks.getJSONObject(i).getJSONArray("artists");
                for (int j = 0; j < artists.length() && numberOfArtists < 5; j++) {
                    String artistName = artists.getJSONObject(j).getString("name");
                    System.out.println(artistName);
                    numberOfArtists += 1;
                }
            }
        } else {
            System.out.println("No artists found for this genre.");
        }
    }
}