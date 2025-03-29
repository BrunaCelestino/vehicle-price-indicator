package br.com.bk.vehicle.price.indicator.application.exceptions;

import br.com.bk.vehicle.price.indicator.application.dtos.ProcessErrorDto;
import lombok.Getter;

@Getter
public class ValidationFailedException extends RuntimeException {
    private final ProcessErrorDto processErrorDto;

    public ValidationFailedException(ProcessErrorDto processErrorDto) {
        super(processErrorDto.getDetails());
        this.processErrorDto = processErrorDto;
    }
}