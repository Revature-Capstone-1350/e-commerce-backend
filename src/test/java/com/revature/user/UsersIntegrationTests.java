package com.revature.user;

import com.revature.dtos.Principal;
import com.revature.models.User;
import com.revature.repositories.UserRepository;
import com.revature.services.AuthService;
import com.revature.services.jwt.TokenService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest // Tells Spring we need to have an entire application context with everything set up and ready to go
@AutoConfigureMockMvc // configures mockMvc
public class UsersIntegrationTests {

    private final MockMvc mockMvc;
    private final UserRepository userRepo;
    private final AuthService authService;
    private final TokenService tokenService;
    private final String GET_ALL_PATH = "/api/users";
    private final String CONTENT_TYPE = "application/json";
    final String username = "Admin@SkyView.com";
    final String password = "Admin12@";

    @Autowired
    public UsersIntegrationTests(MockMvc mockMvc, UserRepository userRepo, AuthService authService, TokenService tokenService) {
        this.mockMvc = mockMvc;
        this.userRepo = userRepo;
        this.authService = authService;
        this.tokenService = tokenService;
    }

    @Test
    void admin_can_get_all_users() throws Exception {
        User user = userRepo.findByEmailIgnoreCaseAndPassword(username, authService.generatePassword(password)).orElseThrow(RuntimeException::new);
        String token = tokenService.generateToken(new Principal(user));

        mockMvc.perform(get(GET_ALL_PATH).header("Authorization", token))
                .andExpect(status().isOk())
                .andExpect(header().string("content-type", CONTENT_TYPE))
                .andExpect(header().string("Access-Control-Allow-Origin", "*"))
                .andExpect(header().string("Access-Control-Allow-Methods", "*"))
                .andExpect(header().string("Access-Control-Allow-Headers", "*"))
                .andReturn();
    }

    @Test
    void non_admin_cannot_get_all_users() throws Exception {
        User user = userRepo.findById(4).orElseThrow(RuntimeException::new);
        String token = tokenService.generateToken(new Principal(user));

        mockMvc.perform(get(GET_ALL_PATH).header("Authorization", token))
                .andExpect(status().isForbidden())
                .andExpect(header().string("content-type", CONTENT_TYPE))
                .andExpect(header().string("Access-Control-Allow-Origin", "*"))
                .andExpect(header().string("Access-Control-Allow-Methods", "*"))
                .andExpect(header().string("Access-Control-Allow-Headers", "*"))
                .andReturn();
    }

    @Test
    void admin_can_get_user_by_id() throws Exception {
        User user = userRepo.findByEmailIgnoreCaseAndPassword(username, authService.generatePassword(password)).orElseThrow(RuntimeException::new);
        String token = tokenService.generateToken(new Principal(user));

        final String GET_BY_ID_PATH = GET_ALL_PATH + "/5";
        mockMvc.perform(get(GET_ALL_PATH).header("Authorization", token))
                .andExpect(status().isOk())
                .andExpect(header().string("content-type", CONTENT_TYPE))
                .andExpect(header().string("Access-Control-Allow-Origin", "*"))
                .andExpect(header().string("Access-Control-Allow-Methods", "*"))
                .andExpect(header().string("Access-Control-Allow-Headers", "*"))
                .andReturn();
    }

    @Test
    void user_cannot_find_self_by_id() throws Exception {
        final int USER_ID = 4;
        User user = userRepo.findById(USER_ID).orElseThrow(RuntimeException::new);
        String token = tokenService.generateToken(new Principal(user));

        final String GET_BY_ID_PATH = GET_ALL_PATH + "/" + USER_ID;
        mockMvc.perform(get(GET_ALL_PATH).header("Authorization", token))
                .andExpect(status().isForbidden())
                .andExpect(header().string("content-type", CONTENT_TYPE))
                .andExpect(header().string("Access-Control-Allow-Origin", "*"))
                .andExpect(header().string("Access-Control-Allow-Methods", "*"))
                .andExpect(header().string("Access-Control-Allow-Headers", "*"))
                .andReturn();
    }

    @Test
    void user_cannot_find_different_user_by_id() throws Exception {
        final int USER_ID = 4;
        User user = userRepo.findById(USER_ID).orElseThrow(RuntimeException::new);
        String token = tokenService.generateToken(new Principal(user));

        final int DIFFERENT_USER_ID = USER_ID + 1;

        final String GET_BY_ID_PATH = GET_ALL_PATH + "/" + DIFFERENT_USER_ID;
        mockMvc.perform(get(GET_ALL_PATH).header("Authorization", token))
                .andExpect(status().isForbidden())
                .andExpect(header().string("content-type", CONTENT_TYPE))
                .andExpect(header().string("Access-Control-Allow-Origin", "*"))
                .andExpect(header().string("Access-Control-Allow-Methods", "*"))
                .andExpect(header().string("Access-Control-Allow-Headers", "*"))
                .andReturn();
    }
}
