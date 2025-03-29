package br.com.bk.vehicle.price.indicator.infrastructure.repositories;

import br.com.bk.vehicle.price.indicator.domain.entities.VehicleDataEntity;
import br.com.bk.vehicle.price.indicator.domain.models.VehicleData;
import br.com.bk.vehicle.price.indicator.domain.repositories.VehicleJpaRepository;
import br.com.bk.vehicle.price.indicator.infrastructure.adapters.VehicleDataAdapter;
import br.com.bk.vehicle.price.indicator.infrastructure.adapters.VehicleDataEntityAdapter;
import java.util.Optional;
import org.springframework.stereotype.Repository;


@Repository
public class VehicleRepository {
    private final VehicleJpaRepository jpaRepository;

    public VehicleRepository(VehicleJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    public void save(VehicleData vehicleData) {
        jpaRepository.save(VehicleDataEntityAdapter.from(vehicleData));
    }

    public VehicleData findByLicensePlate(String licensePlate) {
        Optional<VehicleDataEntity> vehicle = jpaRepository.findByLicensePlate(licensePlate);

        return vehicle.map(VehicleDataAdapter::from).orElse(null);
    }
}
