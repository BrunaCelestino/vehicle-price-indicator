package br.com.bk.vehicle.price.indicator.infrastructure.adapters;

import br.com.bk.vehicle.price.indicator.domain.entities.VehicleDataEntity;
import br.com.bk.vehicle.price.indicator.domain.models.VehicleData;

public class VehicleDataEntityAdapter {

    private VehicleDataEntityAdapter() {
    }

    public static VehicleDataEntity from(VehicleData vehicleData) {
        return VehicleDataEntity.builder()
                .code(vehicleData.getCode())
                .message(vehicleData.getMessage())
                .licensePlate(vehicleData.getLicensePlate())
                .chassis(vehicleData.getChassis())
                .jurisdictionState(vehicleData.getJurisdictionState())
                .registrationCity(vehicleData.getRegistrationCity())
                .manufacturingYear(vehicleData.getManufacturingYear())
                .modelYear(vehicleData.getModelYear())
                .brand(vehicleData.getBrand())
                .model(vehicleData.getModel())
                .vehicleType(vehicleData.getVehicleType())
                .bodyType(vehicleData.getBodyType())
                .bodyNumber(vehicleData.getBodyNumber())
                .predominantColor(vehicleData.getPredominantColor())
                .fuelType(vehicleData.getFuelType())
                .horsepower(vehicleData.getHorsepower())
                .displacement(vehicleData.getDisplacement())
                .passengerCapacity(vehicleData.getPassengerCapacity())
                .gearboxNumber(vehicleData.getGearboxNumber())
                .numberOfAxles(vehicleData.getNumberOfAxles())
                .engineNumber(vehicleData.getEngineNumber())
                .registrationDate(vehicleData.getRegistrationDate())
                .numberOfAcquisitions(vehicleData.getNumberOfAcquisitions())
                .lastAcquisitionDate(vehicleData.getLastAcquisitionDate())
                .lastAcquisitionState(vehicleData.getLastAcquisitionState())
                .currentState(vehicleData.getCurrentState())
                .lastAcquisitionCity(vehicleData.getLastAcquisitionCity())
                .fipeIndicators(vehicleData.getFipeIndicators() != null
                        ? vehicleData.getFipeIndicators().stream()
                        .map(FipeIndicatorEntityAdapter::from)
                        .toList()
                        : null)
                .molicarIndicators(vehicleData.getMolicarIndicators() != null
                        ? vehicleData.getMolicarIndicators().stream()
                        .map(MolicarIndicatorEntityAdapter::from)
                        .toList()
                        : null)
                .icarrosIndicators(vehicleData.getIcarrosIndicators() != null
                        ? vehicleData.getIcarrosIndicators().stream()
                        .map(IcarrosIndicatorEntityAdapter::from)
                        .toList()
                        : null)
                .build();
    }
}
