package br.com.bk.vehicle.price.indicator.infrastructure.adapters;

import br.com.bk.vehicle.price.indicator.domain.entities.IcarrosIndicatorEntity;
import br.com.bk.vehicle.price.indicator.domain.models.IcarrosIndicator;

public class IcarrosIndicatorEntityAdapter {

    private IcarrosIndicatorEntityAdapter() {
    }

    public static IcarrosIndicatorEntity from(IcarrosIndicator icarrosIndicator) {
        return IcarrosIndicatorEntity.builder()
                .icarrosCode(icarrosIndicator.getIcarrosCode())
                .icarrosValue(icarrosIndicator.getIcarrosValue())
                .searchMonth(icarrosIndicator.getSearchMonth())
                .icarrosNomenclature(icarrosIndicator.getIcarrosNomenclature())
                .comparisonIndex(icarrosIndicator.getComparisonIndex())
                .depreciation(icarrosIndicator.getDepreciation())
                .build();
    }
}
