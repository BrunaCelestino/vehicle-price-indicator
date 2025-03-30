package br.com.bk.vehicle.price.indicator.infrastructure.controllers;

import br.com.bk.vehicle.price.indicator.application.dtos.VehiclePriceIndicatorDto;
import br.com.bk.vehicle.price.indicator.application.ports.VehiclePriceIndicatorInputPort;
import br.com.bk.vehicle.price.indicator.domain.types.PriceIndicatorType;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/indicators")
public class IndicatorController {

    private final VehiclePriceIndicatorInputPort vehiclePriceIndicatorInputPort;

    public IndicatorController(VehiclePriceIndicatorInputPort vehiclePriceIndicatorInputPort) {
        this.vehiclePriceIndicatorInputPort = vehiclePriceIndicatorInputPort;
    }

    @GetMapping
    public ResponseEntity<List<VehiclePriceIndicatorDto>> getVehicleIndicators(
            @RequestParam("licensePlate") String licensePlate,
            @RequestParam(value = "date", required = false) String date,
            @RequestParam(value = "type", required = false, defaultValue = "ALL") PriceIndicatorType type) {

        List<VehiclePriceIndicatorDto> indicators = vehiclePriceIndicatorInputPort
                .getVehicleIndicators(licensePlate, date, type);

        return new ResponseEntity<>(indicators, HttpStatus.OK);

    }
}


