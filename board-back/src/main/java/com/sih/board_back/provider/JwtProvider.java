package com.sih.board_back.provider;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtProvider {

    @Value("${secret-key}")
    private String secretKey;

    public String create (String email) {

        Date expriedDate = Date.from(Instant.now().plus(1,ChronoUnit.HOURS));

        String jwt = Jwts.builder().signWith(SignatureAlgorithm.ES256, secretKey)
        .setSubject(email).setIssuedAt(new Date()).setExpiration(expriedDate).compact();

        return jwt;

    }

    public String validate(String jwt) {

        Claims claims = null;

        try {
            claims = Jwts.parser().setSigningKey(secretKey)
            .parseClaimsJws(jwt).getBody();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        return claims.getSubject();
    }
}
