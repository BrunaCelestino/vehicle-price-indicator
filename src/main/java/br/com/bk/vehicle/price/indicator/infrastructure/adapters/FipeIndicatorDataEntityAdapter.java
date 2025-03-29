package br.com.bk.vehicle.price.indicator.infrastructure.adapters;

import br.com.bk.vehicle.price.indicator.domain.entities.FipeIndicatorDataEntity;
import br.com.bk.vehicle.price.indicator.domain.models.FipeIndicatorData;

public class FipeIndicatorDataEntityAdapter {

    private FipeIndicatorDataEntityAdapter() {
    }

    public static FipeIndicatorDataEntity from(FipeIndicatorData fipeIndicatorData) {
        return FipeIndicatorDataEntity.builder()
                .fipeValue(fipeIndicatorData.getFipeValue())
                .searchDate(fipeIndicatorData.getSearchDate())
                .fipeNomenclature(fipeIndicatorData.getFipeNomenclature())
                .comparisonIndex(fipeIndicatorData.getComparisonIndex())
                .depreciation(fipeIndicatorData.getDepreciation())
                .build();
    }
}
