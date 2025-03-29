package br.com.bk.vehicle.price.indicator.infrastructure.adapters;

import br.com.bk.vehicle.price.indicator.application.dtos.VehicleDataDto;
import br.com.bk.vehicle.price.indicator.domain.entities.VehicleDataEntity;
import br.com.bk.vehicle.price.indicator.domain.models.VehicleData;

public class VehicleDataAdapter {

    private VehicleDataAdapter() {
    }

    public static VehicleData from(VehicleDataDto vehicleDataDto) {
        return VehicleData.builder()
                .code(vehicleDataDto.getCode())
                .message(vehicleDataDto.getMessage())
                .licensePlate(vehicleDataDto.getLicensePlate())
                .chassis(vehicleDataDto.getChassis())
                .jurisdictionState(vehicleDataDto.getJurisdictionState())
                .registrationCity(vehicleDataDto.getRegistrationCity())
                .manufacturingYear(vehicleDataDto.getManufacturingYear())
                .modelYear(vehicleDataDto.getModelYear())
                .brand(vehicleDataDto.getBrand())
                .model(vehicleDataDto.getModel())
                .vehicleType(vehicleDataDto.getVehicleType())
                .bodyType(vehicleDataDto.getBodyType())
                .bodyNumber(vehicleDataDto.getBodyNumber())
                .predominantColor(vehicleDataDto.getPredominantColor())
                .fuelType(vehicleDataDto.getFuelType())
                .horsepower(vehicleDataDto.getHorsepower())
                .displacement(vehicleDataDto.getDisplacement())
                .passengerCapacity(vehicleDataDto.getPassengerCapacity())
                .gearboxNumber(vehicleDataDto.getGearboxNumber())
                .numberOfAxles(vehicleDataDto.getNumberOfAxles())
                .engineNumber(vehicleDataDto.getEngineNumber())
                .registrationDate(vehicleDataDto.getRegistrationDate())
                .numberOfAcquisitions(vehicleDataDto.getNumberOfAcquisitions())
                .lastAcquisitionDate(vehicleDataDto.getLastAcquisitionDate())
                .lastAcquisitionState(vehicleDataDto.getLastAcquisitionState())
                .currentState(vehicleDataDto.getCurrentState())
                .lastAcquisitionCity(vehicleDataDto.getLastAcquisitionCity())
                .fipeIndicators(vehicleDataDto.getFipeIndicators() != null
                        ? vehicleDataDto.getFipeIndicators().stream()
                        .map(FipeIndicatorAdapter::from)
                        .toList()
                        : null)
                .molicarIndicators(vehicleDataDto.getMolicarIndicators() != null
                        ? vehicleDataDto.getMolicarIndicators().stream()
                        .map(MolicarIndicatorAdapter::from)
                        .toList()
                        : null)
                .icarrosIndicators(vehicleDataDto.getIcarrosIndicators() != null
                        ? vehicleDataDto.getIcarrosIndicators().stream()
                        .map(IcarrosIndicatorAdapter::from)
                        .toList()
                        : null)
                .build();
    }

    public static VehicleData from(VehicleDataEntity vehicleDataDto) {
        return VehicleData.builder()
                .code(vehicleDataDto.getCode())
                .message(vehicleDataDto.getMessage())
                .licensePlate(vehicleDataDto.getLicensePlate())
                .chassis(vehicleDataDto.getChassis())
                .jurisdictionState(vehicleDataDto.getJurisdictionState())
                .registrationCity(vehicleDataDto.getRegistrationCity())
                .manufacturingYear(vehicleDataDto.getManufacturingYear())
                .modelYear(vehicleDataDto.getModelYear())
                .brand(vehicleDataDto.getBrand())
                .model(vehicleDataDto.getModel())
                .vehicleType(vehicleDataDto.getVehicleType())
                .bodyType(vehicleDataDto.getBodyType())
                .bodyNumber(vehicleDataDto.getBodyNumber())
                .predominantColor(vehicleDataDto.getPredominantColor())
                .fuelType(vehicleDataDto.getFuelType())
                .horsepower(vehicleDataDto.getHorsepower())
                .displacement(vehicleDataDto.getDisplacement())
                .passengerCapacity(vehicleDataDto.getPassengerCapacity())
                .gearboxNumber(vehicleDataDto.getGearboxNumber())
                .numberOfAxles(vehicleDataDto.getNumberOfAxles())
                .engineNumber(vehicleDataDto.getEngineNumber())
                .registrationDate(vehicleDataDto.getRegistrationDate())
                .numberOfAcquisitions(vehicleDataDto.getNumberOfAcquisitions())
                .lastAcquisitionDate(vehicleDataDto.getLastAcquisitionDate())
                .lastAcquisitionState(vehicleDataDto.getLastAcquisitionState())
                .currentState(vehicleDataDto.getCurrentState())
                .lastAcquisitionCity(vehicleDataDto.getLastAcquisitionCity())
                .fipeIndicators(vehicleDataDto.getFipeIndicators() != null
                        ? vehicleDataDto.getFipeIndicators().stream()
                        .map(FipeIndicatorAdapter::from)
                        .toList()
                        : null)
                .molicarIndicators(vehicleDataDto.getMolicarIndicators() != null
                        ? vehicleDataDto.getMolicarIndicators().stream()
                        .map(MolicarIndicatorAdapter::from)
                        .toList()
                        : null)
                .icarrosIndicators(vehicleDataDto.getIcarrosIndicators() != null
                        ? vehicleDataDto.getIcarrosIndicators().stream()
                        .map(IcarrosIndicatorAdapter::from)
                        .toList()
                        : null)
                .build();
    }
}
