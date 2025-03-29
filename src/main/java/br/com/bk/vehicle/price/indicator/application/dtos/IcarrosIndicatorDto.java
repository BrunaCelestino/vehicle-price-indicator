package br.com.bk.vehicle.price.indicator.application.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class IcarrosIndicatorDto {

    @JsonProperty("codigoICARROS")
    private String icarrosCode;

    @JsonProperty("valorICARROS")
    private String icarrosValue;

    @JsonProperty("mesConsulta")
    private String searchMonth;

    @JsonProperty("nomenclaturaICARROS")
    private String icarrosNomenclature;

    @JsonProperty("indiceComparacao")
    private String comparisonIndex;

    @JsonProperty("desvalorizacao")
    private String depreciation;
}
