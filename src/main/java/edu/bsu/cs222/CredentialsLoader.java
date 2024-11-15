package edu.bsu.cs222;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class CredentialsLoader {
    private static String id;
    private static String secret;

    static {
        try (FileInputStream input = new FileInputStream("/My Projects/SongRecommendationApp/src/test/resources/Secret.txt")) {
            Properties prop = new Properties();
            prop.load(input);
            id = prop.getProperty("ID");
            secret = prop.getProperty("Secret");
        } catch (IOException e) {
            System.out.println("Error: " + e);
        }
    }

    public static String getId() {
        return id;
    }

    public static String getSecret() {
        return secret;
    }
}

