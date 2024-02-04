package com.agnibank.agniBankAccount.utill;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;

@Component
public class JwtUtill {

    public static final String SECRET = "5367566B59703373367639792F423F4528482B4D6251655468576D5A71347437";
    public Claims validateToken(final String token) {
        System.out.println(token);
        Jws<Claims> claims = Jwts.parserBuilder().setSigningKey(getSignKey()).build().parseClaimsJws(token);
        Claims cl = claims.getBody();
        System.out.println("str" +cl);
        return cl;
    }



    private Key getSignKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
