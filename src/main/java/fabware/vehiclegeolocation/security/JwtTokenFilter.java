package fabware.vehiclegeolocation.security;

import fabware.vehiclegeolocation.exception.JwtAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class JwtTokenFilter extends GenericFilterBean {

  private final JwtTokenProvider provider;

  public JwtTokenFilter(JwtTokenProvider provider) {
    this.provider = provider;
  }

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
    String token = provider.resolveToken((HttpServletRequest) request);

    try {
      if (token != null && provider.validateToken(token)) {
        Authentication authentication = provider.getAuthentication(token);
        if (authentication != null) {
          SecurityContextHolder.getContext().setAuthentication(authentication);
        }
      }
    } catch (JwtAuthenticationException e) {
      SecurityContextHolder.clearContext();
      ((HttpServletResponse) response).sendError(e.getHttpStatus().value());
      throw new JwtAuthenticationException("JWT token is expired or invalid");
    }
    chain.doFilter(request, response);
  }
}
