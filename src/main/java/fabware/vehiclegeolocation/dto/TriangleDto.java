package fabware.vehiclegeolocation.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class TriangleDto {

  private List<Double> coordinatesX;
  private List<Double> coordinatesY;


}
