package edu.bsu.cs222;

import org.json.JSONArray;
import org.json.JSONObject;

public class ArtistParser {

    public String printArtists(String jsonResponse) {
        JSONObject jsonObject = new JSONObject(jsonResponse);
        StringBuilder artistList = new StringBuilder();
        JSONArray tracks = jsonObject.getJSONArray("tracks");

        if (!tracks.isEmpty()) {
            int numberOfArtists = 0;
            System.out.println("\nArtists:");

            for (int trackIndex = 0; trackIndex < tracks.length() && numberOfArtists < 5; trackIndex++) {
                JSONArray artists = tracks.getJSONObject(trackIndex).getJSONArray("artists");
                for (int artistIndex = 0; artistIndex < artists.length() && numberOfArtists < 5; artistIndex++) {
                    String artistName = artists.getJSONObject(artistIndex).getString("name");
                    artistList.append(artistName).append("\n");
                    System.out.println(artistName);
                    numberOfArtists += 1;
                }
            }
        } else {
            System.out.println("No artists found for this genre.");
        }
        return artistList.toString();
    }
/*
    public String printArtistIcon(String jsonResponse){
        JSONObject jsonObject = new JSONObject(jsonResponse);
        StringBuilder artistIcons = new StringBuilder();
        JSONArray tracks = jsonObject.getJSONArray("tracks");

        if (!tracks.isEmpty()){
            int numberOfArtists = 0;

            for (int trackIndex = 0; trackIndex < tracks.length() && numberOfArtists < 5; trackIndex++) {
                JSONArray artists = tracks.getJSONObject(trackIndex).getJSONArray("artists");
                for (int artistIndex = 0; artistIndex < artists.length() && numberOfArtists < 5; artistIndex++){
                    String artistName = artists.getJSONObject(artistIndex).getString("name");
                    String artistIconUrl = artists.getJSONObject(artistIndex).getJSONArray("images").getJSONObject(0).getString("url");

                    artistIconUrl.append(artistName).append(": ").append(artistIconUrl).append("\n");
                    System.out.println(artistName + ": " + artistIconUrl);
                    numberOfArtists += 1;
                }
            }
        } else {
            System.out.println("No artist icons found for this genre.");
        }
        return artistIcons.toString();
    }*/
}