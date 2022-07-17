package com.revature.services.jwt;


import com.revature.dtos.Principal;
import com.revature.exceptions.BadRequestException;
import com.revature.exceptions.TokenParseException;
import com.revature.exceptions.UnauthorizedException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.stereotype.Service;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

@Service
public class TokenService {

    private final JwtConfig jwtConfig;

    public TokenService(JwtConfig jwtConfig) {
        this.jwtConfig = jwtConfig;
    }

    public String generateToken(Principal subject) {
        long now = System.currentTimeMillis();
        long timeout = (subject.getIsAdmin()? jwtConfig.getExpirationAdmin() : jwtConfig.getExpiration());

        JwtBuilder tokenBuilder = Jwts.builder()
                                      .setIssuer("ecomapi")
                                      .claim("id", ""+subject.getAuthUserId())
                                      .claim("email",""+subject.getAuthUserEmail())
                                      .setIssuedAt(new Date(now))
                                      .setExpiration(new Date(now + timeout))
                                      .signWith(jwtConfig.getSigAlg(), jwtConfig.getSigningKey());

        return encryptRSA(tokenBuilder.compact());

    }

    public Principal extractTokenDetails(String token) {

        if (token == null || token.isEmpty()) {
            throw new UnauthorizedException();
        }
        Claims claims = Jwts.parser()
                .setSigningKey(jwtConfig.getSigningKey())
                .parseClaimsJws(decryptRSA(token))
                .getBody();

        return new Principal(
                Integer.parseInt(claims.get("id", String.class)),
                claims.get("email",String.class));
    }

    private String encryptRSA(String data) {
        try {
            Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            cipher.init(Cipher.ENCRYPT_MODE, jwtConfig.getPublicKey());
            byte[] encrypted = cipher.doFinal(data.getBytes());
            return Base64.encodeBase64String(encrypted);
        } catch (Throwable t) {
            throw new BadRequestException(t);
        } // This was tested, so errors here are due to bad data
    }
    private String decryptRSA(String data) {
        try {
            Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            cipher.init(Cipher.DECRYPT_MODE, jwtConfig.getPrivateKey());
            return new String(cipher.doFinal(Base64.decodeBase64(data)));
        } catch (Throwable t) {
            throw new BadRequestException(t);
        } // This was tested, so errors here are due to bad data
    }
}
