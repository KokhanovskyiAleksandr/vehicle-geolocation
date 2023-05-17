package fabware.vehiclegeolocation.repository;

import fabware.vehiclegeolocation.model.entity.VehicleCoordinates;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VehicleCoordinatesRepository extends JpaRepository<VehicleCoordinates, Long> {

  @Query(value =
      "SELECT vc.vehicle_id"
          + " FROM vehicle_coordinates vc"
          + " WHERE ST_Within(coordinates, ST_GeomFromText(:polygon))", nativeQuery = true)
  List<Long> findAllVehiclesByCoordinates(@Param("polygon") String polygon) ;
}
