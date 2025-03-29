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
public class MolicarIndicatorDto {

    @JsonProperty("codigo")
    private String code;

    @JsonProperty("descricao")
    private String description;

}
