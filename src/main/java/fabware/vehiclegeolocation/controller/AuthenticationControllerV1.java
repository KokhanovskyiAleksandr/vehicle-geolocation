package fabware.vehiclegeolocation.controller;

import fabware.vehiclegeolocation.dto.AuthenticationRequestDto;
import fabware.vehiclegeolocation.model.entity.User;
import fabware.vehiclegeolocation.repository.UserRepository;
import fabware.vehiclegeolocation.security.JwtTokenProvider;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthenticationControllerV1 {

  private final AuthenticationManager authenticationManager;
  private final UserRepository userRepository;
  private final JwtTokenProvider tokenProvider;

  public AuthenticationControllerV1(AuthenticationManager authenticationManager, UserRepository userRepository, JwtTokenProvider tokenProvider) {
    this.authenticationManager = authenticationManager;
    this.userRepository = userRepository;
    this.tokenProvider = tokenProvider;
  }

  @PostMapping("/login")
  public ResponseEntity<?> authenticate(@RequestBody AuthenticationRequestDto request) {
    authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
    User user = userRepository.findByEmail(request.getEmail()).orElseThrow(() -> new UsernameNotFoundException("User doesn't exist"));
    String token = tokenProvider.createToken(request.getEmail(), user.getRole().name());

    Map<Object, Object> response = new HashMap<>();
    response.put("email", request.getEmail());
    response.put("token", token);

    return ResponseEntity.ok(response);

  }

  @PostMapping("/logout")
  public void logout(HttpServletRequest request, HttpServletResponse response) {
    SecurityContextLogoutHandler securityContextLogoutHandler = new SecurityContextLogoutHandler();
    securityContextLogoutHandler.logout(request, response, null);
  }
}
