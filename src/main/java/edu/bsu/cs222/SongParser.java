package edu.bsu.cs222;

import org.json.JSONArray;
import org.json.JSONObject;

public class SongParser {
    public String[][] getSongsWithPreviews(String jsonResponse) {
        JSONObject jsonObject = new JSONObject(jsonResponse);
        JSONArray tracks = jsonObject.getJSONArray("tracks");
        String[][] songData = new String[tracks.length()][2];

        for (int i = 0; i < tracks.length(); i++) {
            JSONObject track = tracks.getJSONObject(i);
            String songName = track.getString("name");
            String previewUrl = track.optString("preview_url", null);

            songData[i][0] = songName;
            songData[i][1] = previewUrl;
        }
        return songData;
    }
}
