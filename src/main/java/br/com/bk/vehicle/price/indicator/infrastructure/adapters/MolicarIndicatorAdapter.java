package br.com.bk.vehicle.price.indicator.infrastructure.adapters;

import br.com.bk.vehicle.price.indicator.application.dtos.MolicarIndicatorDto;
import br.com.bk.vehicle.price.indicator.domain.entities.MolicarIndicatorEntity;
import br.com.bk.vehicle.price.indicator.domain.models.MolicarIndicator;

public class MolicarIndicatorAdapter {

    private MolicarIndicatorAdapter() {
    }

    public static MolicarIndicator from(MolicarIndicatorDto molicarIndicatorDto) {
        return MolicarIndicator.builder()
                .code(molicarIndicatorDto.getCode())
                .description(molicarIndicatorDto.getDescription())
                .build();
    }

    public static MolicarIndicator from(MolicarIndicatorEntity molicarIndicatorDto) {
        return MolicarIndicator.builder()
                .code(molicarIndicatorDto.getCode())
                .description(molicarIndicatorDto.getDescription())
                .build();
    }
}
