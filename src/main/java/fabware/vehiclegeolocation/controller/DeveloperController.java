package fabware.vehiclegeolocation.controller;

import fabware.vehiclegeolocation.model.entity.Developer;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping("/api/v1/developer")
public class DeveloperController {

  List<Developer> developers = Stream.of(
      new Developer(1L, "john"),
      new Developer(2L, "simon"),
      new Developer(3L, "scarlet")
  ).collect(Collectors.toList());

  Long id = 4L;

  @GetMapping
  @PreAuthorize("hasAuthority('developers:read')")
  public List<Developer> getAll() {
    return developers;
  }

  @GetMapping("/{id}")
  @PreAuthorize("hasAuthority('developers:write')")
  public Developer getById(@PathVariable Long id) {
    return developers.stream().filter(developer -> developer.getId().equals(id)).findAny().orElse(null);
  }

  @PostMapping
  @PreAuthorize("hasAuthority('developers:write')")
  public Developer createDeveloper(@RequestBody Developer developer) {
    developer.setId(id);
    developers.add(developer);

    id++;
    return developer;
  }

  @DeleteMapping("/{id}")
  public void deleteDeveloper(@PathVariable Long id) {
    developers.removeIf(developer -> developer.getId().equals(id));
  }
}
