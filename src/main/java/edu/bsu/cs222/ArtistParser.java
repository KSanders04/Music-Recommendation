package edu.bsu.cs222;

import org.json.JSONArray;
import org.json.JSONObject;

public class ArtistParser {

    public String printArtists(String jsonResponse) {
        JSONObject jsonObject = new JSONObject(jsonResponse);  // Convert the raw JSON to a JSONObject
        StringBuilder artistList = new StringBuilder();

        JSONArray results = jsonObject.getJSONArray("results");

        int numberOfArtists = 0;
        for (int i = 0; i < results.length() && numberOfArtists < 5; i++) {
            JSONObject track = results.getJSONObject(i);
            String artistName = track.getString("artist_name");

            artistList.append(artistName).append("\n");
            numberOfArtists++;
        }
        return artistList.toString();
    }
}
