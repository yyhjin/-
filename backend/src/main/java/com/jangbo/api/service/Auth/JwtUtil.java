package com.jangbo.api.service.Auth;

import com.jangbo.db.entity.Customer;
import com.jangbo.db.entity.Seller;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
@Slf4j
@Component
public class JwtUtil {

    public final static long TOKEN_VALIDATION_SECOND = 30 * 60L;   //* 1000            // 30분
    public final static long REFRESH_TOKEN_VALIDATION_SECOND = 2 * 60 * 60L;  // * 1000  // 2시간

    final static public String ACCESS_TOKEN_NAME = "accessToken";
    final static public String REFRESH_TOKEN_NAME = "refreshToken";


    @Value("${spring.jwt.secret}") //
    private String SECRET_KEY;

    private Key getSigningKey(String secretKey) {
        byte[] keyBytes = secretKey.getBytes(StandardCharsets.UTF_8);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public Claims extractAllClaims(String token) throws ExpiredJwtException {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey(SECRET_KEY))
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public String getUsername(String token) {
        return extractAllClaims(token).get("username", String.class);
    }


    public Boolean isTokenExpired(String token) {
        final Date expiration = extractAllClaims(token).getExpiration();
        return expiration.before(new Date());
    }

    public Long getExpiration(String token) {
        // token 남은 유효시간
        Date expiration = Jwts.parserBuilder().setSigningKey(getSigningKey(SECRET_KEY)).build().parseClaimsJws(token).getBody().getExpiration();
        // 현재 시간
        Long now = new Date().getTime();
        return (expiration.getTime() - now);
    }

    //Seller
    public String generateToken(Seller seller) {
        return doGenerateToken(seller.getSellerId(), TOKEN_VALIDATION_SECOND);
    }

    //Customer
    public String generateToken(Customer customer) {
        return doGenerateToken(customer.getCustomerId(), TOKEN_VALIDATION_SECOND);
    }

    //Seller
    public String generateRefreshToken(Seller seller) {
        return doGenerateToken(seller.getSellerId(), REFRESH_TOKEN_VALIDATION_SECOND);
    }

    //Customer
    public String generateRefreshToken(Customer customer) {
        return doGenerateToken(customer.getCustomerId(), REFRESH_TOKEN_VALIDATION_SECOND);
    }
    public String doGenerateToken(String username, long expireTime) {

        Claims claims = Jwts.claims();
        claims.put("username", username);

        String jwt = Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expireTime*1000))
                .signWith(getSigningKey(SECRET_KEY), SignatureAlgorithm.HS256)
                .compact();

        return jwt;
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(getSigningKey(SECRET_KEY)).build().parseClaimsJws(token).getBody();
            return true;
        } catch (io.jsonwebtoken.security.SecurityException | MalformedJwtException e) {
            log.info("Invalid JWT Token", e);
        } catch (ExpiredJwtException e) {
            log.info("Expired JWT Token", e);
        } catch (UnsupportedJwtException e) {
            log.info("Unsupported JWT Token", e);
        } catch (IllegalArgumentException e) {
            log.info("JWT claims string is empty.", e);
        }
        return false;
    }



}