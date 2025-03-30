package br.com.bk.vehicle.price.indicator.infrastructure.controllers;

import br.com.bk.vehicle.price.indicator.application.dtos.ProcessErrorDto;
import br.com.bk.vehicle.price.indicator.application.dtos.VehicleDataDto;
import br.com.bk.vehicle.price.indicator.application.dtos.VehicleSavedDto;
import br.com.bk.vehicle.price.indicator.application.exceptions.EntityAlreadyExistsException;
import br.com.bk.vehicle.price.indicator.application.exceptions.GlobalExceptionHandler;
import br.com.bk.vehicle.price.indicator.application.exceptions.ValidationFailedException;
import br.com.bk.vehicle.price.indicator.application.ports.VehicleDataProcessInputPort;
import br.com.bk.vehicle.price.indicator.domain.types.ProcessErrorType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class VehicleControllerTest {

    private MockMvc mockMvc;

    @Mock
    private VehicleDataProcessInputPort vehicleDataProcessInputPort;

    @InjectMocks
    private VehicleController vehicleController;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders
                .standaloneSetup(vehicleController)
                .setControllerAdvice(new GlobalExceptionHandler())
                .build();
    }

    @Test
    void when_saveVehicleData_ShouldReturnCreatedStatus() throws Exception {
        VehicleSavedDto vehicleSavedDto = new VehicleSavedDto();
        vehicleSavedDto.setLicensePlate("ABC12345");

        when(vehicleDataProcessInputPort.processVehicleData(any(VehicleDataDto.class)))
                .thenReturn(vehicleSavedDto);

        mockMvc.perform(post("/v1/vehicles")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(getJson()))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.placa").value("ABC12345"));
    }

    @Test
    void when_saveVehicleData_vehicleAlreadyExists_ShouldReturnBadRequestStatus() throws Exception {
        when(vehicleDataProcessInputPort.processVehicleData(any(VehicleDataDto.class)))
                .thenThrow(new EntityAlreadyExistsException(new ProcessErrorDto(ProcessErrorType
                        .VEHICLE_ALREADY_REGISTERED)));

        mockMvc.perform(post("/v1/vehicles")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(getJson()))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].title").value(ProcessErrorType.VEHICLE_ALREADY_REGISTERED.name()))
                .andExpect(jsonPath("$[0].code").value(ProcessErrorType.VEHICLE_ALREADY_REGISTERED.getCode()))
                .andExpect(jsonPath("$[0].details").value(ProcessErrorType.VEHICLE_ALREADY_REGISTERED
                        .getDetails()));
    }

    @Test
    void when_saveVehicleData_InvalidDate_ShouldReturnBadRequestStatus() throws Exception {
        when(vehicleDataProcessInputPort.processVehicleData(any(VehicleDataDto.class)))
                .thenThrow(new ValidationFailedException(new ProcessErrorDto(ProcessErrorType
                        .INVALID_DATE_FORMAT)));

        mockMvc.perform(post("/v1/vehicles")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(getJson()))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].title").value(ProcessErrorType.INVALID_DATE_FORMAT.name()))
                .andExpect(jsonPath("$[0].code").value(ProcessErrorType.INVALID_DATE_FORMAT.getCode()))
                .andExpect(jsonPath("$[0].details").value(ProcessErrorType.INVALID_DATE_FORMAT
                        .getDetails()));
    }

    @Test
    void when_saveVehicleData_genericException_ShouldReturnBadRequestStatus() throws Exception {
        when(vehicleDataProcessInputPort.processVehicleData(any(VehicleDataDto.class)))
                .thenThrow(new RuntimeException());

        mockMvc.perform(post("/v1/vehicles")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(getJson()))
                .andExpect(status().isInternalServerError())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].title").value(ProcessErrorType.GENERAL_ERROR.name()))
                .andExpect(jsonPath("$[0].code").value(ProcessErrorType.GENERAL_ERROR.getCode()))
                .andExpect(jsonPath("$[0].details").value(ProcessErrorType.GENERAL_ERROR
                        .getDetails()));
    }

    private String getJson() {
        return """
                {
                  "codigo": "000",
                  "mensagem": "Sucesso",
                  "placa": "FUD5J98",
                  "chassi": "3N1BB7ADXHY200644",
                  "ufJurisdicao": "SP",
                  "nomeMunicipioEmplacamento": "VALINHOS",
                  "anoFabricacao": 2016,
                  "anoModelo": 2017,
                  "marca": "I",
                  "modelo": "NISSAN SENTRA 20SV CVT",
                  "tipoVeiculo": "Automovel",
                  "tipoCarroceria": "NAO APLICAVEL",
                  "numeroCarroceria": "",
                  "corPredominante": "Cinza",
                  "combustivel": "ALCOOL/GASOLINA",
                  "potencia": 140,
                  "cilindradas": 1997,
                  "capacidadePassageiros": 5,
                  "numeroCaixaCambio": "",
                  "numeroEixos": 2,
                  "numeroMotor": "MR20980798H",
                  "dataEmplacamento": "09092016",
                  "quantidadeAquisicoes": 8,
                  "dataUltimaAquisicao": null,
                  "ufUltimaAquisicao": "SP",
                  "ufAtual": "SP",
                  "nomeMunicipioUltimaAquisicao": "MOGI DAS CRUZES",
                  "indicadoresFipe": [
                    {
                      "codigoFIPE": "023123-1",
                      "valores": [
                        {
                          "valorFIPE": "67388.0",
                          "dataConsulta": "03/2025",
                          "nomenclaturaFIPE": "Nissan-Sentra SV 2.0 FlexStart 16V Aut.",
                          "indiceComparacao": "0.50",
                          "desvalorizacao": "true"
                        },
                        {
                          "valorFIPE": "67727.0",
                          "dataConsulta": "02/2025",
                          "nomenclaturaFIPE": "Nissan-Sentra SV 2.0 FlexStart 16V Aut.",
                          "indiceComparacao": "2.04",
                          "desvalorizacao": "false"
                        },
                        {
                          "valorFIPE": "66373.0",
                          "dataConsulta": "01/2025",
                          "nomenclaturaFIPE": "Nissan-Sentra SV 2.0 FlexStart 16V Aut.",
                          "indiceComparacao": "3.50",
                          "desvalorizacao": "false"
                        }
                      ]
                    }
                  ],
                  "indicadoresMolicar": [
                    {
                      "codigo": "03701339-7",
                      "descricao": "NISSAN SENTRA SV N.GERACAO 2.0 16V CVT FLEXSTART 4P Eta./Gas."
                    }
                  ],
                  "indicadoresIcarros": [
                
                  ]
                }
                """;
    }
}
