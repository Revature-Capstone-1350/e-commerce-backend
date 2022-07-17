package com.revature.services.jwt;

import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.*;

@Component
public class JwtConfig {

    private final PrivateKey privateKey;
    private final PublicKey publicKey;

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

    // Getters
    public Key getSigningKey() {
        return signingKey;
    }
    public PrivateKey getPrivateKey() { return privateKey; }
    public PublicKey getPublicKey() { return publicKey; }

    public JwtConfig() {
        try {
            KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
            keyGen.initialize(8192);
            // 4096  only supported email length 240
            // 8192 supports email length 632
            KeyPair pair = keyGen.generateKeyPair();
            this.privateKey = pair.getPrivate();
            this.publicKey = pair.getPublic();
        } catch (Throwable t) {
            throw new RuntimeException(t);
        }
    }

    private void RSAKeyPairGenerator() throws NoSuchAlgorithmException {

    }
}
