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
@Table(name = "FIPE_INDICATOR_DATA")
public class FipeIndicatorDataEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "VALOR_FIPE")
    private String fipeValue;

    @Column(name = "DATA_CONSULTA")
    private String searchDate;

    @Column(name = "NOMENCLATURA_FIPE")
    private String fipeNomenclature;

    @Column(name = "INDICE_COMPARACAO")
    private String comparisonIndex;

    @Column(name = "DESVALORIZACAO")
    private String depreciation;
}