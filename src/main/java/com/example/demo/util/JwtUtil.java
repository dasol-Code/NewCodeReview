package com.example.demo.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

public class JwtUtil {

    public static String getUserName(String token, String secretKey) {
        return Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(token)
                .getBody().get("userName", String.class);
    }

    public static boolean isExpried(String token, String secretKey) {
        return Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(token)
                .getBody().getExpiration().before(new Date());
    }

    public static String createJwt(String userName, String secretKey, Long expireTime) {
        Claims claims = Jwts.claims();
        claims.put("userName", userName);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expireTime))
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }
}
