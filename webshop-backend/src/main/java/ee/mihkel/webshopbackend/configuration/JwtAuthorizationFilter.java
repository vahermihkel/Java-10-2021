package ee.mihkel.webshopbackend.configuration;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Key;
import java.util.Date;

@Log4j2
public class JwtAuthorizationFilter extends BasicAuthenticationFilter {

//    private Key signingKey = Keys.secretKeyFor(SignatureAlgorithm.HS512);
    private String secretKey;

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public JwtAuthorizationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String token = request.getHeader("Authorization");
        if (token != null && token.startsWith("Bearer ")) {
            log.info("Starting to decode token");

            try {
                Claims claims = Jwts.parser()
                        .setSigningKey(secretKey.getBytes())
                        .parseClaimsJws(token.replace("Bearer ", ""))
                        .getBody();

                String email = claims.getSubject();
                String issuer = claims.getIssuer();
                Date expiration = claims.getExpiration();
                if (issuer.equals("webshop")) {
                    UsernamePasswordAuthenticationToken authentication;
                    authentication = new UsernamePasswordAuthenticationToken(
                            email,
                            null,
                            null
                    );

                    SecurityContextHolder.getContext().setAuthentication(authentication);
                    log.info("logged in");

                }
            } catch (ExpiredJwtException e) {
                log.error("Aegunud JSON WEB TOKEN {} {}", token, e.getMessage());
            } catch (UnsupportedJwtException e) {
                log.error("Mitteühilduv JSON WEB TOKEN {} {}", token, e.getMessage());
            } catch (MalformedJwtException e) {
                log.error("Modifitseeritud JSON WEB TOKEN {} {}", token, e.getMessage());
            } catch (SignatureException e) {
                log.error("Signature vale JSON WEB TOKEN {} {}", token, e.getMessage());
            } catch (IllegalArgumentException e) {
                log.error("Argumendid valed JSON WEB TOKEN {} {}", token, e.getMessage());
            }
        }

        chain.doFilter(request,response);
    }
}
