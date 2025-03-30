package br.com.bk.vehicle.price.indicator.application.dtos;

import br.com.bk.vehicle.price.indicator.domain.types.ProcessErrorType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "Estrutura para retorno de erro da API")
public class ProcessErrorDto {
    @Schema(description = "Título do erro", example = "GENERAL_ERROR")
    private String title;

    @Schema(description = "Código do erro", example = "CPI0001")
    private String code;

    @Schema(description = "Detalhes do erro", example = "Erro no processamento da aplicação.")
    private String details;

    public ProcessErrorDto(ProcessErrorType error, Object... args) {
        this.title = error.name();
        this.code = error.getCode();
        this.details = String.format(error.getDetails(), args);
    }
}
