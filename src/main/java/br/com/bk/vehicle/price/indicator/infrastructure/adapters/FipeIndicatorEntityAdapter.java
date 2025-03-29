package br.com.bk.vehicle.price.indicator.infrastructure.adapters;

import br.com.bk.vehicle.price.indicator.domain.entities.FipeIndicatorEntity;
import br.com.bk.vehicle.price.indicator.domain.models.FipeIndicator;

public class FipeIndicatorEntityAdapter {

    private FipeIndicatorEntityAdapter() {
    }

    public static FipeIndicatorEntity from(FipeIndicator fipeIndicator) {
        return FipeIndicatorEntity.builder()
                .code(fipeIndicator.getCode())
                .values(fipeIndicator.getValues() != null
                        ? fipeIndicator.getValues().stream()
                        .map(FipeIndicatorDataEntityAdapter::from)
                        .toList()
                        : null)
                .build();
    }
}
