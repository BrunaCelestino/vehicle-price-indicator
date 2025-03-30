package br.com.bk.vehicle.price.indicator.infrastructure.utils;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtils {

    private JsonUtils() {
    }

    protected static ObjectMapper objectMapper = new ObjectMapper();

    public static String beautifyJson(Object value) {
        try {
            return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(value);
        } catch (Exception e) {
            return null;
        }
    }
}
