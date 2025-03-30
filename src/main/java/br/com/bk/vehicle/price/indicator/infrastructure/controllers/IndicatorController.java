package br.com.bk.vehicle.price.indicator.infrastructure.controllers;

import br.com.bk.vehicle.price.indicator.application.dtos.ProcessErrorDto;
import br.com.bk.vehicle.price.indicator.application.dtos.VehiclePriceIndicatorDto;
import br.com.bk.vehicle.price.indicator.application.ports.VehiclePriceIndicatorInputPort;
import br.com.bk.vehicle.price.indicator.domain.types.PriceIndicatorType;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/indicators")
@Tag(name = "Indicators API", description = "Gerencia informações de indicadores de preços")
public class IndicatorController {

    private final VehiclePriceIndicatorInputPort vehiclePriceIndicatorInputPort;

    public IndicatorController(VehiclePriceIndicatorInputPort vehiclePriceIndicatorInputPort) {
        this.vehiclePriceIndicatorInputPort = vehiclePriceIndicatorInputPort;
    }

    @GetMapping
    @Operation(summary = "Buscar informações sobre indicadores de preço de veículos.",
            description = "Retorna os indicadores de acordo com os filtros informados.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Indicador localizado com sucesso."),
            @ApiResponse(
                    responseCode = "400",
                    description = "Erro de validação dos filtros informados.",
                    content = @Content(
                            mediaType = "application/json",
                            array = @ArraySchema(
                                    schema = @Schema(implementation = ProcessErrorDto.class)
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Indicador não localizado.",
                    content = @Content(
                            mediaType = "application/json",
                            array = @ArraySchema(
                                    schema = @Schema(implementation = ProcessErrorDto.class)
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Erro no processamento da aplicação.",
                    content = @Content(
                            mediaType = "application/json",
                            array = @ArraySchema(
                                    schema = @Schema(implementation = ProcessErrorDto.class)
                            )
                    )
            )
    })
    public ResponseEntity<List<VehiclePriceIndicatorDto>> getVehicleIndicators(
            @Parameter(description = "Placa do veículo para o qual os indicadores serão buscados.")
            @RequestParam("licensePlate") String licensePlate,
            @Parameter(description = "Data do indicador buscado.", example = "03/2025")
            @RequestParam(value = "date", required = false) String date,
            @Parameter(description = """
                    Tipo do indicador buscado.
                    ALL: Todos os tipos.
                    BOTH: Somente se houver indicadores Fipe e Icarros.
                    FIPE: Somente indicador Fipe.
                    ICARROS: Somente indicador Icarros.
                    """)
            @Schema(description = "Tipo de indicador de preço")
            @RequestParam(value = "type", required = false, defaultValue = "ALL") PriceIndicatorType type) {

        List<VehiclePriceIndicatorDto> indicators = vehiclePriceIndicatorInputPort
                .getVehicleIndicators(licensePlate, date, type);

        return new ResponseEntity<>(indicators, HttpStatus.OK);

    }
}


