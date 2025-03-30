package br.com.bk.vehicle.price.indicator.application.usecases;

import br.com.bk.vehicle.price.indicator.application.dtos.ProcessErrorDto;
import br.com.bk.vehicle.price.indicator.application.dtos.VehiclePriceIndicatorDto;
import br.com.bk.vehicle.price.indicator.application.exceptions.ValidationFailedException;
import br.com.bk.vehicle.price.indicator.application.ports.VehiclePriceIndicatorInputPort;
import br.com.bk.vehicle.price.indicator.domain.services.IndicatorService;
import br.com.bk.vehicle.price.indicator.domain.types.PriceIndicatorType;
import br.com.bk.vehicle.price.indicator.domain.types.ProcessErrorType;
import br.com.bk.vehicle.price.indicator.infrastructure.adapters.VehiclePriceIndicatorAdapter;
import br.com.bk.vehicle.price.indicator.infrastructure.logger.LOG;
import br.com.bk.vehicle.price.indicator.infrastructure.utils.DateUtils;
import java.util.List;
import org.springframework.stereotype.Component;


@Component
public class VehiclePriceIndicatorUseCase implements VehiclePriceIndicatorInputPort {

    private final IndicatorService service;

    public VehiclePriceIndicatorUseCase(IndicatorService service) {
        this.service = service;
    }

    @Override
    public List<VehiclePriceIndicatorDto> getVehicleIndicators(String licensePlate,
                                                               String date,
                                                               PriceIndicatorType type) {
        return getIndicators(licensePlate, date, type);
    }

    private List<VehiclePriceIndicatorDto> getIndicators(String licensePlate,
                                                         String date,
                                                         PriceIndicatorType type) {

        LOG.info("Parâmetros da busca: PLACA: %s, DATA: %s, TIPO: %s", licensePlate, date, type);

        validateRequest(date);
        return VehiclePriceIndicatorAdapter.from(service.findVehicleByLicensePlate(licensePlate, date, type));
    }

    private void validateRequest(String date) {
        if (date != null && !DateUtils.validateDate(date)) {
            LOG.warn("Erro ao validar data.");
            throw new ValidationFailedException(new ProcessErrorDto(ProcessErrorType.INVALID_DATE_FORMAT));
        }
    }
}
