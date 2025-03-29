package br.com.bk.vehicle.price.indicator.domain.models;

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
public class IcarrosIndicator {
    private String icarrosCode;
    private String icarrosValue;
    private String searchMonth;
    private String icarrosNomenclature;
    private String comparisonIndex;
    private String depreciation;
}
