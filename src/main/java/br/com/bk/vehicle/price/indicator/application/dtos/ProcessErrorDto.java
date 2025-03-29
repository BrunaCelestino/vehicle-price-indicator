package br.com.bk.vehicle.price.indicator.application.dtos;

import br.com.bk.vehicle.price.indicator.domain.types.ProcessErrorType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProcessErrorDto {
    private String title;
    private String code;
    private String details;

    public ProcessErrorDto(ProcessErrorType error, Object... args) {
        this.title = error.name();
        this.code = error.getCode();
        this.details = String.format(error.getDetails(), args);
    }
}
