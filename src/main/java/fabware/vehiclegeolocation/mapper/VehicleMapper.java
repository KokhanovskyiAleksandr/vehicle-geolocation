package fabware.vehiclegeolocation.mapper;

import fabware.vehiclegeolocation.dto.VehicleDto;
import fabware.vehiclegeolocation.model.entity.Vehicle;
import lombok.experimental.UtilityClass;

import java.util.List;
import java.util.stream.Collectors;

@UtilityClass
public class VehicleMapper {

  public static List<VehicleDto> toDto(List<Vehicle> vehicleCoordinates) {
    return vehicleCoordinates.stream().map(VehicleMapper::toDto).collect(Collectors.toList());
  }

  public static VehicleDto toDto(Vehicle vehicle) {
    return VehicleDto.builder()
        .trackingName(vehicle.getTrackingName())
        .build();
  }
}
