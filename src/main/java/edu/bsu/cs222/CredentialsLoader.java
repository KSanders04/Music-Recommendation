package edu.bsu.cs222;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class CredentialsLoader {
    private static String id;
    private static String secret;

    static {
        try (FileInputStream input = new FileInputStream("/Users/ptmarlo/Desktop/cs222/FinalProject-AnthoneeEmar-ChrisSilhavy-KyleSanders/src/main/java/edu/bsu/cs222/Secret.txt")) {
            Properties prop = new Properties();
            prop.load(input);
            id = prop.getProperty("ID");
            secret = prop.getProperty("Secret");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getId() {
        return id;
    }

    public static String getSecret() {
        return secret;
    }
}

