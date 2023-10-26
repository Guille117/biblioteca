package com.example.Biblioteca.seguridad.jwt;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Date;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Value;

@Component
public class SecurityUtils {
    
    @Value("${jwt.secret.key}")
    private String secretKey;
    @Value("${jwt.time.expiration}")
    private String tiempo;


    public Key obtenerFirma(){
        byte[] cadena = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(cadena);
    }

    public boolean isValidKey(String token){
        try{
            Jwts.parser()
            .setSigningKey(obtenerFirma())
            .build()
            .parseSignedClaims(token)
            .getPayload();
            return true;
        }catch(Exception e){
            return false;
        }
    }

    public String generarToken(String usu){
        return Jwts.builder()
        .subject(usu)                                          // usuario
        .issuedAt(new Date(System.currentTimeMillis()))        // indicamos el tiempo de creación  -> ahora
        .expiration(new Date(System.currentTimeMillis() + Long.parseLong(tiempo)))      // tiempo finalizacion
        .signWith(obtenerFirma())
        .compact();
    }

    public Claims extractAllClaims(String token){
        return Jwts.parser()
        .setSigningKey(obtenerFirma())
        .build()
        .parseSignedClaims(token)
        .getPayload();
    }

    public<T> T getClaim(String token, Function<Claims, T> claimsFunction){
        Claims claims = extractAllClaims(token);
        return claimsFunction.apply(claims);
    }

    public String getUserNameFromToken(String token){
        return getClaim(token,Claims::getSubject);
    }
}
