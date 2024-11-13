package edu.bsu.cs222;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class ErrorCheck {
    public static String parseErrorMessage(InputStream errorStream) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(errorStream))) {
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }

            JSONObject jsonResponse = new JSONObject(response.toString());
            if (jsonResponse.has("error")) {
                JSONObject errorObject = jsonResponse.getJSONObject("error");
                if (errorObject.has("message")) {
                    return errorObject.getString("message");
                }
            }
        } catch (Exception e) {
            return "Unable to parse error message.";
        }

        return "Unknown error occurred.";
    }
}
