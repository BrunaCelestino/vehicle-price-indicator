package br.com.bk.vehicle.price.indicator.infrastructure.controllers;

import br.com.bk.vehicle.price.indicator.application.dtos.ProcessErrorDto;
import br.com.bk.vehicle.price.indicator.application.dtos.VehicleDataDto;
import br.com.bk.vehicle.price.indicator.application.dtos.VehicleSavedDto;
import br.com.bk.vehicle.price.indicator.application.ports.VehicleDataProcessInputPort;
import br.com.bk.vehicle.price.indicator.infrastructure.logger.LOG;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/vehicles")
@Tag(name = "Vehicle API", description = "Gerencia informações de veículos")
public class VehicleController {

    private final VehicleDataProcessInputPort vehicleDataProcessInputPort;

    public VehicleController(VehicleDataProcessInputPort vehicleDataProcessInputPort) {
        this.vehicleDataProcessInputPort = vehicleDataProcessInputPort;
    }

    @PostMapping
    @Operation(summary = "Cadastrar as informações de veículos.", description = "Retorna a confirmação do cadastro.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Veículo cadastrado com sucesso."),
            @ApiResponse(
                    responseCode = "400",
                    description = "Erro de validação dos dados do veículo.",
                    content = @Content(
                            mediaType = "application/json",
                            array = @ArraySchema(
                                    schema = @Schema(implementation = ProcessErrorDto.class)
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "409",
                    description = "Veículo já cadastrado com a placa informada.",
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
    public ResponseEntity<VehicleSavedDto> saveVehicleData(@RequestBody VehicleDataDto vehicleDataDto) {
        LOG.info("Veículo recebido");
        LOG.info(vehicleDataDto.toString());

        return new ResponseEntity<>(vehicleDataProcessInputPort.processVehicleData(vehicleDataDto),
                HttpStatus.CREATED);
    }
}
