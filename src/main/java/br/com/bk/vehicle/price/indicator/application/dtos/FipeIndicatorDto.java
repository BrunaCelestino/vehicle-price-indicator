package br.com.bk.vehicle.price.indicator.application.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class FipeIndicatorDto {

    @JsonProperty("codigoFIPE")
    private String code;

    @JsonProperty("valores")
    private List<FipeIndicatorDataDto> values;
}
