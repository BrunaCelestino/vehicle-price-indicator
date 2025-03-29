package br.com.bk.vehicle.price.indicator.domain.models;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VehicleData {
    private String code;
    private String message;
    private String licensePlate;
    private String chassis;
    private String jurisdictionState;
    private String registrationCity;
    private int manufacturingYear;
    private int modelYear;
    private String brand;
    private String model;
    private String vehicleType;
    private String bodyType;
    private String bodyNumber;
    private String predominantColor;
    private String fuelType;
    private int horsepower;
    private int displacement;
    private int passengerCapacity;
    private String gearboxNumber;
    private int numberOfAxles;
    private String engineNumber;
    private String registrationDate;
    private int numberOfAcquisitions;
    private String lastAcquisitionDate;
    private String lastAcquisitionState;
    private String currentState;
    private String lastAcquisitionCity;
    private List<FipeIndicator> fipeIndicators;
    private List<MolicarIndicator> molicarIndicators;
    private List<IcarrosIndicator> icarrosIndicators;
}
