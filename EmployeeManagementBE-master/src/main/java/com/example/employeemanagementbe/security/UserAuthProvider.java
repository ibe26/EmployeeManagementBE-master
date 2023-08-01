package com.example.employeemanagementbe.security;

import com.example.employeemanagementbe.Model.user.UserDto;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class UserAuthProvider {
    private final String secretKey= "SECRETKEYSECRETKEYSECRETKEYSECRETKEYSECRETKEY";
    @PostConstruct

    private Key key() {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(secretKey));
    }
    public String generateJwtToken(UserDto userDto) {

        final Date now=new Date();
        final Date validity=new Date(now.getTime()+1000*60*60*24);
        return Jwts.builder()
                .setSubject(userDto.getLogin())
                .setIssuedAt(now)
                .setExpiration(validity)
                .signWith(key(),SignatureAlgorithm.HS256)
                .compact();
    }
    public boolean validateJwtToken(String authToken) {
        try {
            Jwts.parserBuilder().setSigningKey(key()).build().parse(authToken);
            return true;
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }

    }
    public String getUsernameFromJWT(String token){
        Claims claims=Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
        return claims.getSubject();
    }

}
