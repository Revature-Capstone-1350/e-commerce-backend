package com.revature.user;

import com.revature.dtos.Principal;
import com.revature.services.jwt.TokenService;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest // Tells Spring we need to have an entire application context with everything set up and ready to go
@AutoConfigureMockMvc // configures mockMvc
@DirtiesContext
public class JWTsizeTest {

    private final MockMvc mockMvc;
    private final TokenService tokenService;

    @Autowired
    public JWTsizeTest(MockMvc mockMvc, TokenService tokenService) {
        this.mockMvc = mockMvc;
        this.tokenService = tokenService;
    }

    @Test
    void test_max_token_size_fits_in_RSA_keysize() {
        String size255Username = "";
        for (int i=0; i<255/5; i++) {
            size255Username += "@@@@@";
        }
        assertEquals(255, size255Username.length());
        Principal prin = new Principal(Integer.MAX_VALUE, size255Username);
        String token = tokenService.generateToken(prin);
    }

    @Test
    void test_current_max_email_size_that_fits_in_RSA_keysize() {

        // Bisection method
        final int fwdStepSize = 300;
        int pointA;
        int pointB = 0;
        Principal prin;
        // find initial interval
        while (true) {
            try {
                pointB += fwdStepSize;
                prin = new Principal(
                        Integer.MAX_VALUE,
                        StringUtils.repeat("@", pointB));
                tokenService.generateToken((prin));
            } catch (Exception e) {
                break;
            }
        }
        pointA = pointB - fwdStepSize;
        // pointA works, but pointB is too large

        // Bisection method
        int pointC = 0;
        boolean cantEncrypt;
        while ((pointB - pointA) > 1) {
            cantEncrypt = false;
            pointC = (int) Math.round((double) (pointB + pointA) / 2);
            prin = new Principal(
                    Integer.MAX_VALUE,
                    StringUtils.repeat("@", pointC));
            try {
                tokenService.generateToken((prin));
            } catch(Exception e) {
                cantEncrypt = true;
            }
            if(cantEncrypt) { // pointB is too large
                pointB = pointC; // pointC becomes new upper limit
            }
            else {
                pointA = pointC; // pointC becomes new lower limit
            }
        }

        assertEquals(589, pointA); // Maximum email size is 589 characters
    }

}
