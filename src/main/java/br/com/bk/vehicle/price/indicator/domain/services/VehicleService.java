package br.com.bk.vehicle.price.indicator.domain.services;

import static br.com.bk.vehicle.price.indicator.infrastructure.utils.JsonUtils.beautifyJson;

import br.com.bk.vehicle.price.indicator.application.dtos.ProcessErrorDto;
import br.com.bk.vehicle.price.indicator.application.dtos.VehicleSavedDto;
import br.com.bk.vehicle.price.indicator.application.exceptions.EntityAlreadyExistsException;
import br.com.bk.vehicle.price.indicator.application.exceptions.ValidationFailedException;
import br.com.bk.vehicle.price.indicator.domain.models.FipeIndicatorData;
import br.com.bk.vehicle.price.indicator.domain.models.IcarrosIndicator;
import br.com.bk.vehicle.price.indicator.domain.models.VehicleData;
import br.com.bk.vehicle.price.indicator.domain.types.ProcessErrorType;
import br.com.bk.vehicle.price.indicator.infrastructure.logger.LOG;
import br.com.bk.vehicle.price.indicator.infrastructure.repositories.VehicleRepository;
import br.com.bk.vehicle.price.indicator.infrastructure.utils.DateUtils;
import java.time.Instant;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class VehicleService {
    private final VehicleRepository repository;

    public VehicleService(VehicleRepository repository) {
        this.repository = repository;
    }

    public VehicleSavedDto saveVehicle(VehicleData vehicleData) {
        LOG.info("Salvando veículo");
        LOG.info(beautifyJson(vehicleData));

        validateVehicleData(vehicleData);

        saveVehicleData(vehicleData);

        return VehicleSavedDto.builder()
                .licensePlate(vehicleData.getLicensePlate())
                .createdAt(Instant.now())
                .build();
    }

    private void validateVehicleData(VehicleData vehicleData) {
        validateLicensePlate(vehicleData);
        validateDates(vehicleData);
    }

    private static void validateDates(VehicleData vehicleData) {
        boolean hasInvalidDates = vehicleData.getFipeIndicators().stream()
                .flatMap(fipe -> fipe.getValues().stream())
                .map(FipeIndicatorData::getSearchDate)
                .anyMatch(date -> !DateUtils.isValidDate(date))
                || vehicleData.getIcarrosIndicators().stream()
                        .map(IcarrosIndicator::getSearchMonth)
                        .anyMatch(date -> !DateUtils.isValidDate(date));

        if (hasInvalidDates) {
            throw new ValidationFailedException(new ProcessErrorDto(ProcessErrorType.INVALID_DATE_FORMAT));
        }
    }

    private static void validateLicensePlate(VehicleData vehicleData) {
        if (vehicleData.getLicensePlate() == null || vehicleData.getLicensePlate().isBlank()) {
            throw new ValidationFailedException(new ProcessErrorDto(
                    ProcessErrorType.REQUIRED_INFORMATION_MISSING, "placa"));
        }
    }

    private void saveVehicleData(VehicleData vehicleData) {
        try {
            repository.save(vehicleData);
        } catch (DataIntegrityViolationException e) {
            throw new EntityAlreadyExistsException(new ProcessErrorDto(ProcessErrorType.VEHICLE_ALREADY_REGISTERED));
        }
    }
}
