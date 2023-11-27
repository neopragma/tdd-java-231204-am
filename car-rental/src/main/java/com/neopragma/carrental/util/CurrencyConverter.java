package com.neopragma.carrental.util;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.javamoney.moneta.Money;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;

public class CurrencyConverter {

    public Money convert(Money amount, String sourceCurrencyCode, String targetCurrencyCode)
        throws IOException
    {
        StringBuilder route = new StringBuilder();
        route.append(Config.valueOf("currency.conversion.endpoint"));
        route.append(Config.valueOf("currency.conversion.api.key"));
        route.append("&base_currency=");
        route.append(sourceCurrencyCode);
        route.append("&currencies=");
        route.append(targetCurrencyCode);

        URL url = URI.create(route.toString()).toURL();
        HttpURLConnection request = (HttpURLConnection) url.openConnection();
        request.connect();

        JsonObject root = (JsonObject) JsonParser.parseReader(
                new InputStreamReader((InputStream) request
                        .getContent()));
        JsonObject dataJson = root.get("data").getAsJsonObject();
        JsonObject adaJson = dataJson.get("ADA").getAsJsonObject();
        double conversionFactor = adaJson.get("value").getAsDouble();
        return amount.multiply(conversionFactor);

    }
}
