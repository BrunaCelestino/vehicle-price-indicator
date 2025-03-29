package br.com.bk.vehicle.price.indicator.application.exceptions;

import br.com.bk.vehicle.price.indicator.application.dtos.ProcessErrorDto;
import lombok.Getter;

@Getter
public class EntityNotFoundException extends RuntimeException {
    private final ProcessErrorDto processErrorDto;

    public EntityNotFoundException(ProcessErrorDto processErrorDto) {
        super(processErrorDto.getDetails());
        this.processErrorDto = processErrorDto;
    }
}
