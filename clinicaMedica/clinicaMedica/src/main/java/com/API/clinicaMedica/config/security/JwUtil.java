package com.API.clinicaMedica.config.security;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Date;
@Component
public class JwUtil {
    // use uma chave forte em produção (env var). Aqui exemplo simples:
    private final Key key = Keys.hmacShaKeyFor("minha-chave-secreta-muito-grande-para-demo-1234567890".getBytes());
    private final long expirationMs = 1000 * 60 * 60; // 1h

    public String generateToken(String subject, String role) {
        return Jwts.builder()
                .setSubject(subject) // normalmente id ou email
                .claim("role", role)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expirationMs))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }

    public Claims getClaims(String token) {
        return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
    }
}



