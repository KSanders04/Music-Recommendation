package edu.bsu.cs222;

import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class SecretKeyTest {

    @Test
    void testIfKeyIsPresent() throws Exception {
        InputStream input = Thread.currentThread().getContextClassLoader().getResourceAsStream("Secret.txt");

        String content = new BufferedReader(new InputStreamReader(input))
                .lines()
                .collect(Collectors.joining("\n"))
                .trim();

        boolean hasKey = content.startsWith("apiKey=");
        if (hasKey) {
            String keyValue = content.substring("apiKey=".length());
            hasKey = keyValue.matches("[a-zA-Z0-9]+");
        }


        assertTrue(hasKey, "The file does not contain a valid API key format.");
    }
}
