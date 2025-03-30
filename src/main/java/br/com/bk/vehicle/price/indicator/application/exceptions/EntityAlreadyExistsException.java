package br.com.bk.vehicle.price.indicator.application.exceptions;

import br.com.bk.vehicle.price.indicator.application.dtos.ProcessErrorDto;
import lombok.Getter;
import lombok.ToString;

@Getter
public class EntityAlreadyExistsException extends RuntimeException {
    private final ProcessErrorDto processErrorDto;

    public EntityAlreadyExistsException(ProcessErrorDto processErrorDto) {
        super(processErrorDto.getDetails());
        this.processErrorDto = processErrorDto;
    }
}
