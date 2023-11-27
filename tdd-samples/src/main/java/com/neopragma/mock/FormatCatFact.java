package com.neopragma.mock;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class FormatCatFact {

    public String formattedCatFact(CatFacts theFacts) throws Exception {
        Gson gson = new Gson();
        return gson.toJson(theFacts.justTheFactsMaam());
    }
}
