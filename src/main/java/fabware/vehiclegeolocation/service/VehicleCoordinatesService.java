package fabware.vehiclegeolocation.service;

import fabware.vehiclegeolocation.dto.TriangleDto;
import fabware.vehiclegeolocation.dto.VehicleDto;
import fabware.vehiclegeolocation.model.entity.Vehicle;
import fabware.vehiclegeolocation.mapper.VehicleMapper;
import fabware.vehiclegeolocation.repository.VehicleCoordinatesRepository;
import fabware.vehiclegeolocation.repository.VehicleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehicleCoordinatesService {

  private final VehicleCoordinatesRepository vehicleCoordinatesRepository;
  private final VehicleRepository vehicleRepository;

  public VehicleCoordinatesService(VehicleCoordinatesRepository vehicleCoordinatesRepository, VehicleRepository vehicleRepository) {
    this.vehicleCoordinatesRepository = vehicleCoordinatesRepository;
    this.vehicleRepository = vehicleRepository;
  }

  public List<VehicleDto> getVehiclesCoordinates(TriangleDto triangleDto) {

    List<Double> coordinatesX = triangleDto.getCoordinatesX();
    List<Double> coordinatesY = triangleDto.getCoordinatesY();
    String polygon = String.format("POLYGON((%s %s, %s %s, %s %s, %s %s))",
        coordinatesX.get(0), coordinatesY.get(0),
        coordinatesX.get(1), coordinatesY.get(1),
        coordinatesX.get(2), coordinatesY.get(2),
        coordinatesX.get(0), coordinatesY.get(0));
    List<Long> vehicleCoordinates = vehicleCoordinatesRepository.findAllVehiclesByCoordinates(polygon);

    List<Vehicle> vehicles = vehicleRepository.findAllById(vehicleCoordinates);

    return VehicleMapper.toDto(vehicles);
  }
}
