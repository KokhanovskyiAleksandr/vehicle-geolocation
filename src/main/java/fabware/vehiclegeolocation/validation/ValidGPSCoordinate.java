package fabware.vehiclegeolocation.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

@Target({ElementType.PARAMETER, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ValidGPSCoordinateValidator.class)
public @interface ValidGPSCoordinate {

  String message() default "Invalid GPS coordinate";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};
}
