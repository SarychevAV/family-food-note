package ru.asarychev.family_food_note;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Component
public class JwtCore {
    @Value("${app.secret}")
    private String secret;
    @Value("${app.lifetime}")
    private int lifetime;

    public String generateToken(Authentication authentication) {
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        SecretKey key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
        return Jwts.builder().subject(userDetails.getUsername()).issuedAt(new Date())
                .expiration(new Date(new Date().getTime() + lifetime))
                .signWith(key)
                .compact();
    }

    public String getNameFromJwt(String token) {
        SecretKey key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));


        return Jwts
                .parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token)
                .getPayload().getSubject();
    }
}
