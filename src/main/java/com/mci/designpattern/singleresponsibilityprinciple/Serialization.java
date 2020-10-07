package com.mci.designpattern.singleresponsibilityprinciple;

import java.util.Collections;
import java.util.Map;

/**
    Protocol format: identifier-string;{gson string}
    For example: UEUEUE;{"a":"A","b":"B"}

    Serialization 类实现了一个简单协议的序列化和反序列功能

    Single Responsibility Principle - A class or module should have a single responsibility.
 */
public class Serialization {
    private static final String IDENTIFIER_STRING = "UEUEUE;";
    private Gson gson;

    public Serialization() {
        this.gson = new Gson();
    }

    public String serialize(Map<String, String> object) {
        StringBuilder textBuilder = new StringBuilder();
        textBuilder.append(IDENTIFIER_STRING);
        textBuilder.append(gson.toJson(object));
        return textBuilder.toString();
    }

    public Map<String, String> deserialize(String text) {
        if (!text.startsWith(IDENTIFIER_STRING)) {
            return Collections.emptyMap();
        }
        String gsonStr = text.substring(IDENTIFIER_STRING.length());
        return gson.fromJson(gsonStr, Map.class);
    }
}
