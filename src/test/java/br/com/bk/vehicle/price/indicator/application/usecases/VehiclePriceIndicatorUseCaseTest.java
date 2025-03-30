package br.com.bk.vehicle.price.indicator.application.usecases;

import br.com.bk.vehicle.price.indicator.application.dtos.VehiclePriceIndicatorDto;
import br.com.bk.vehicle.price.indicator.application.exceptions.EntityNotFoundException;
import br.com.bk.vehicle.price.indicator.application.exceptions.ValidationFailedException;
import br.com.bk.vehicle.price.indicator.domain.models.FipeIndicator;
import br.com.bk.vehicle.price.indicator.domain.models.FipeIndicatorData;
import br.com.bk.vehicle.price.indicator.domain.models.IcarrosIndicator;
import br.com.bk.vehicle.price.indicator.domain.models.MolicarIndicator;
import br.com.bk.vehicle.price.indicator.domain.models.VehicleData;
import br.com.bk.vehicle.price.indicator.domain.services.IndicatorService;
import br.com.bk.vehicle.price.indicator.domain.types.PriceIndicatorType;
import br.com.bk.vehicle.price.indicator.domain.types.ProcessErrorType;
import br.com.bk.vehicle.price.indicator.infrastructure.repositories.VehicleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;


class VehiclePriceIndicatorUseCaseTest {
    private VehicleRepository repository;
    private VehiclePriceIndicatorUseCase useCase;

    @BeforeEach
    void setUp() {
        repository = Mockito.mock(VehicleRepository.class);
        IndicatorService service = new IndicatorService(repository);
        useCase = new VehiclePriceIndicatorUseCase(service);
    }

    @Test
    void when_getVehicleIndicators_shouldReturnVehiclePriceIndicators() {
        String licensePlate = "123456";
        String date = "03/2025";
        PriceIndicatorType type = PriceIndicatorType.ALL;

        when(repository.findByLicensePlate(licensePlate)).thenReturn(getVehicleData());
        List<VehiclePriceIndicatorDto> vehicleIndicators = useCase.getVehicleIndicators(licensePlate, date, type);

        assertNotNull(vehicleIndicators);
        assertFalse(vehicleIndicators.isEmpty());

    }

    @Test
    void when_getVehicleIndicators_typeFipe_shouldReturnVehiclePriceIndicators() {
        String licensePlate = "123456";
        String date = "03/2025";
        PriceIndicatorType type = PriceIndicatorType.FIPE;

        when(repository.findByLicensePlate(licensePlate)).thenReturn(getVehicleData());
        List<VehiclePriceIndicatorDto> vehicleIndicators = useCase.getVehicleIndicators(licensePlate, date, type);

        assertNotNull(vehicleIndicators);
        assertFalse(vehicleIndicators.isEmpty());
        assertNull(vehicleIndicators.get(0).getIcarrosValue());
    }

    @Test
    void when_getVehicleIndicators_typeIcarros_shouldReturnVehiclePriceIndicators() {
        String licensePlate = "123456";
        String date = "03/2025";
        PriceIndicatorType type = PriceIndicatorType.ICARROS;

        when(repository.findByLicensePlate(licensePlate)).thenReturn(getVehicleData());
        List<VehiclePriceIndicatorDto> vehicleIndicators = useCase.getVehicleIndicators(licensePlate, date, type);

        assertNotNull(vehicleIndicators);
        assertFalse(vehicleIndicators.isEmpty());
        assertNull(vehicleIndicators.get(0).getFipeValue());
    }

    @Test
    void when_getVehicleIndicators_typeBoth_shouldReturnVehiclePriceIndicators() {
        String licensePlate = "123456";
        String date = "03/2025";
        PriceIndicatorType type = PriceIndicatorType.BOTH;

        when(repository.findByLicensePlate(licensePlate)).thenReturn(getVehicleData());
        List<VehiclePriceIndicatorDto> vehicleIndicators = useCase.getVehicleIndicators(licensePlate, date, type);

        assertNotNull(vehicleIndicators);
        assertFalse(vehicleIndicators.isEmpty());
        assertNotNull(vehicleIndicators.get(0).getFipeValue());
        assertNotNull(vehicleIndicators.get(0).getIcarrosValue());
    }


    @Test
    void when_getVehicleIndicators_dateNull_shouldReturnVehiclePriceIndicators() {
        String licensePlate = "123456";
        String date = null;
        PriceIndicatorType type = PriceIndicatorType.ALL;

        when(repository.findByLicensePlate(licensePlate)).thenReturn(getVehicleData());
        List<VehiclePriceIndicatorDto> vehicleIndicators = useCase.getVehicleIndicators(licensePlate, date, type);

        assertNotNull(vehicleIndicators);
        assertFalse(vehicleIndicators.isEmpty());
    }

    @Test
    void when_getVehicleIndicators_invalidDate_shouldThrowError() {
        String licensePlate = "123456";
        String date = "03/25";
        PriceIndicatorType type = PriceIndicatorType.ALL;

        ValidationFailedException exception = assertThrows(ValidationFailedException.class, () -> useCase
                .getVehicleIndicators(licensePlate, date, type));

        assertEquals(ProcessErrorType.INVALID_DATE_FORMAT.getDetails(), exception.getMessage());
    }

