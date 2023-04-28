package fabware.vehiclegeolocation.validation;

import fabware.vehiclegeolocation.dto.TriangleDto;
import fabware.vehiclegeolocation.exception.InvalidVehicleCoordinateException;

import java.util.List;
import java.util.Objects;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ValidGPSCoordinateValidator implements ConstraintValidator<ValidGPSCoordinate, TriangleDto> {

  private final String ERROR_MESSAGE = "Invalid GPS coordinate";

  @Override
  public boolean isValid(TriangleDto coordinate, ConstraintValidatorContext constraintValidatorContext) {
    //business logic
    //can be some checks on Region specific
    if (isNotAxisCorrectSize(coordinate.getCoordinatesX()) && isNotAxisCorrectSize(coordinate.getCoordinatesY())) {
      throw new InvalidVehicleCoordinateException(ERROR_MESSAGE);
    }

    return true;
  }

  private static boolean isNotAxisCorrectSize(List<Double> coordinates) {
    return Objects.isNull(coordinates) || coordinates.size() != 3;
  }
}
