package br.com.bk.vehicle.price.indicator.infrastructure.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OpenApiConfigTest {

    @Test
    void customOpenAPI_shouldContainCorrectInfo() {
        OpenApiConfig openApiConfig = new OpenApiConfig();
        OpenAPI openAPI = openApiConfig.customOpenAPI();
        Info info = openAPI.getInfo();
        assertNotNull(openAPI.getInfo());
        assertEquals("Vehicle Price Indicator API", info.getTitle());
        assertEquals("1.0.0", info.getVersion());
        assertEquals("Project for storing and retrieving vehicle information and price indicators.",
                info.getDescription());
    }
}