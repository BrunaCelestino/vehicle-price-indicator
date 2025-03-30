package br.com.bk.vehicle.price.indicator.application.exceptions;

import br.com.bk.vehicle.price.indicator.application.dtos.ProcessErrorDto;
import lombok.Getter;

@Getter
public class EntityAlreadyExistsException extends RuntimeException {
    private final transient ProcessErrorDto processErrorDto;

    public EntityAlreadyExistsException(ProcessErrorDto processErrorDto) {
        super(processErrorDto.getDetails());
        this.processErrorDto = processErrorDto;
    }
}
