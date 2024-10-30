package edu.bsu.cs222;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Base64;


public class RequestToken {

    String clientID = "dd6b2daa511b43089bf71651eaf0ca74";
    String clientSecret = "4bfb2902292045f7815ef909cab7984d";
    String tokenURL = "https://accounts.spotify.com/api/token";
    String accessToken;

    public String getAccessToken() throws Exception{
        if (accessToken == null || accessToken.isEmpty()){
            fetchAccessToken();
        }
        return accessToken;
    }
    private void fetchAccessToken() throws IOException {
        String authorization = clientID + ":" + clientSecret;
        String encodedAuthorization = Base64.getEncoder().encodeToString(authorization.getBytes());

        URL url = new URL(tokenURL);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Authorization", "Basic " + encodedAuthorization);
        connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        connection.setDoOutput(true);

        String body = "grant_type=client_credentials";
        try (OutputStream outputStreams = connection.getOutputStream()) {
            outputStreams.write(body.getBytes());
            outputStreams.flush();
        }
        int responseCode = connection.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK){
            try(BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()))){
                StringBuilder response = new StringBuilder();
                String line;
                while ((line = bufferedReader.readLine()) != null){
                    response.append(line);
                }
                accessToken = parseAccessToken(response.toString());
            }
        }else{
            throw new RuntimeException("Failed to fetch access token: " + connection.getResponseMessage());
        }
    }
    private String parseAccessToken(String response){
        if (response.contains("access_token")){
            return response.substring(response.indexOf("access_token") + 15, response.indexOf("token_type") - 3);
        }
        return null;
    }
}