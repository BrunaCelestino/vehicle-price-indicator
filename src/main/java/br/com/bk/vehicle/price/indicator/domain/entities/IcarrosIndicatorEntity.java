package br.com.bk.vehicle.price.indicator.domain.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "ICARROS_INDICATOR")
public class IcarrosIndicatorEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "CODIGO_ICARROS")
    private String icarrosCode;

    @Column(name = "VALOR_ICARROS")
    private String icarrosValue;

    @Column(name = "MES_CONSULTA")
    private String searchMonth;

    @Column(name = "NOMENCLATURA_ICARROS")
    private String icarrosNomenclature;

    @Column(name = "INDICE_COMPARACAO")
    private String comparisonIndex;

    @Column(name = "DESVALORIZACAO")
    private String depreciation;
}
