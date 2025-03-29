package br.com.bk.vehicle.price.indicator.application.ports;

import br.com.bk.vehicle.price.indicator.application.dtos.VehicleDataDto;
import br.com.bk.vehicle.price.indicator.application.dtos.VehicleSavedDto;

public interface VehicleDataProcessInputPort {
    VehicleSavedDto processVehicleData(VehicleDataDto carDataDto);
}
