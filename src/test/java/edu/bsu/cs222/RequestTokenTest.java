package edu.bsu.cs222;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RequestTokenTest {

    @Test
    void testGetAccessToken() throws Exception {
        RequestToken requestToken = new RequestToken();

        String token = requestToken.getAccessToken();

        assertNotNull(token, "Access token should not be null");
        assertEquals("b2320882", token, "The access token should match the API key");
    }

}
