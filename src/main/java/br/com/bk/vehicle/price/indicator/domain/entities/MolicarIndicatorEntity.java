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
@Table(name = "MOLICAR_INDICATOR")
public class MolicarIndicatorEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "CODIGO")
    private String code;

    @Column(name = "DESCRICAO")
    private String description;
}