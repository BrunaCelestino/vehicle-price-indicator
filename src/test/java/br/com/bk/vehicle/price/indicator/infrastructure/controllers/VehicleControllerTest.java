package br.com.bk.vehicle.price.indicator.infrastructure.controllers;

import br.com.bk.vehicle.price.indicator.application.dtos.VehicleDataDto;
import br.com.bk.vehicle.price.indicator.application.dtos.VehicleSavedDto;
import br.com.bk.vehicle.price.indicator.application.ports.VehicleDataProcessInputPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class VehicleControllerTest {

    @InjectMocks
    private VehicleController vehicleController;

    @Mock
    private VehicleDataProcessInputPort vehicleDataProcessInputPort;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void saveVehicleData_ShouldReturnCreatedStatus() {
        VehicleDataDto vehicleDataDto = new VehicleDataDto();
        VehicleSavedDto vehicleSavedDto = new VehicleSavedDto();
        when(vehicleDataProcessInputPort.processVehicleData(vehicleDataDto)).thenReturn(vehicleSavedDto);

        ResponseEntity<VehicleSavedDto> response = vehicleController.saveVehicleData(vehicleDataDto);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(vehicleSavedDto, response.getBody());
        verify(vehicleDataProcessInputPort, times(1)).processVehicleData(vehicleDataDto);
    }
}
