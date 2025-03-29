package br.com.bk.vehicle.price.indicator.application.usecases;

import br.com.bk.vehicle.price.indicator.application.dtos.VehicleDataDto;
import br.com.bk.vehicle.price.indicator.application.dtos.VehicleSavedDto;
import br.com.bk.vehicle.price.indicator.application.ports.VehicleDataProcessInputPort;
import br.com.bk.vehicle.price.indicator.domain.models.VehicleData;
import br.com.bk.vehicle.price.indicator.domain.services.VehicleService;
import br.com.bk.vehicle.price.indicator.infrastructure.adapters.VehicleDataAdapter;
import br.com.bk.vehicle.price.indicator.infrastructure.logger.LOG;
import org.springframework.stereotype.Component;


@Component
public class VehicleDataProcessUseCase implements VehicleDataProcessInputPort {
    private final VehicleService service;

    public VehicleDataProcessUseCase(VehicleService service) {
        this.service = service;
    }

    @Override
    public VehicleSavedDto processVehicleData(VehicleDataDto vehicleDataDto) {
        LOG.info("Convertendo DTO para Entity");

        VehicleData vehicleData = VehicleDataAdapter.from(vehicleDataDto);

        return service.saveVehicle(vehicleData);
    }
}
