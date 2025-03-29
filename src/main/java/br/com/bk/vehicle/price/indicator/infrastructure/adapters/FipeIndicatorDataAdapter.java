package br.com.bk.vehicle.price.indicator.infrastructure.adapters;

import br.com.bk.vehicle.price.indicator.application.dtos.FipeIndicatorDataDto;
import br.com.bk.vehicle.price.indicator.domain.entities.FipeIndicatorDataEntity;
import br.com.bk.vehicle.price.indicator.domain.models.FipeIndicatorData;

public class FipeIndicatorDataAdapter {

    private FipeIndicatorDataAdapter() {
    }

    public static FipeIndicatorData from(FipeIndicatorDataDto fipeIndicatorDataDto) {
        return FipeIndicatorData.builder()
                .fipeValue(fipeIndicatorDataDto.getFipeValue())
                .searchDate(fipeIndicatorDataDto.getSearchDate())
                .fipeNomenclature(fipeIndicatorDataDto.getFipeNomenclature())
                .comparisonIndex(fipeIndicatorDataDto.getComparisonIndex())
                .depreciation(fipeIndicatorDataDto.getDepreciation())
                .build();
    }

    public static FipeIndicatorData from(FipeIndicatorDataEntity fipeIndicatorDataDto) {
        return FipeIndicatorData.builder()
                .fipeValue(fipeIndicatorDataDto.getFipeValue())
                .searchDate(fipeIndicatorDataDto.getSearchDate())
                .fipeNomenclature(fipeIndicatorDataDto.getFipeNomenclature())
                .comparisonIndex(fipeIndicatorDataDto.getComparisonIndex())
                .depreciation(fipeIndicatorDataDto.getDepreciation())
                .build();
    }
}
