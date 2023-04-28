package fabware.vehiclegeolocation.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class InvalidVehicleCoordinateException extends RuntimeException {

  public InvalidVehicleCoordinateException(String message) {
    super(message);
  }
}
