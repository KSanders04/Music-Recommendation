package edu.bsu.cs222;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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


        List<String> artistNames = new ArrayList<>();
        for (int i = 0; i < results.length(); i++) {
            JSONObject track = results.getJSONObject(i);
            String artistName = track.optString("artist_name", "Unknown Artist");
            artistNames.add(artistName);
        }


        Collections.shuffle(artistNames);
        List<String> randomArtists = artistNames.subList(0, Math.min(5, artistNames.size()));


        for (int i = 0; i < randomArtists.size(); i++) {
            artistList.append(i + 1).append(". ").append(randomArtists.get(i)).append("\n");
        }

        return artistList.toString().trim();
    }
}
