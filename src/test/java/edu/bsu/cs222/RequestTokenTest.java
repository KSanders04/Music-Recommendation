package edu.bsu.cs222;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class RequestTokenTest {

    @Test
    public void testTokenReceived() {
        RequestToken requestToken = new RequestToken();
        String token = requestToken.getToken();
        Assertions.assertNull(token, "Token should be null");
    }
}
