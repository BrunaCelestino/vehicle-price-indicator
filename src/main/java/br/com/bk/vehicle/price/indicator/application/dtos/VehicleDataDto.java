package br.com.bk.vehicle.price.indicator.application.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class VehicleDataDto {

    @JsonProperty("codigo")
    private String code;

    @JsonProperty("mensagem")
    private String message;

    @JsonProperty("placa")
    private String licensePlate;

    @JsonProperty("chassi")
    private String chassis;

    @JsonProperty("ufJurisdicao")
    private String jurisdictionState;

    @JsonProperty("nomeMunicipioEmplacamento")
    private String registrationCity;

    @JsonProperty("anoFabricacao")
    private int manufacturingYear;

    @JsonProperty("anoModelo")
    private int modelYear;

    @JsonProperty("marca")
    private String brand;

    @JsonProperty("modelo")
    private String model;

    @JsonProperty("tipoVeiculo")
    private String vehicleType;

    @JsonProperty("tipoCarroceria")
    private String bodyType;

    @JsonProperty("numeroCarroceria")
    private String bodyNumber;

    @JsonProperty("corPredominante")
    private String predominantColor;

    @JsonProperty("combustivel")
    private String fuelType;

    @JsonProperty("potencia")
    private int horsepower;

    @JsonProperty("cilindradas")
    private int displacement;

    @JsonProperty("capacidadePassageiros")
    private int passengerCapacity;

    @JsonProperty("numeroCaixaCambio")
    private String gearboxNumber;

    @JsonProperty("numeroEixos")
    private int numberOfAxles;

    @JsonProperty("numeroMotor")
    private String engineNumber;

    @JsonProperty("dataEmplacamento")
    private String registrationDate;

    @JsonProperty("quantidadeAquisicoes")
    private int numberOfAcquisitions;

    @JsonProperty("dataUltimaAquisicao")
    private String lastAcquisitionDate;

    @JsonProperty("ufUltimaAquisicao")
    private String lastAcquisitionState;

    @JsonProperty("ufAtual")
    private String currentState;

    @JsonProperty("nomeMunicipioUltimaAquisicao")
    private String lastAcquisitionCity;

    @JsonProperty("indicadoresFipe")
    private List<FipeIndicatorDto> fipeIndicators;

    @JsonProperty("indicadoresMolicar")
    private List<MolicarIndicatorDto> molicarIndicators;

    @JsonProperty("indicadoresIcarros")
    private List<IcarrosIndicatorDto> icarrosIndicators;
}


