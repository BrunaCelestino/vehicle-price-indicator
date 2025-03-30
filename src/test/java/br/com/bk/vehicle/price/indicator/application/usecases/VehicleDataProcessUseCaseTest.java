package br.com.bk.vehicle.price.indicator.application.usecases;

import br.com.bk.vehicle.price.indicator.application.dtos.FipeIndicatorDataDto;
import br.com.bk.vehicle.price.indicator.application.dtos.FipeIndicatorDto;
import br.com.bk.vehicle.price.indicator.application.dtos.IcarrosIndicatorDto;
import br.com.bk.vehicle.price.indicator.application.dtos.MolicarIndicatorDto;
import br.com.bk.vehicle.price.indicator.application.dtos.VehicleDataDto;
import br.com.bk.vehicle.price.indicator.application.dtos.VehicleSavedDto;
import br.com.bk.vehicle.price.indicator.application.exceptions.EntityAlreadyExistsException;
import br.com.bk.vehicle.price.indicator.application.exceptions.ValidationFailedException;
import br.com.bk.vehicle.price.indicator.domain.models.VehicleData;
import br.com.bk.vehicle.price.indicator.domain.services.VehicleService;
import br.com.bk.vehicle.price.indicator.domain.types.ProcessErrorType;
import br.com.bk.vehicle.price.indicator.infrastructure.repositories.VehicleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.Mockito;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;

class VehicleDataProcessUseCaseTest {
    private VehicleRepository repository;
    private VehicleDataProcessUseCase useCase;

    @BeforeEach
    void setUp() {
        repository = Mockito.mock(VehicleRepository.class);
        VehicleService service = new VehicleService(repository);
        useCase = new VehicleDataProcessUseCase(service);
    }

    @Test
    void when_processVehicleData_shouldReturnVehicleSavedDto() {
        VehicleDataDto vehicleDataDto = getVehicleData();
        VehicleSavedDto savedDto = useCase.processVehicleData(vehicleDataDto);

        assertNotNull(savedDto);
        assertEquals(savedDto.getLicensePlate(), vehicleDataDto.getLicensePlate());
    }

    @Test
    void when_processVehicleData_withNullFipeIndicator_shouldReturnVehicleSavedDto() {
        VehicleDataDto vehicleDataDto = getVehicleData();
        vehicleDataDto.setFipeIndicators(null);
        VehicleSavedDto savedDto = useCase.processVehicleData(vehicleDataDto);

        assertNotNull(savedDto);
        assertEquals(savedDto.getLicensePlate(), vehicleDataDto.getLicensePlate());
    }

    @Test
    void when_processVehicleData_withNullFipeIndicatorData_shouldReturnVehicleSavedDto() {
        VehicleDataDto vehicleDataDto = getVehicleData();
        vehicleDataDto.getFipeIndicators().get(0).setValues(null);
        VehicleSavedDto savedDto = useCase.processVehicleData(vehicleDataDto);

        assertNotNull(savedDto);
        assertEquals(savedDto.getLicensePlate(), vehicleDataDto.getLicensePlate());
    }


    @Test
    void when_processVehicleData_withNullIcarrosIndicator_shouldReturnVehicleSavedDto() {
        VehicleDataDto vehicleDataDto = getVehicleData();
        vehicleDataDto.setIcarrosIndicators(null);
        VehicleSavedDto savedDto = useCase.processVehicleData(vehicleDataDto);

        assertNotNull(savedDto);
        assertEquals(savedDto.getLicensePlate(), vehicleDataDto.getLicensePlate());
    }

    @Test
    void when_processVehicleData_withNullMolicarIndicator_shouldReturnVehicleSavedDto() {
        VehicleDataDto vehicleDataDto = getVehicleData();
        vehicleDataDto.setMolicarIndicators(null);
        VehicleSavedDto savedDto = useCase.processVehicleData(vehicleDataDto);

        assertNotNull(savedDto);
        assertEquals(savedDto.getLicensePlate(), vehicleDataDto.getLicensePlate());
    }

    @Test
    void when_processVehicleData_withInvalidIcarrosDate_shouldThrowError() {
        VehicleDataDto vehicleDataDto = getVehicleData();
        vehicleDataDto.getIcarrosIndicators().get(0).setSearchMonth("12/25");

        ValidationFailedException exception = assertThrows(ValidationFailedException.class, () ->
                useCase.processVehicleData(vehicleDataDto));

        assertEquals(ProcessErrorType.INVALID_DATE_FORMAT.getDetails(), exception.getMessage());
    }

