package br.com.bk.vehicle.price.indicator.infrastructure.adapters;

import br.com.bk.vehicle.price.indicator.domain.entities.MolicarIndicatorEntity;
import br.com.bk.vehicle.price.indicator.domain.models.MolicarIndicator;

public class MolicarIndicatorEntityAdapter {

    private MolicarIndicatorEntityAdapter() {
    }

    public static MolicarIndicatorEntity from(MolicarIndicator molicarIndicator) {
        return MolicarIndicatorEntity.builder()
                .code(molicarIndicator.getCode())
                .description(molicarIndicator.getDescription())
                .build();
    }
}
