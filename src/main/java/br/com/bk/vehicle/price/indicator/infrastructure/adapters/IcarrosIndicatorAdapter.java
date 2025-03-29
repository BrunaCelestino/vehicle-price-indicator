package br.com.bk.vehicle.price.indicator.infrastructure.adapters;

import br.com.bk.vehicle.price.indicator.application.dtos.IcarrosIndicatorDto;
import br.com.bk.vehicle.price.indicator.domain.entities.IcarrosIndicatorEntity;
import br.com.bk.vehicle.price.indicator.domain.models.IcarrosIndicator;

public class IcarrosIndicatorAdapter {

    private IcarrosIndicatorAdapter() {
    }

    public static IcarrosIndicator from(IcarrosIndicatorDto icarrosIndicatorDto) {
        return IcarrosIndicator.builder()
                .icarrosCode(icarrosIndicatorDto.getIcarrosCode())
                .icarrosValue(icarrosIndicatorDto.getIcarrosValue())
                .searchMonth(icarrosIndicatorDto.getSearchMonth())
                .icarrosNomenclature(icarrosIndicatorDto.getIcarrosNomenclature())
                .comparisonIndex(icarrosIndicatorDto.getComparisonIndex())
                .depreciation(icarrosIndicatorDto.getDepreciation())
                .build();
    }

    public static IcarrosIndicator from(IcarrosIndicatorEntity icarrosIndicatorDto) {
        return IcarrosIndicator.builder()
                .icarrosCode(icarrosIndicatorDto.getIcarrosCode())
                .icarrosValue(icarrosIndicatorDto.getIcarrosValue())
                .searchMonth(icarrosIndicatorDto.getSearchMonth())
                .icarrosNomenclature(icarrosIndicatorDto.getIcarrosNomenclature())
                .comparisonIndex(icarrosIndicatorDto.getComparisonIndex())
                .depreciation(icarrosIndicatorDto.getDepreciation())
                .build();
    }
}
