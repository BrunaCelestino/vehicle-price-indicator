package br.com.bk.vehicle.price.indicator.infrastructure.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class JsonUtilsTest {

    @Test
    void when_beautifyJson_shouldThrowError() throws JsonProcessingException {
        ObjectMapper mockObjectMapper = mock(ObjectMapper.class);
        ObjectWriter mockObjectWriter = mock(ObjectWriter.class);

        when(mockObjectMapper.writerWithDefaultPrettyPrinter()).thenReturn(mockObjectWriter);
        when(mockObjectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(any()))
                .thenThrow(new RuntimeException("Simulated IOException"));

        JsonUtils.objectMapper = mockObjectMapper;

        String result = JsonUtils.beautifyJson("{ 'key' : 'value' ");

        assertNull(result);
    }

}