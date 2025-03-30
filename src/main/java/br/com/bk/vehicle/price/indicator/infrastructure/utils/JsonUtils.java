package br.com.bk.vehicle.price.indicator.infrastructure.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;

public class JsonUtils {

    private JsonUtils() {
    }

    public static ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    public static String beautifyJson(Object value) {
        try {
            return OBJECT_MAPPER.writerWithDefaultPrettyPrinter().writeValueAsString(value);
        } catch (Exception e) {
            return null;
        }
    }
}
