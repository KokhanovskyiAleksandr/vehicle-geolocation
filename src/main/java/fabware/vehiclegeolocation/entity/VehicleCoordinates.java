package fabware.vehiclegeolocation.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.geo.Polygon;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@ToString
@EqualsAndHashCode
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "vehicle_coordinates")
public class VehicleCoordinates {


  public VehicleCoordinates(Polygon coordinates) {
    this.coordinates = coordinates;
  }

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "triangle")
  private Polygon coordinates;

  @OneToOne
  @JoinColumn(name = "vehicle_id")
  private Vehicle vehicle;
}
