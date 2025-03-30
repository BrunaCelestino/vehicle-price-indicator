package br.com.bk.vehicle.price.indicator.application.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(description = "Estrutura para retorno da busca de indicadores de preço na API")
public class VehiclePriceIndicatorDto {
    @JsonProperty("valorFIPE")
    @Schema(description = "Valor do veículo segundo a tabela FIPE", example = "50000.00")
    private String fipeValue;

    @JsonProperty("valorICARROS")
    @Schema(description = "Valor do veículo segundo o site Icarros", example = "52000.00")
    private String icarrosValue;

    @JsonProperty("data")
    @Schema(description = "Data de referência do valor", example = "03/2023")
    private String date;

}
