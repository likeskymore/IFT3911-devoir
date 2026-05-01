package com.example.demo.auth;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.example.demo.account.UserService;
import com.example.demo.account.models.User;

import java.util.Date;

@Component
public class JwtUtil {

    private final UserService userService;

    @Value("${secret.key}")
    private String SECRET_KEY;

    public JwtUtil(UserService userService) {
        this.userService = userService;
    }

    public String generateToken(String email) {
        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60)) 
                .signWith(Keys.hmacShaKeyFor(SECRET_KEY.getBytes()))
                .compact();
    }

    public User getUserFromToken(String token) {
        String email = Jwts.parserBuilder()
                .setSigningKey(Keys.hmacShaKeyFor(SECRET_KEY.getBytes()))
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();

        // Look up the full User from DB
        return userService.findByEmail(email); // returns full user with role, id, etc.
    }
}
