package br.com.bk.vehicle.price.indicator.infrastructure.controllers;

import br.com.bk.vehicle.price.indicator.application.dtos.VehicleDataDto;
import br.com.bk.vehicle.price.indicator.application.dtos.VehicleSavedDto;
import br.com.bk.vehicle.price.indicator.application.ports.VehicleDataProcessInputPort;
import br.com.bk.vehicle.price.indicator.infrastructure.logger.LOG;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/vehicles")
public class VehicleController {

    private final VehicleDataProcessInputPort vehicleDataProcessInputPort;

    public VehicleController(VehicleDataProcessInputPort vehicleDataProcessInputPort) {
        this.vehicleDataProcessInputPort = vehicleDataProcessInputPort;
    }

    @PostMapping
    public ResponseEntity<VehicleSavedDto> saveVehicleData(@RequestBody VehicleDataDto vehicleDataDto) {
        LOG.info("Veículo recebido");
        LOG.info(vehicleDataDto.toString());

        return new ResponseEntity<>(vehicleDataProcessInputPort.processVehicleData(vehicleDataDto),
                HttpStatus.CREATED);
    }
}