    @Test
    void when_getVehicleIndicators_vehicleNotFound_shouldThrowError() {
        String licensePlate = "123456";
        String date = "03/2025";
        PriceIndicatorType type = PriceIndicatorType.ALL;

        when(repository.findByLicensePlate(licensePlate)).thenReturn(null);

        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class, () -> useCase
                .getVehicleIndicators(licensePlate, date, type));

        assertEquals(String.format(ProcessErrorType.VEHICLE_NOT_FOUND.getDetails(), licensePlate),
                exception.getMessage());
    }

    @Test
    void when_getVehicleIndicators_indicatorNotFound_shouldThrowError() {
        String licensePlate = "123456";
        String date = "12/2026";
        PriceIndicatorType type = PriceIndicatorType.ALL;

        VehicleData vehicleData = getVehicleData();

        when(repository.findByLicensePlate(licensePlate)).thenReturn(vehicleData);

        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class, () -> useCase
                .getVehicleIndicators(licensePlate, date, type));

        assertEquals(ProcessErrorType.INDICATOR_NOT_FOUND.getDetails(),
                exception.getMessage());
    }

    public static VehicleData getVehicleData() {
        VehicleData vehicleData = new VehicleData();
        vehicleData.setCode("000");
        vehicleData.setMessage("Sucesso");
        vehicleData.setLicensePlate("FUD5J98");
        vehicleData.setChassis("3N1BB7ADXHY200644");
        vehicleData.setJurisdictionState("SP");
        vehicleData.setRegistrationCity("VALINHOS");
        vehicleData.setManufacturingYear(2016);
        vehicleData.setModelYear(2017);
        vehicleData.setBrand("I");
        vehicleData.setModel("NISSAN SENTRA 20SV CVT");
        vehicleData.setVehicleType("Automovel");
        vehicleData.setBodyType("NAO APLICAVEL");
        vehicleData.setPredominantColor("Cinza");
        vehicleData.setFuelType("ALCOOL/GASOLINA");
        vehicleData.setHorsepower(140);
        vehicleData.setDisplacement(1997);
        vehicleData.setPassengerCapacity(5);
        vehicleData.setNumberOfAxles(2);
        vehicleData.setEngineNumber("MR20980798H");
        vehicleData.setRegistrationDate("09092016");
        vehicleData.setNumberOfAcquisitions(8);
        vehicleData.setLastAcquisitionState("SP");
        vehicleData.setCurrentState("SP");
        vehicleData.setLastAcquisitionCity("MOGI DAS CRUZES");

        FipeIndicatorData fipeData1 = new FipeIndicatorData();
        fipeData1.setFipeValue("67388.0");
        fipeData1.setSearchDate("03/2025");
        fipeData1.setFipeNomenclature("Nissan-Sentra SV 2.0 FlexStart 16V Aut.");
        fipeData1.setComparisonIndex("0.50");
        fipeData1.setDepreciation("true");

        FipeIndicatorData fipeData2 = new FipeIndicatorData();
        fipeData2.setFipeValue("67388.0");
        fipeData2.setSearchDate("05/2025");
        fipeData2.setFipeNomenclature("Nissan-Sentra SV 2.0 FlexStart 16V Aut.");
        fipeData2.setComparisonIndex("0.50");
        fipeData2.setDepreciation("true");

        FipeIndicator fipeIndicator = new FipeIndicator();
        fipeIndicator.setCode("023123-1");
        fipeIndicator.setValues(List.of(fipeData1, fipeData2));

        vehicleData.setFipeIndicators(List.of(fipeIndicator));

        MolicarIndicator molicarIndicator = new MolicarIndicator();
        molicarIndicator.setCode("03701339-7");
        molicarIndicator.setDescription("NISSAN SENTRA SV N.GERACAO 2.0 16V CVT FLEXSTART 4P Eta./Gas.");

        vehicleData.setMolicarIndicators(List.of(molicarIndicator));

        IcarrosIndicator icarrosIndicator1 = new IcarrosIndicator();
        icarrosIndicator1.setIcarrosCode("IC123456");
        icarrosIndicator1.setIcarrosValue("65000.0");
        icarrosIndicator1.setSearchMonth("03/2025");
        icarrosIndicator1.setIcarrosNomenclature("Nissan-Sentra SV 2.0 FlexStart 16V Aut.");
        icarrosIndicator1.setComparisonIndex("1.20");
        icarrosIndicator1.setDepreciation("false");

        IcarrosIndicator icarrosIndicator2 = new IcarrosIndicator();
        icarrosIndicator2.setIcarrosCode("IC123456");
        icarrosIndicator2.setIcarrosValue("65000.0");
        icarrosIndicator2.setSearchMonth("12/2023");
        icarrosIndicator2.setIcarrosNomenclature("Nissan-Sentra SV 2.0 FlexStart 16V Aut.");
        icarrosIndicator2.setComparisonIndex("1.20");
        icarrosIndicator2.setDepreciation("false");

        vehicleData.setIcarrosIndicators(List.of(icarrosIndicator1, icarrosIndicator2));

        return vehicleData;
    }

}