package com.neopragma.carrental.util;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SetupGsonTest {

    @Test
    public void playWithGson() {
        String jsonString =
            "{ \"meta\": { \"last_updated_at\":\"2023-08-02T23:59:59Z\" }," +
               "\"data\": {" +
                  "\"ADA\":{" +
                  "\"code\":\"ADA\", \"value\":3.3342788788" +
               "}," +
               "\"AOA\":{" +
                  "\"code\":\"AOA\", \"value\":823.0601503138" +
               "}," +
               "\"ARB\":{" +
                  "\"code\":\"ARB\", \"value\":0.8813003623" +
               "}," +
               "\"ARS\":{" +
                  "\"code\":\"ARS\", \"value\":277.3166432108" +
             "}" +
            "}" +
           "}";
        JsonObject rootJson = JsonParser.parseString(jsonString)
                .getAsJsonObject();

        assertTrue(rootJson.isJsonObject());
        JsonObject dataJson = rootJson.get("data").getAsJsonObject();
        JsonObject adaJson = dataJson.get("ADA").getAsJsonObject();
        assertEquals(3.3342788788, adaJson.get("value").getAsDouble(), 0.05);
    }
}
