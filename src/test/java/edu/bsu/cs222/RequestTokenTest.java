package edu.bsu.cs222;

import static org.junit.jupiter.api.Assertions.assertNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class RequestTokenTest {
    private RequestToken requestToken;

    @BeforeEach
    public void setUp() {
        requestToken = new RequestToken();
    }

    @Test
    public void testTokenReceived() {
        String token = requestToken.getToken();
        assertNull(token, "Token should be null");
    }
}
