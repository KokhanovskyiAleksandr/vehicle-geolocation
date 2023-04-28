package fabware.vehiclegeolocation.controller;

import fabware.vehiclegeolocation.dto.TriangleDto;
import fabware.vehiclegeolocation.dto.VehicleDto;
import fabware.vehiclegeolocation.service.VehicleCoordinatesService;
import fabware.vehiclegeolocation.validation.ValidGPSCoordinate;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Validated
@RestController
@RequestMapping("/vehicles")
public class VehicleCoordinatesController {

  private final VehicleCoordinatesService vehicleCoordinatesService;

  public VehicleCoordinatesController(VehicleCoordinatesService vehicleCoordinatesService) {
    this.vehicleCoordinatesService = vehicleCoordinatesService;
  }

  @GetMapping("/coordinates")
  public List<VehicleDto> updateCurrentCoordinates(@ValidGPSCoordinate TriangleDto coordinates) {


    return vehicleCoordinatesService.getVehiclesCoordinates(coordinates);
  }

}
