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
public class FipeIndicatorDataDto {

    @JsonProperty("valorFIPE")
    private String fipeValue;

    @JsonProperty("dataConsulta")
    private String searchDate;

    @JsonProperty("nomenclaturaFIPE")
    private String fipeNomenclature;

    @JsonProperty("indiceComparacao")
    private String comparisonIndex;

    @JsonProperty("desvalorizacao")
    private String depreciation;
}
