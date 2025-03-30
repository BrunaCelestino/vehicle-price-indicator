package br.com.bk.vehicle.price.indicator.application.exceptions;

import br.com.bk.vehicle.price.indicator.application.dtos.ProcessErrorDto;
import br.com.bk.vehicle.price.indicator.domain.types.ProcessErrorType;
import br.com.bk.vehicle.price.indicator.infrastructure.logger.LOG;
import java.util.ArrayList;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EntityAlreadyExistsException.class)
    public ResponseEntity<List<ProcessErrorDto>> handleDuplicateValueException(EntityAlreadyExistsException ex) {
        LOG.error(ex.getMessage());

        List<ProcessErrorDto> errors = new ArrayList<>();
        errors.add(ex.getProcessErrorDto());

        return new ResponseEntity<>(errors, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(ValidationFailedException.class)
    public ResponseEntity<List<ProcessErrorDto>> handleValidationFailedException(ValidationFailedException ex) {
        LOG.error(ex.getMessage());

        List<ProcessErrorDto> errors = new ArrayList<>();
        errors.add(ex.getProcessErrorDto());

        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<List<ProcessErrorDto>> handleEntityNotFoundException(EntityNotFoundException ex) {
        LOG.error(ex.getMessage());

        List<ProcessErrorDto> errors = new ArrayList<>();
        errors.add(ex.getProcessErrorDto());

        return new ResponseEntity<>(errors, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<List<ProcessErrorDto>> handleArgumentMissingException(
            MissingServletRequestParameterException ex) {
        LOG.error(ex.getMessage());

        List<ProcessErrorDto> errors = new ArrayList<>();
        errors.add(new ProcessErrorDto(ProcessErrorType.REQUIRED_INFORMATION_MISSING, ex.getParameterName()));

        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<List<ProcessErrorDto>> handleArgumentMismatchException(
            MethodArgumentTypeMismatchException ex) {
        LOG.error(ex.getMessage());

        List<ProcessErrorDto> errors = new ArrayList<>();
        errors.add(new ProcessErrorDto(ProcessErrorType.INVALID_PARAMETER_VALUE, ex.getName()));

        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<List<ProcessErrorDto>> handleException(Exception ex) {
        LOG.error(ex.getMessage());

        List<ProcessErrorDto> errors = new ArrayList<>();
        ProcessErrorDto error = new ProcessErrorDto(ProcessErrorType.GENERAL_ERROR);
        errors.add(error);

        return new ResponseEntity<>(errors, HttpStatus.INTERNAL_SERVER_ERROR);
    }


}