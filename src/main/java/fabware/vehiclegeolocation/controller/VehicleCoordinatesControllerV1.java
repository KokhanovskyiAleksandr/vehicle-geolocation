package fabware.vehiclegeolocation.controller;

import fabware.vehiclegeolocation.dto.TriangleDto;
import fabware.vehiclegeolocation.dto.VehicleDto;
import fabware.vehiclegeolocation.service.VehicleCoordinatesService;
import fabware.vehiclegeolocation.validation.ValidGPSCoordinate;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Validated
@RestController
@RequestMapping("/api/v1/vehicles")
public class VehicleCoordinatesControllerV1 {

  private final VehicleCoordinatesService vehicleCoordinatesService;

  public VehicleCoordinatesControllerV1(VehicleCoordinatesService vehicleCoordinatesService) {
    this.vehicleCoordinatesService = vehicleCoordinatesService;
  }

  @GetMapping("/coordinates")
  public List<VehicleDto> updateCurrentCoordinates(@ValidGPSCoordinate TriangleDto coordinates) {


    return vehicleCoordinatesService.getVehiclesCoordinates(coordinates);
  }

}
