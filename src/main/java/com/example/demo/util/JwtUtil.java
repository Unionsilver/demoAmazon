package com.example.demo.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SecurityException;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtUtil {

    private Key key;

    @PostConstruct
    public void init() {
        try {
            // Инициализация ключа для генерации и проверки токенов JWT
            key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
        } catch (SecurityException e) {
            e.printStackTrace();
        }
    }

    // Метод для извлечения имени пользователя из токена
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    // Метод для извлечения даты истечения срока действия токена
    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        // Извлечение всех полей из токена JWT
        return Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody();
    }

    private Boolean isTokenExpired(String token) {
        // Проверка, истек ли срок действия токена
        return extractExpiration(token).before(new Date());
    }

    // Метод для генерации токена JWT на основе имени пользователя
    public String generateToken(String username) {
        Map<String, Object> claims = new HashMap<>();
        // Создание токена JWT с учетом указанных полей
        return createToken(claims, username);
    }

    private String createToken(Map<String, Object> claims, String subject) {
        // Установка срока действия токена и его подпись
        long expirationTimeInMilliseconds = 1000 * 60 * 60 * 10; // 10 hours
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expirationTimeInMilliseconds))
                .signWith(key)
                .compact();
    }

    // Метод для проверки валидности токена JWT
    public Boolean validateToken(String token, String username, SecretKey secretKey) {
        // Извлечение имени пользователя из токена и проверка его валидности
        final String extractedUsername = extractUsername(token);
        return (extractedUsername.equals(username) && !isTokenExpired(token));
    }
}
