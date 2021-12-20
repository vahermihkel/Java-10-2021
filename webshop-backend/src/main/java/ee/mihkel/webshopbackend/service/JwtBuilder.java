package ee.mihkel.webshopbackend.service;

import ee.mihkel.webshopbackend.model.Person;
import ee.mihkel.webshopbackend.model.output.AuthData;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.temporal.TemporalUnit;
import java.util.Date;

@Service
public class JwtBuilder {

    @Value("${jwt.signingkey}")
    private String jwtSecretKey;

    public AuthData createJwtAuthToken(Person person) {
        LocalDateTime newTime = LocalDateTime.now().plusHours(2);
        Instant instant = newTime.toInstant(ZoneOffset.UTC);
        Date expiryDate = Date.from(instant);

        byte[] signingKey = jwtSecretKey.getBytes();
        String token = Jwts.builder()
                .signWith(Keys.hmacShaKeyFor(signingKey), SignatureAlgorithm.HS512)
                .setIssuer("webshop")
                .setSubject("Email: " + person.getEmail())
                .setExpiration(expiryDate)
                .compact();
        AuthData authData = new AuthData();
        authData.setToken(token);
        authData.setExpirationDate(expiryDate);

        return authData;
    }
}
