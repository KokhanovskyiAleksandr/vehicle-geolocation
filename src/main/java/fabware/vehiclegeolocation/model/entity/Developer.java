package fabware.vehiclegeolocation.model.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Developer {
  public Developer(Long id, String name) {
    this.id = id;
    this.name = name;
  }

  private Long id;
  private String name;
}
