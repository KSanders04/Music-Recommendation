package edu.bsu.cs222;

import org.json.JSONArray;
import org.json.JSONObject;

public class SongParser {
    public void printSong(String jsonResponse) {
        JSONObject jsonObject = new JSONObject(jsonResponse);
        JSONArray songs = jsonObject.getJSONArray("tracks");

        if (!songs.isEmpty()) {
            int numberOfSongs = 0;
            System.out.println("\nSongs:");
            for (int i = 0; i < songs.length() && numberOfSongs < 5; i++) {
                String songName = songs.getJSONObject(i).getString("name");
                System.out.println(songName);
                numberOfSongs += 1;
            }
        }else {
            System.out.println("No songs found for this genre.");
        }
    }
}
