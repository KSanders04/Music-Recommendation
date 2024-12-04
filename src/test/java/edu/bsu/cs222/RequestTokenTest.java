package edu.bsu.cs222;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RequestTokenTest {

    @Test
    void testGetAccessToken() throws Exception {
        // Arrange
        RequestToken requestToken = new RequestToken();

        // Act
        String token = requestToken.getAccessToken();

        // Assert
        assertNotNull(token, "Access token should not be null");
        assertEquals("b2320882", token, "The access token should match the API key");
    }

}
