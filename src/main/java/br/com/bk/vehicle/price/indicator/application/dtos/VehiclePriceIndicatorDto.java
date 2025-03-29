package br.com.bk.vehicle.price.indicator.application.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class VehiclePriceIndicatorDto {
    @JsonProperty("valorFIPE")
    private String fipeValue;

    @JsonProperty("valorICARROS")
    private String icarrosValue;

    @JsonProperty("data")
    private String date;

}
