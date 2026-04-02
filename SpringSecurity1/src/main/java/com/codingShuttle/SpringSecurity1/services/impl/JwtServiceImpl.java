package com.codingShuttle.SpringSecurity1.services.impl;

import com.codingShuttle.SpringSecurity1.entities.UserEntity;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;

@Service
public class JwtServiceImpl {

    Logger log = LoggerFactory.getLogger(JwtServiceImpl.class);

    @Value("${jwt.secretKey}")
    private String jwtSecretKey;

    private SecretKey getJwtSecretKey() {
        return Keys.hmacShaKeyFor(jwtSecretKey.getBytes());
    }

    ;

    public String generateJwtToken(UserEntity userEntity) {
        log.info("JwtServiceImpl generateJwtToken userEntity: {}", userEntity);
        return Jwts.builder()
                .subject(userEntity.getId().toString())
                .claim("email", userEntity.getEmail())
                .claim("username", userEntity.getUsername())
                .claim("role", userEntity.getRole())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + 60 * 1000))
                .signWith(getJwtSecretKey())
                .compact();

    }

    public Long getUserIdFromJwtToken(String token) {
        log.info("JwtServiceImpl getUserIdFromJwtToken");
        Claims claims = Jwts.parser()
                .verifyWith(getJwtSecretKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();

        log.info("getUserIdFromJwtToken claims: {}", claims);
        return Long.valueOf(claims.getSubject());
    }


}
