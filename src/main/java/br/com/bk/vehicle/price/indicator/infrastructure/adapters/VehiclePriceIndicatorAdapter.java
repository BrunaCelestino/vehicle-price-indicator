package br.com.bk.vehicle.price.indicator.infrastructure.adapters;

import br.com.bk.vehicle.price.indicator.application.dtos.VehiclePriceIndicatorDto;
import br.com.bk.vehicle.price.indicator.domain.models.VehiclePriceIndicator;
import java.util.List;

public class VehiclePriceIndicatorAdapter {

    private VehiclePriceIndicatorAdapter() {
    }

    public static List<VehiclePriceIndicatorDto> from(List<VehiclePriceIndicator> vehiclePriceIndicators) {
        return vehiclePriceIndicators.stream()
                .map(vehiclePriceIndicator -> VehiclePriceIndicatorDto
                        .builder()
                        .fipeValue(vehiclePriceIndicator.getFipeValue())
                        .icarrosValue(vehiclePriceIndicator.getIcarrosValue())
                        .date(vehiclePriceIndicator.getDate())
                        .build())
                .toList();
    }
}
