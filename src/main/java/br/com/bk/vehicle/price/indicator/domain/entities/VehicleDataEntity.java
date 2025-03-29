package br.com.bk.vehicle.price.indicator.domain.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "VEICULO", uniqueConstraints = @UniqueConstraint(columnNames = "PLACA"))
public class VehicleDataEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "CODIGO")
    private String code;

    @Column(name = "MENSAGEM")
    private String message;

    @Column(name = "PLACA", nullable = false)
    private String licensePlate;

    @Column(name = "CHASSI")
    private String chassis;

    @Column(name = "UF_JURISDICAO")
    private String jurisdictionState;

    @Column(name = "NOME_MUNICIPIO_EMPLACAMENTO")
    private String registrationCity;

    @Column(name = "ANO_FABRICACAO")
    private int manufacturingYear;

    @Column(name = "ANO_MODELO")
    private int modelYear;

    @Column(name = "MARCA")
    private String brand;

    @Column(name = "MODELO")
    private String model;

    @Column(name = "TIPO_VEICULO")
    private String vehicleType;

    @Column(name = "TIPO_CARROCERIA")
    private String bodyType;

    @Column(name = "NUMERO_CARROCERIA")
    private String bodyNumber;

    @Column(name = "COR_PREDOMINANTE")
    private String predominantColor;

    @Column(name = "COMBUSTIVEL")
    private String fuelType;

    @Column(name = "POTENCIA")
    private int horsepower;

    @Column(name = "CILINDRADAS")
    private int displacement;

    @Column(name = "CAPACIDADE_PASSAGEIROS")
    private int passengerCapacity;

    @Column(name = "NUMERO_CAIXA_CAMBIO")
    private String gearboxNumber;

    @Column(name = "NUMERO_EIXOS")
    private int numberOfAxles;

    @Column(name = "NUMERO_MOTOR")
    private String engineNumber;

    @Column(name = "DATA_EMPLACAMENTO")
    private String registrationDate;

    @Column(name = "QUANTIDADE_AQUISICOES")
    private int numberOfAcquisitions;

    @Column(name = "DATA_ULTIMA_AQUISICAO")
    private String lastAcquisitionDate;

    @Column(name = "UF_ULTIMA_AQUISICAO")
    private String lastAcquisitionState;

    @Column(name = "UF_ATUAL")
    private String currentState;

    @Column(name = "NOME_MUNICIPIO_ULTIMA_AQUISICAO")
    private String lastAcquisitionCity;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "VEICULO_ID")
    private List<FipeIndicatorEntity> fipeIndicators;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "VEICULO_ID")
    private List<MolicarIndicatorEntity> molicarIndicators;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "VEICULO_ID")
    private List<IcarrosIndicatorEntity> icarrosIndicators;

}
