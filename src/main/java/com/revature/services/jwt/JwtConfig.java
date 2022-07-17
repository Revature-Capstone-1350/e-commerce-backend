package com.revature.services.jwt;

import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Arrays;
import java.util.Base64;

@Component
public class JwtConfig {

    private PrivateKey privateKey;
    private PublicKey publicKey;

    @Value("${secrets.token-secret}")
    private String salt;

    @Value("${secrets.generate-rsa-keys}")
    private Boolean generateKeysRSA;
    // toggles whether to generate new keys

    @Value("${secrets.encrypt-rsa-key}")
    private String publicKeyRSA;

    @Value("${secrets.decrypt-rsa-key}")
    private String privateKeyRSA;

    @Value("#{24 * 60 * 60 * 1000}") // number of milliseconds in a day
    private int expiration;

    @Value("#{5 * 60 * 1000}") // number of milliseconds in five minutes
    private int expirationAdmin;

    private final SignatureAlgorithm sigAlg = SignatureAlgorithm.HS256;

    private Key signingKey;

    private String encryptRSA;
    private String decryptRSA;
    private Boolean genKeys;


    @PostConstruct
    public void createSigningKey() {
        byte[] saltyBytes = DatatypeConverter.parseBase64Binary(salt);
        signingKey = new SecretKeySpec(saltyBytes, sigAlg.getJcaName());
        this.genKeys = generateKeysRSA;
        if (genKeys) {
            try {
                long startTime = System.nanoTime();
                System.out.println("Generating RSA keys. This may take a few minutes.");
                KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
                keyGen.initialize(8192);
                // 4096  only supported email length 240
                // 8192 supports email length 632
                KeyPair pair = keyGen.generateKeyPair();
                this.privateKey = pair.getPrivate();
                this.publicKey = pair.getPublic();
                // The following two lines are used to get RSA keys.
//                System.out.println(Base64.getEncoder().encodeToString(pair.getPrivate().getEncoded()));
//                System.out.println(Base64.getEncoder().encodeToString(pair.getPublic().getEncoded()));
                long totalTime = System.nanoTime() - startTime;
                System.out.println("RSA key gen took " + (totalTime/Math.pow(1000,3)) + " seconds");
            } catch (Throwable t) {
                throw new RuntimeException(t);
            }
        }
        else {
            this.publicKey = (PublicKey) loadPublicKey(publicKeyRSA);
            this.privateKey = (PrivateKey) loadPrivateKey(privateKeyRSA);
        }
    }

    public int getExpiration() {
        return expiration;
    }

    public int getExpirationAdmin() { return expirationAdmin; }

    public SignatureAlgorithm getSigAlg() {
        return sigAlg;
    }

    // Getters
    public Key getSigningKey() {
        return signingKey;
    }
    public PrivateKey getPrivateKey() { return privateKey; }
    public PublicKey getPublicKey() { return publicKey; }

    public static Key loadPublicKey(String stored) {
        try {
            byte[] data = Base64.getDecoder().decode((stored.getBytes()));
            X509EncodedKeySpec spec = new X509EncodedKeySpec(data);
            KeyFactory fact = KeyFactory.getInstance("RSA");
            return fact.generatePublic(spec);
        } catch (Throwable t) {
            throw new RuntimeException(t);
        }
    }
    public static Key loadPrivateKey(String key64) {
        try {
            byte[] clear = Base64.getDecoder().decode(key64.getBytes());
            PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(clear);
            KeyFactory fact = KeyFactory.getInstance("RSA");
            PrivateKey priv = fact.generatePrivate(keySpec);
            Arrays.fill(clear, (byte) 0);
            return priv;
        } catch (Throwable t) {
            throw new RuntimeException(t);
        }

    }

}
