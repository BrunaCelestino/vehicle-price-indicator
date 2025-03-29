package br.com.bk.vehicle.price.indicator.application.ports;

import br.com.bk.vehicle.price.indicator.application.dtos.VehiclePriceIndicatorDto;
import br.com.bk.vehicle.price.indicator.domain.types.PriceIndicatorType;
import java.util.List;

public interface VehiclePriceIndicatorInputPort {
    List<VehiclePriceIndicatorDto> getVehicleIndicators(String licensePlate, String date, PriceIndicatorType type);
}
