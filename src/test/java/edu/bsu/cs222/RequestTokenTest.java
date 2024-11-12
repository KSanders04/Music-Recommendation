package edu.bsu.cs222;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class RequestTokenTest {

    @Test
    public void testTokenIsReturned() {
        RequestToken requestToken = new RequestToken();

        String token = null;
        try {
            token = requestToken.getAccessToken();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        assertNotNull(token, "Token should not be null");

        assertFalse(token.isEmpty(), "Token should not be empty");
    }
}

