package br.com.bk.vehicle.price.indicator.domain.types;

import lombok.Getter;

@Getter
public enum ProcessErrorType {

    GENERAL_ERROR("CPI0001", "Erro no processamento da aplicação."),
    VEHICLE_ALREADY_REGISTERED("CPI0002", "Não foi possível salvar veículo. Placa já cadastrada."),
    REQUIRED_INFORMATION_MISSING("CPI0003", "O campo '%s' é obrigatório."),
    INVALID_PARAMETER_VALUE("CPI0004", "O valor do campo '%s' é inválido."),
    INVALID_DATE_FORMAT("CPI0005", "A data deve seguir o formato MM/YYYY."),
    VEHICLE_NOT_FOUND("CPI0006", "Nenhum veículo foi encontrado com a placa '%s'."),
    INDICATOR_NOT_FOUND("CPI0006", "Nenhum indicador foi encontrado para os critérios informados.");



    private final String code;
    private final String details;

    ProcessErrorType(String code, String details) {
        this.code = code;
        this.details = details;
    }
}
