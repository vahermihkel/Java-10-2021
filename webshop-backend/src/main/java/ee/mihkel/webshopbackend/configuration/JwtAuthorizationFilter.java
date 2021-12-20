package ee.mihkel.webshopbackend.configuration;

import io.jsonwebtoken.Jwts;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Log4j2
public class JwtAuthorizationFilter extends BasicAuthenticationFilter {

    public JwtAuthorizationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
//        super.doFilterInternal(request, response, chain);
        String token = request.getHeader("Authorization");
        if (token != null && token.startsWith("Bearer ")) {
            log.info("Starting to decode token");
            UsernamePasswordAuthenticationToken authentication;
            authentication = new UsernamePasswordAuthenticationToken(
                    null,
                    null,
                    null
            );
//            Jwts.parser()
//                    .parseClaimsJws(token.replace("Bearer ", ""));

            SecurityContextHolder.getContext().setAuthentication(authentication);
            log.info("logged in");
        }

        chain.doFilter(request,response);
    }
}
