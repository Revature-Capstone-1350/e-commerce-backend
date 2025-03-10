package com.revature.services.jwt;


import com.revature.dtos.Principal;
import com.revature.exceptions.BadRequestException;
import com.revature.exceptions.UnauthorizedException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.stereotype.Service;

import javax.crypto.Cipher;
import java.util.Date;

@Service
public class TokenService {

    private final JwtConfig jwtConfig;
    private static final String TRANSFORMATION = "RSA/ECB/OAEPWITHSHA-256ANDMGF1PADDING";
    // "RSA/ECB/PKCS1Padding" was marked insecure by Sonar Lint

    public TokenService(JwtConfig jwtConfig) {
        this.jwtConfig = jwtConfig;
    }

    public String generateToken(Principal subject) {
        long now = System.currentTimeMillis();
        long timeout = jwtConfig.getExpiration();

        JwtBuilder tokenBuilder = Jwts.builder() // IF adding claims, test that RSA key-size supports additional size
                                      .setIssuer("ecomapi")
                                      .claim("id", ""+subject.getAuthUserId())
                                      .claim("email",""+subject.getAuthUserEmail())
                                      .setIssuedAt(new Date(now))
                                      .setExpiration(new Date(now + timeout))
                                      .signWith(jwtConfig.getSigningKey(), jwtConfig.getSigAlg());

        return encryptRSA(tokenBuilder.compact());

    }

    public Principal extractTokenDetails(String token) {

        if (token == null || token.isEmpty()) {
            throw new UnauthorizedException();
        }

        Claims claims = Jwts.parserBuilder()
                .setSigningKey(jwtConfig.getSigningKey())
                .build()
                .parseClaimsJws(decryptRSA(token))
                .getBody();

        return new Principal(
                Integer.parseInt(claims.get("id", String.class)),
                claims.get("email",String.class));
    }

    private String encryptRSA(String data) {
        try {
            Cipher cipher = Cipher.getInstance(TRANSFORMATION);
            cipher.init(Cipher.ENCRYPT_MODE, jwtConfig.getPublicKey());
            byte[] encrypted = cipher.doFinal(data.getBytes());
            return Base64.encodeBase64String(encrypted);
        } catch (Exception t) {
            throw new BadRequestException(t);
        } // This was tested, so errors here are due to bad data
    }

    private String decryptRSA(String data) {
        try {
            Cipher cipher = Cipher.getInstance(TRANSFORMATION);
            cipher.init(Cipher.DECRYPT_MODE, jwtConfig.getPrivateKey());
            return new String(cipher.doFinal(Base64.decodeBase64(data)));
        } catch (Exception t) {
            throw new BadRequestException(t);
        } // This was tested, so errors here are due to bad data
    }
}
