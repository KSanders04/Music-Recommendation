package edu.bsu.cs222;

import org.junit.jupiter.api.Test;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SecretTest {

    @Test
    public void testIdAndSecretReadCorrectly() {
        Properties properties = new Properties();

        try (BufferedReader reader = new BufferedReader(new FileReader("/Users/ptmarlo/Desktop/cs222/FinalProject-AnthoneeEmar-ChrisSilhavy-KyleSanders/src/main/java/edu/bsu/cs222/secret.txt"))) {
            properties.load(reader);

            String id = properties.getProperty("ID");
            String secret = properties.getProperty("Secret");

            assertEquals("b2885153405844bf9fbf5fb4fbad27bb", id, "ID does not match expected value.");
            assertEquals("e074972f15b844819eaa087ef38092b8", secret, "Secret does not match expected value.");
        } catch (IOException e) {
            throw new RuntimeException("Error reading the secret file", e);
        }
    }
}

