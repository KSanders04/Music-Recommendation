package edu.bsu.cs222;

import org.json.JSONArray;
import org.json.JSONObject;

public class TrackParser {
    public void printTrack(String jsonResponse) {
        JSONObject jsonObject = new JSONObject(jsonResponse);
        JSONArray tracks = jsonObject.getJSONArray("tracks");

        if (!tracks.isEmpty()) {
            int numberOfTracks = 0;
            System.out.println("\nTracks:");
            for (int i = 0; i < tracks.length() && numberOfTracks < 5; i++) {
                String songName = tracks.getJSONObject(i).getString("name");
                System.out.println(songName);
                numberOfTracks += 1;
            }
        }else {
            System.out.println("No tracks found for this genre.");
        }
    }
}
