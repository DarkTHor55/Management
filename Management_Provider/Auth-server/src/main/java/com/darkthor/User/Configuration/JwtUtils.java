package com.darkthor.User.Configuration;

import com.darkthor.User.Model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.Date;
import java.util.function.Function;
@Component
public class JwtUtils {
    private static final String SECRECT ="Doha6EyBEBrUX5fPTXuByGJqBf9VPFDFWjM6QgKTvzw";

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }
    public String extractEmail(String token) {
        return extractClaim(token, claims -> claims.get("email", String.class));
    }


    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
//        System.out.println(token);
        return Jwts.parserBuilder()
                .setSigningKey(getKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }


    public String generateToken(User user) {
        return Jwts.builder()
                .setSubject(user.getEmail())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 24 * 7))
                .claim("email", user.getEmail())
                .claim("role", user.getRole())
                .signWith(getKey(), SignatureAlgorithm.HS256)
                .compact();
    }

//    private static String createSecretKey() {
//        SecureRandom secureRandom = new SecureRandom();
//        byte[] secretBytes = new byte[32]; // 32 bytes = 256 bits
//        secureRandom.nextBytes(secretBytes);
//        Base64.Encoder encoder = Base64.getUrlEncoder().withoutPadding();
//        return encoder.encodeToString(secretBytes);
//    }
    public SecretKey getKey() {

        byte[] keyByte = Decoders.BASE64URL.decode(SECRECT);
        return Keys.hmacShaKeyFor(keyByte);
    }
}