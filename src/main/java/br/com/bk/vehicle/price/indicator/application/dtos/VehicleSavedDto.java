package br.com.bk.vehicle.price.indicator.application.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import java.time.Instant;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Schema(description = "Estrutura para retorno do cadastro de veículos na API")
public class VehicleSavedDto {
    @JsonProperty("placa")
    @Schema(description = "Placa do veículo", example = "ABC1D23")
    private String licensePlate;

    @JsonProperty("registradoEm")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "America/Sao_Paulo")
    @Schema(description = "Data de registro do veículo", example = "2023-10-01T12:00:00Z")
    private Instant createdAt;
}
