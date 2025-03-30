package br.com.bk.vehicle.price.indicator.infrastructure.repositories;

import br.com.bk.vehicle.price.indicator.application.usecases.VehicleDataProcessUseCase;
import br.com.bk.vehicle.price.indicator.domain.entities.FipeIndicatorDataEntity;
import br.com.bk.vehicle.price.indicator.domain.entities.FipeIndicatorEntity;
import br.com.bk.vehicle.price.indicator.domain.entities.IcarrosIndicatorEntity;
import br.com.bk.vehicle.price.indicator.domain.entities.MolicarIndicatorEntity;
import br.com.bk.vehicle.price.indicator.domain.entities.VehicleDataEntity;
import br.com.bk.vehicle.price.indicator.domain.models.FipeIndicator;
import br.com.bk.vehicle.price.indicator.domain.models.FipeIndicatorData;
import br.com.bk.vehicle.price.indicator.domain.models.IcarrosIndicator;
import br.com.bk.vehicle.price.indicator.domain.models.MolicarIndicator;
import br.com.bk.vehicle.price.indicator.domain.models.VehicleData;
import br.com.bk.vehicle.price.indicator.domain.repositories.VehicleJpaRepository;
import br.com.bk.vehicle.price.indicator.domain.services.VehicleService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class VehicleRepositoryTest {

    @Mock
    private VehicleJpaRepository jpaRepository;

    @InjectMocks
    private VehicleRepository vehicleRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void when_save_shouldSave() {
        vehicleRepository.save(new VehicleData());

        verify(jpaRepository, times(1)).save(any(VehicleDataEntity.class));
    }

    @Test
    void when_save_withLists_shouldSave() {
        VehicleData data = new VehicleData();

        data.setIcarrosIndicators(List.of(new IcarrosIndicator()));

        FipeIndicator fipeIndicator = new FipeIndicator();
        fipeIndicator.setValues(List.of(new FipeIndicatorData()));

        data.setFipeIndicators(List.of(fipeIndicator, new FipeIndicator()));
        data.setMolicarIndicators(List.of(new MolicarIndicator()));

        vehicleRepository.save(data);

        verify(jpaRepository, times(1)).save(any(VehicleDataEntity.class));
    }



    @Test
    void when_findByLicensePlate_shouldFind() {
        VehicleDataEntity entity = new VehicleDataEntity();
        entity.setLicensePlate("FUD5J98");
        entity.setBrand("Nissan");

        when(jpaRepository.findByLicensePlate("FUD5J98"))
                .thenReturn(Optional.of(entity));

        VehicleData result = vehicleRepository.findByLicensePlate("FUD5J98");

        assertNotNull(result);
        assertEquals("FUD5J98", result.getLicensePlate());
        assertEquals("Nissan", result.getBrand());
    }

    @Test
    void when_findByLicensePlate_withLists_shouldFind() {
        VehicleDataEntity entity = new VehicleDataEntity();
        entity.setIcarrosIndicators(List.of(new IcarrosIndicatorEntity()));
        FipeIndicatorEntity fipeIndicator = new FipeIndicatorEntity();
        fipeIndicator.setValues(List.of(new FipeIndicatorDataEntity()));
        entity.setFipeIndicators(List.of(fipeIndicator, new FipeIndicatorEntity()));
        entity.setMolicarIndicators(List.of(new MolicarIndicatorEntity()));
        entity.setLicensePlate("FUD5J98");
        entity.setBrand("Nissan");

        when(jpaRepository.findByLicensePlate("FUD5J98"))
                .thenReturn(Optional.of(entity));

        VehicleData result = vehicleRepository.findByLicensePlate("FUD5J98");

        assertNotNull(result);
        assertEquals("FUD5J98", result.getLicensePlate());
        assertEquals("Nissan", result.getBrand());
    }

    @Test
    void when_findByLicensePlate_shouldNotFind() {
        when(jpaRepository.findByLicensePlate("FUD5J99"))
                .thenReturn(Optional.empty());

        VehicleData result = vehicleRepository.findByLicensePlate("FUD5J99");

        assertNull(result);
    }

}