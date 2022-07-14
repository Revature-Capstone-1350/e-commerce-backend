package com.revature.services.jwt;

import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;

@Component
public class JwtConfig {

    @Value("${secrets.token-secret}")
    private String salt;

    @Value("#{24 * 60 * 60 * 1000}") // number of milliseconds in a day
    private int expiration;

    private final SignatureAlgorithm sigAlg = SignatureAlgorithm.HS256;

    private Key signingKey;

    @PostConstruct
    public void createSigningKey() {
        byte[] saltyBytes = DatatypeConverter.parseBase64Binary(salt);
        signingKey = new SecretKeySpec(saltyBytes, sigAlg.getJcaName());
    }

    public int getExpiration() {
        return expiration;
    }

    public SignatureAlgorithm getSigAlg() {
        return sigAlg;
    }

    public Key getSigningKey() {
        return signingKey;
    }

//    public JwtConfig() {
//        this.salt = "revature";
//        byte[] saltyBytes = DatatypeConverter.parseBase64Binary(salt);
//        this.signingKey = new SecretKeySpec(saltyBytes, sigAlg.getJcaName());
//        expiration = 24 * 60 * 60 * 1000;
//    }
}
