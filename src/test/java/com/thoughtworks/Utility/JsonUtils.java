package com.thoughtworks.Utility;


import com.google.gson.*;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Type;

public class JsonUtils {

    public static JsonObject convertToJsonObject(String json) {
        JsonParser jsonParser = new JsonParser();
        return jsonParser.parse(json).getAsJsonObject();
    }

    public static String convertToJsonString(String jsonFilePath) throws FileNotFoundException {
        JsonParser jsonParser = new JsonParser();
        JsonElement jsonElement = jsonParser.parse(new FileReader(jsonFilePath));
        return jsonElement.toString();
    }

    public static JsonArray convertToJsonArray(String json) {
        JsonParser jsonParser = new JsonParser();
        return jsonParser.parse(json).getAsJsonArray();
    }

    public static Object convertFromJson(String json, Type type){
        return new Gson().fromJson(json ,type);
    }

}
