package br.com.bk.vehicle.price.indicator.domain.repositories;

import br.com.bk.vehicle.price.indicator.domain.entities.VehicleDataEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;


public interface VehicleJpaRepository extends JpaRepository<VehicleDataEntity, Long> {

    Optional<VehicleDataEntity> findByLicensePlate(String licensePlate);
}