    @Test
    void when_processVehicleData_withInvalidFipeDate_shouldThrowError() {
        VehicleDataDto vehicleDataDto = getVehicleData();
        vehicleDataDto.getFipeIndicators().get(0).getValues().get(0).setSearchDate("12/25");

        ValidationFailedException exception = assertThrows(ValidationFailedException.class, () ->
                useCase.processVehicleData(vehicleDataDto));

        assertEquals(ProcessErrorType.INVALID_DATE_FORMAT.getDetails(), exception.getMessage());
    }

    @Test
    void when_processVehicleData_dateNull_shouldThrowError() {
        VehicleDataDto vehicleDataDto = getVehicleData();
        vehicleDataDto.getIcarrosIndicators().get(0).setSearchMonth(null);

        ValidationFailedException exception = assertThrows(ValidationFailedException.class, () ->
                useCase.processVehicleData(vehicleDataDto));

        assertEquals(ProcessErrorType.INVALID_DATE_FORMAT.getDetails(), exception.getMessage());
    }

    @Test
    void when_processVehicleData_withLicensePlateNull_shouldThrowError() {
        VehicleDataDto vehicleDataDto = getVehicleData();
        vehicleDataDto.setLicensePlate(null);

        ValidationFailedException exception = assertThrows(ValidationFailedException.class, () ->
                useCase.processVehicleData(vehicleDataDto));

        assertEquals(String.format(ProcessErrorType.REQUIRED_INFORMATION_MISSING.getDetails(), "placa"),
                exception.getMessage());
    }

    @Test
    void when_processVehicleData_withLicensePlateBlank_shouldThrowError() {
        VehicleDataDto vehicleDataDto = getVehicleData();
        vehicleDataDto.setLicensePlate(" ");

        ValidationFailedException exception = assertThrows(ValidationFailedException.class, () ->
                useCase.processVehicleData(vehicleDataDto));

        assertEquals(String.format(ProcessErrorType.REQUIRED_INFORMATION_MISSING.getDetails(), "placa"),
                exception.getMessage());
    }

    @Test
    void when_processVehicleData_withDataIntegrationViolation_shouldThrowError() {
        VehicleDataDto vehicleDataDto = getVehicleData();

        doThrow(DataIntegrityViolationException.class).when(repository).save(any(VehicleData.class));

        EntityAlreadyExistsException exception = assertThrows(EntityAlreadyExistsException.class, () ->
                useCase.processVehicleData(vehicleDataDto));

        assertEquals(String.format(ProcessErrorType.VEHICLE_ALREADY_REGISTERED.getDetails()),
                exception.getMessage());
    }

    public static VehicleDataDto getVehicleData() {
        VehicleDataDto vehicleData = new VehicleDataDto();
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

        FipeIndicatorDataDto fipeData1 = new FipeIndicatorDataDto();
        fipeData1.setFipeValue("67388.0");
        fipeData1.setSearchDate("03/2025");
        fipeData1.setFipeNomenclature("Nissan-Sentra SV 2.0 FlexStart 16V Aut.");
        fipeData1.setComparisonIndex("0.50");
        fipeData1.setDepreciation("true");

        FipeIndicatorDto fipeIndicator = new FipeIndicatorDto();
        fipeIndicator.setCode("023123-1");
        fipeIndicator.setValues(List.of(fipeData1));

        vehicleData.setFipeIndicators(List.of(fipeIndicator));

        MolicarIndicatorDto molicarIndicator = new MolicarIndicatorDto();
        molicarIndicator.setCode("03701339-7");
        molicarIndicator.setDescription("NISSAN SENTRA SV N.GERACAO 2.0 16V CVT FLEXSTART 4P Eta./Gas.");

        vehicleData.setMolicarIndicators(List.of(molicarIndicator));

        IcarrosIndicatorDto icarrosIndicator = new IcarrosIndicatorDto();
        icarrosIndicator.setIcarrosCode("IC123456");
        icarrosIndicator.setIcarrosValue("65000.0");
        icarrosIndicator.setSearchMonth("03/2025");
        icarrosIndicator.setIcarrosNomenclature("Nissan-Sentra SV 2.0 FlexStart 16V Aut.");
        icarrosIndicator.setComparisonIndex("1.20");
        icarrosIndicator.setDepreciation("false");

        vehicleData.setIcarrosIndicators(List.of(icarrosIndicator));

        return vehicleData;
    }

}