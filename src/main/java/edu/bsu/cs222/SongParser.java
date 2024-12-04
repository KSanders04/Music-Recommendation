package edu.bsu.cs222;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SongParser {


    public String[][] getSongNamesWithPreviews(String jsonResponse) {
        JSONObject jsonObject = new JSONObject(jsonResponse);

        if (!jsonObject.has("results")) {
            return new String[0][0];
        }

        JSONArray results = jsonObject.getJSONArray("results");

        if (results.isEmpty()) {
            return new String[0][0];
        }


        List<String[]> songs = new ArrayList<>();

        for (int i = 0; i < results.length(); i++) {
            JSONObject track = results.getJSONObject(i);
            String songName = track.optString("name", "Unknown Name");
            String previewUrl = track.optString("audio", null);

            if (previewUrl != null) {
                songs.add(new String[]{songName, previewUrl});
            }
        }

        Collections.shuffle(songs);
        return songs.subList(0, Math.min(5, songs.size())).toArray(new String[0][0]);
    }
}
