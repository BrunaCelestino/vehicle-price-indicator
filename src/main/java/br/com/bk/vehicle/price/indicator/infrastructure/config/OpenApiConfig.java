package br.com.bk.vehicle.price.indicator.infrastructure.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Vehicle Price Indicator API")
                        .version("1.0.0")
                        .description("Project for storing and retrieving vehicle information and price indicators."));
    }
}
