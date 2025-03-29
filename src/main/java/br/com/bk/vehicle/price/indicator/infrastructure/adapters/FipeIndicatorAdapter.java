package br.com.bk.vehicle.price.indicator.infrastructure.adapters;

import br.com.bk.vehicle.price.indicator.application.dtos.FipeIndicatorDto;
import br.com.bk.vehicle.price.indicator.domain.entities.FipeIndicatorEntity;
import br.com.bk.vehicle.price.indicator.domain.models.FipeIndicator;

public class FipeIndicatorAdapter {

    private FipeIndicatorAdapter() {
    }

    public static FipeIndicator from(FipeIndicatorDto fipeIndicatorDto) {
        return FipeIndicator.builder()
                .code(fipeIndicatorDto.getCode())
                .values(fipeIndicatorDto.getValues() != null
                        ? fipeIndicatorDto.getValues().stream()
                        .map(FipeIndicatorDataAdapter::from)
                        .toList()
                        : null)
                .build();
    }

    public static FipeIndicator from(FipeIndicatorEntity fipeIndicatorDto) {
        return FipeIndicator.builder()
                .code(fipeIndicatorDto.getCode())
                .values(fipeIndicatorDto.getValues() != null
                        ? fipeIndicatorDto.getValues().stream()
                        .map(FipeIndicatorDataAdapter::from)
                        .toList()
                        : null)
                .build();
    }
}
