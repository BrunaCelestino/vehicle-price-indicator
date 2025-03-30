package br.com.bk.vehicle.price.indicator.infrastructure.controllers;

import br.com.bk.vehicle.price.indicator.application.dtos.ProcessErrorDto;
import br.com.bk.vehicle.price.indicator.application.dtos.VehiclePriceIndicatorDto;
import br.com.bk.vehicle.price.indicator.application.exceptions.EntityNotFoundException;
import br.com.bk.vehicle.price.indicator.application.exceptions.GlobalExceptionHandler;
import br.com.bk.vehicle.price.indicator.application.ports.VehiclePriceIndicatorInputPort;
import br.com.bk.vehicle.price.indicator.domain.types.ProcessErrorType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class IndicatorControllerTest {

    private MockMvc mockMvc;

    @Mock
    private VehiclePriceIndicatorInputPort vehiclePriceIndicatorInputPort;

    @InjectMocks
    private IndicatorController indicatorController;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders
                .standaloneSetup(indicatorController)
                .setControllerAdvice(new GlobalExceptionHandler())
                .build();
    }

    @Test
    void when_getVehicleIndicators_ShouldReturnOkStatus() throws Exception {
        VehiclePriceIndicatorDto vehiclePriceIndicatorDto = new VehiclePriceIndicatorDto();
        vehiclePriceIndicatorDto.setDate("03/2025");
        vehiclePriceIndicatorDto.setFipeValue("1234");
        vehiclePriceIndicatorDto.setIcarrosValue("3453");

        when(vehiclePriceIndicatorInputPort.getVehicleIndicators(any(), any(), any()))
                .thenReturn(List.of(vehiclePriceIndicatorDto));

        mockMvc.perform(get("/v1/indicators")
                        .param("licensePlate", "abc"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].valorFIPE").value("1234"))
                .andExpect(jsonPath("$[0].valorICARROS").value("3453"))
                .andExpect(jsonPath("$[0].data").value("03/2025"));
    }

    @Test
    void when_getVehicleIndicators_indicatorNotFound_ShouldReturnNotFoundStatus() throws Exception {
        when(vehiclePriceIndicatorInputPort.getVehicleIndicators(any(), any(), any()))
                .thenThrow(new EntityNotFoundException(new ProcessErrorDto(ProcessErrorType.INDICATOR_NOT_FOUND)));

        mockMvc.perform(get("/v1/indicators")
                        .param("licensePlate", "abc"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].title").value(ProcessErrorType.INDICATOR_NOT_FOUND.name()))
                .andExpect(jsonPath("$[0].code").value(ProcessErrorType.INDICATOR_NOT_FOUND.getCode()))
                .andExpect(jsonPath("$[0].details").value(ProcessErrorType.INDICATOR_NOT_FOUND
                        .getDetails()));
    }

    @Test
    void when_getVehicleIndicators_parameterMissing_ShouldReturnBadRequestStatus() throws Exception {

        mockMvc.perform(get("/v1/indicators"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].title").value(ProcessErrorType.REQUIRED_INFORMATION_MISSING.name()))
                .andExpect(jsonPath("$[0].code").value(ProcessErrorType.REQUIRED_INFORMATION_MISSING.getCode()))
                .andExpect(jsonPath("$[0].details").value(String.format(ProcessErrorType.REQUIRED_INFORMATION_MISSING
                        .getDetails(), "licensePlate")));
    }

    @Test
    void when_getVehicleIndicators_typeMismatch_ShouldReturnBadRequestStatus() throws Exception {

        mockMvc.perform(get("/v1/indicators")
                        .param("licensePlate", "abc")
                        .param("type", "abc"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].title").value(ProcessErrorType.INVALID_PARAMETER_VALUE.name()))
                .andExpect(jsonPath("$[0].code").value(ProcessErrorType.INVALID_PARAMETER_VALUE.getCode()))
                .andExpect(jsonPath("$[0].details").value(String.format(ProcessErrorType.INVALID_PARAMETER_VALUE
                        .getDetails(), "type")));
    }
}