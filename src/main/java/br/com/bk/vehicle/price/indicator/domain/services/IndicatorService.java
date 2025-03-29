package br.com.bk.vehicle.price.indicator.domain.services;

import br.com.bk.vehicle.price.indicator.application.dtos.ProcessErrorDto;
import br.com.bk.vehicle.price.indicator.application.exceptions.EntityNotFoundException;
import br.com.bk.vehicle.price.indicator.domain.models.VehicleData;
import br.com.bk.vehicle.price.indicator.domain.models.VehiclePriceIndicator;
import br.com.bk.vehicle.price.indicator.domain.types.PriceIndicatorType;
import br.com.bk.vehicle.price.indicator.domain.types.ProcessErrorType;
import br.com.bk.vehicle.price.indicator.infrastructure.repositories.VehicleRepository;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;

@Service
public class IndicatorService {
    private final VehicleRepository vehicleRepository;

    public IndicatorService(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }



    public List<VehiclePriceIndicator> findVehicleByLicensePlate(String licensePlate, String date,
                                                                 PriceIndicatorType type) {

        VehicleData vehicleData = vehicleRepository.findByLicensePlate(licensePlate);

        if (vehicleData == null) {
            throw new EntityNotFoundException(new ProcessErrorDto(ProcessErrorType.VEHICLE_NOT_FOUND, licensePlate));
        }

        List<VehiclePriceIndicator>  vehiclePriceIndicators = getVehiclePriceIndicators(vehicleData, date, type);

        if (vehiclePriceIndicators.isEmpty()) {
            throw new EntityNotFoundException(new ProcessErrorDto(ProcessErrorType.INDICATOR_NOT_FOUND));

        }
        return vehiclePriceIndicators;

    }


    private List<VehiclePriceIndicator> getVehiclePriceIndicators(VehicleData vehicleData,
                                                                  String date,
                                                                  PriceIndicatorType type) {
        Map<String, VehiclePriceIndicator> indicatorMap = new HashMap<>();

        if (shouldAddIcarrosIndicators(type)) {
            addIcarrosIndicators(vehicleData, indicatorMap);
        }

        if (shouldAddFipeIndicators(type)) {
            addFipeIndicators(vehicleData, indicatorMap);
        }

        List<VehiclePriceIndicator> result = new ArrayList<>(indicatorMap.values());

        if (type == PriceIndicatorType.BOTH) {
            result = filterBothIndicators(result);
        }

        return filterByDate(result, date);
    }

    private boolean shouldAddIcarrosIndicators(PriceIndicatorType type) {
        return type == PriceIndicatorType.ALL || type == PriceIndicatorType.ICARROS || type == PriceIndicatorType.BOTH;
    }

    private boolean shouldAddFipeIndicators(PriceIndicatorType type) {
        return type == PriceIndicatorType.ALL || type == PriceIndicatorType.FIPE || type == PriceIndicatorType.BOTH;
    }

    private List<VehiclePriceIndicator> filterBothIndicators(List<VehiclePriceIndicator> result) {
        return result.stream()
                .filter(item -> item.getFipeValue() != null && item.getIcarrosValue() != null)
                .toList();
    }

    private List<VehiclePriceIndicator> filterByDate(List<VehiclePriceIndicator> result, String date) {
        if (date == null) {
            return result;
        }
        return result.stream()
                .filter(item -> item.getDate().equals(date))
                .toList();
    }

    private static void addFipeIndicators(VehicleData vehicleData, Map<String, VehiclePriceIndicator> indicatorMap) {
        vehicleData.getFipeIndicators().stream()
                .flatMap(fipe -> fipe.getValues().stream())
                .forEach(fipeValue -> indicatorMap.merge(
                        fipeValue.getSearchDate(),
                        VehiclePriceIndicator.builder().fipeValue(fipeValue.getFipeValue())
                                .date(fipeValue.getSearchDate()).build(),
                        (existing, newValue) -> {
                            existing.setFipeValue(newValue.getFipeValue());
                            return existing;
                        }));
    }

    private void addIcarrosIndicators(VehicleData vehicleData, Map<String, VehiclePriceIndicator> indicatorMap) {
        vehicleData.getIcarrosIndicators().forEach(item ->
                indicatorMap.put(item.getSearchMonth(), VehiclePriceIndicator.builder()
                        .icarrosValue(item.getIcarrosValue())
                        .date(item.getSearchMonth())
                        .build()));
    }
}
