package edu.bsu.cs222;

import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SecretTest {

    @Test
    public void testIdAndSecretReadCorrectly() {
        Properties properties = new Properties();

        try (InputStream reader = Thread.currentThread().getContextClassLoader().getResourceAsStream("Secret.txt")) {
            properties.load(reader);

            String id = properties.getProperty("ID");
            String secret = properties.getProperty("apiKey");

            assertEquals(null, id, "ID does not match expected value.");
            assertEquals("b2320882", secret, "Secret does not match expected value.");
        } catch (IOException e) {
            throw new RuntimeException("Error reading the secret file", e);
        }
    }
}

