package fabware.vehiclegeolocation.controller;

import fabware.vehiclegeolocation.error.ErrorResponse;
import fabware.vehiclegeolocation.exception.InvalidVehicleCoordinateException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Optional;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

  private final String DEFAULT_ERROR = "Oops, something goes wrong!";


  @ExceptionHandler(value = {InvalidVehicleCoordinateException.class})
  protected ResponseEntity<Object> handleInvalidVehicleCoordinate(RuntimeException ex) {
    ErrorResponse errorResponse = new ErrorResponse(getCause(ex));
    return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
  }

  private String getCause(RuntimeException ex) {
    return Optional.of(ex).map(RuntimeException::getCause).map(Throwable::getMessage).orElse(DEFAULT_ERROR);
  }
}
