package edu.bsu.cs222;

import java.io.IOException;

public class RequestToken {

    private String apiKey;

    public RequestToken() {
        this.apiKey = CredentialsLoader.getApiKey();
    }

    public String getAccessToken() throws IOException {
        if (apiKey == null || apiKey.isEmpty()) {
            throw new IOException("API key is missing or invalid");
        }
        return apiKey;
    }
}
