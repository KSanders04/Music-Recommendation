package edu.bsu.cs222;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class CredentialsLoader {

    private static String apiKey;

    static {
        try (InputStream input = Thread.currentThread().getContextClassLoader().getResourceAsStream("Secret.txt")) {

            Properties prop = new Properties();
            prop.load(input);
            apiKey = prop.getProperty("apiKey");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getApiKey() {
        return apiKey;
    }
}
