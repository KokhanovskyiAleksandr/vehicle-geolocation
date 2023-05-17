package fabware.vehiclegeolocation.security;

import fabware.vehiclegeolocation.exception.JwtAuthenticationException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import java.util.Base64;
import java.util.Date;
import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

@Getter
@Setter
@Component
public class JwtTokenProvider {
  private final UserDetailsService userDetailsService;

  @Value(value = "${security.jwt.header}")
  private String authorizationHeader;
  @Value(value = "${security.jwt.signingKey}")
  private String signingKey;
  @Value(value = "${security.jwt.expiration}")
  private long validityInMilliseconds;

  public JwtTokenProvider(@Qualifier("userDetailsServiceImpl") UserDetailsService userDetailsService) {
    this.userDetailsService = userDetailsService;
  }

  @PostConstruct
  protected void init() {
    signingKey = Base64.getEncoder().encodeToString(signingKey.getBytes());
  }

  public String createToken(String username, String role) {
    Claims claims = Jwts.claims().setSubject(username);
    claims.put("role", role);
    Date now = new Date();
    Date validity = new Date(now.getTime() + validityInMilliseconds);

    return Jwts.builder()
        .setClaims(claims)
        .setIssuedAt(now)
        .setExpiration(validity)
        .signWith(SignatureAlgorithm.HS256, signingKey)
        .compact();
  }

  public boolean validateToken(String token) {
    try {
      Jws<Claims> claimsJws = Jwts.parser().setSigningKey(signingKey).parseClaimsJws(token);
      return !claimsJws.getBody().getExpiration().before(new Date());
    } catch (JwtException | IllegalArgumentException e) {
      throw new JwtAuthenticationException("JWT token is expired or invalid", HttpStatus.UNAUTHORIZED);
    }
  }

  public Authentication getAuthentication(String token) {
    UserDetails userDetails = this.userDetailsService.loadUserByUsername(getUsername(token));
    return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
  }

  public String getUsername(String token) {
    return Jwts.parser().setSigningKey(signingKey).parseClaimsJws(token).getBody().getSubject();
  }

  public String resolveToken(HttpServletRequest request) {
    return request.getHeader(authorizationHeader);
  }
}
