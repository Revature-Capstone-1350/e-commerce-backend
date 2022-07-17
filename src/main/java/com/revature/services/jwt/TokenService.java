package com.revature.services.jwt;


import com.revature.dtos.Principal;
import com.revature.exceptions.TokenParseException;
import com.revature.exceptions.UnauthorizedException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TokenService {

    private final JwtConfig jwtConfig;

    public TokenService(JwtConfig jwtConfig) {
        this.jwtConfig = jwtConfig;
    }
    //public TokenService() {this.jwtConfig = new JwtConfig();}

    public String generateToken(Principal subject) {
        long now = System.currentTimeMillis();

        JwtBuilder tokenBuilder = Jwts.builder()
                                      .setIssuer("e-commerce-backend")
                                      .claim("id", ""+subject.getAuthUserId())
                                      .claim("email", subject.getAuthUserEmail())
                                      .setIssuedAt(new Date(now))
                                      .setExpiration(new Date(now + jwtConfig.getExpiration()))
                                      .signWith(jwtConfig.getSigAlg(), jwtConfig.getSigningKey());

        return tokenBuilder.compact();

    }

    public Principal extractTokenDetails(String token) {

        if (token == null || token.isEmpty()) {
            throw new UnauthorizedException();
        }

        try {
            Claims claims = Jwts.parser()
                                .setSigningKey(jwtConfig.getSigningKey())
                                .parseClaimsJws(token)
                                .getBody();

            return new Principal(Integer.parseInt(claims.get("id", String.class)) , claims.get("email", String.class));
        } catch (ExpiredJwtException e) {
            throw new TokenParseException();
        } catch (Exception e) {
            throw new UnauthorizedException();
        }
    }


}
