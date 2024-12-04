package edu.bsu.cs222;

import org.json.JSONArray;
import org.json.JSONObject;

public class ArtistParser {

    public String printArtists(String jsonResponse) {
        JSONObject jsonObject = new JSONObject(jsonResponse);
        StringBuilder artistList = new StringBuilder();

        if (!jsonObject.has("results")) {
            return "No artists found in the response.";
        }

        JSONArray results = jsonObject.getJSONArray("results");

        if (results.isEmpty()) {
            return "No artists found in the results.";
        }

        for (int i = 0; i < results.length(); i++) {
            JSONObject track = results.getJSONObject(i);
            String artistName = track.optString("artist_name", "Unknown Artist");

            artistList.append(i + 1).append(". ").append(artistName).append("\n");
        }

        return artistList.toString().trim(); // Removes trailing whitespace
    }
}
