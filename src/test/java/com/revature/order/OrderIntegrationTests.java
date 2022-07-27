package com.revature.order;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.controllers.AuthController;
import com.revature.dtos.Principal;
import com.revature.models.Order;
import com.revature.models.User;
import com.revature.repositories.OrderRepository;
import com.revature.repositories.UserRepository;
import com.revature.repositories.UserRoleRepository;
import com.revature.services.AuthService;
import com.revature.services.jwt.TokenService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.hamcrest.Matchers.hasItem;
import static org.junit.Assert.assertFalse;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest // Tells Spring we need to have an entire application context with everything set up and ready to go
@AutoConfigureMockMvc // configures mockMvc
@DirtiesContext
public class OrderIntegrationTests {
    private final MockMvc mockMvc;
    // allows to send HTTP requests and assert about their responses

    private final ObjectMapper mapper;
    private final UserRepository userRepo;
    private final UserRoleRepository roleRepo;
    private final OrderRepository orderRepo;
    private final AuthService authService;
    private final AuthController authCtrl;
    private final TokenService tokenService;
    private final String GET_ALL = "/api/order";
    private final String GET_BY_ORDER_ID = "/api/order/orderid/";
    private final String GET_BY_USER_ID = "/api/order/userid/";
    private final String CREATE_PRODUCT_PATH = "/api/product/createproduct";
    private final String POST_REVIEW_PATH = "/api/product/rating/" + 1;
    private final String CONTENT_TYPE = "application/json";
    final String username = "Admin@SkyView.com";
    final String password = "Admin12@";

    @Autowired
    public OrderIntegrationTests(MockMvc mockMvc, ObjectMapper mapper, UserRepository userRepo, UserRoleRepository roleRepo, OrderRepository orderRepo, AuthService authService, AuthController authCtrl, TokenService tokenService) {
        this.mockMvc = mockMvc;
        this.mapper = mapper;
        this.userRepo = userRepo;
        this.roleRepo = roleRepo;
        this.orderRepo = orderRepo;
        this.authService = authService;
        this.authCtrl = authCtrl;
        this.tokenService = tokenService;
    }

    @Test
    void test_admin_can_get_all_orders_returns_200() throws Exception{
        User user = userRepo.findByEmailIgnoreCaseAndPassword(username, authService.generatePassword(password)).orElseThrow(RuntimeException::new);
        String token = tokenService.generateToken(new Principal(user));

        mockMvc.perform(get(GET_ALL).header("Authorization", token))
                        .andExpect(status().isOk())
                        .andExpect(header().string("content-type", CONTENT_TYPE))
                        .andExpect(header().string("Access-Control-Allow-Origin", "*"))
                        .andExpect(header().string("Access-Control-Allow-Methods", "*"))
                        .andExpect(header().string("Access-Control-Allow-Headers", "*"))
                        .andReturn();
    }

    @Test
    void test_admin_can_get_order_by_order_id() throws Exception {
        User user = userRepo.findByEmailIgnoreCaseAndPassword(username, authService.generatePassword(password)).orElseThrow(RuntimeException::new);
        String token = tokenService.generateToken(new Principal(user));

        final String GET_PATH = GET_BY_ORDER_ID + 1;
        mockMvc.perform(get(GET_PATH).header("Authorization", token))
                .andExpect(status().isOk())
                .andExpect(header().string("content-type", CONTENT_TYPE))
                .andExpect(header().string("Access-Control-Allow-Origin", "*"))
                .andExpect(header().string("Access-Control-Allow-Methods", "*"))
                .andExpect(header().string("Access-Control-Allow-Headers", "*"))
                .andReturn();
    }

    @Test
    void test_admin_can_get_order_by_user_id() throws Exception {
        User adminUser = userRepo.findByEmailIgnoreCaseAndPassword(username, authService.generatePassword(password)).orElseThrow(RuntimeException::new);
        String adminToken = tokenService.generateToken(new Principal(adminUser));

        final int ORDER_ID = 4;
        Order order = orderRepo.findById(ORDER_ID).orElseThrow(RuntimeException::new);
        User user = userRepo.findById(order.getUser().getUserId())
                .orElseThrow(RuntimeException::new);

        final String GET_PATH = GET_BY_USER_ID + (user.getUserId()+1);
        mockMvc.perform(get(GET_PATH).header("Authorization", adminToken))
                .andExpect(status().isOk())
                .andExpect(header().string("content-type", CONTENT_TYPE))
                .andExpect(header().string("Access-Control-Allow-Origin", "*"))
                .andExpect(header().string("Access-Control-Allow-Methods", "*"))
                .andExpect(header().string("Access-Control-Allow-Headers", "*"))
                .andReturn();
    }

    @Test
    void test_user_cannot_get_own_order_by_order_id() throws Exception {
        final int ORDER_ID = 4;
        Order order = orderRepo.findById(ORDER_ID).orElseThrow(RuntimeException::new);
        User user = userRepo.findById(order.getUser().getUserId())
                .orElseThrow(RuntimeException::new);
        String token = tokenService.generateToken(new Principal(user));

        final String GET_PATH = GET_BY_ORDER_ID + ORDER_ID;
        mockMvc.perform(get(GET_PATH).header("Authorization", token))
                .andExpect(status().isForbidden())
                .andExpect(header().string("content-type", CONTENT_TYPE))
                .andExpect(header().string("Access-Control-Allow-Origin", "*"))
                .andExpect(header().string("Access-Control-Allow-Methods", "*"))
                .andExpect(header().string("Access-Control-Allow-Headers", "*"))
                .andReturn();
    }

    @Test
    void test_user_can_get_own_orders_by_own_user_id() throws Exception {
        final int ORDER_ID = 4;
        Order order = orderRepo.findById(ORDER_ID).orElseThrow(RuntimeException::new);
        User user = userRepo.findById(order.getUser().getUserId())
                .orElseThrow(RuntimeException::new);
        String token = tokenService.generateToken(new Principal(user));

        final String GET_PATH = GET_BY_USER_ID + user.getUserId();
        mockMvc.perform(get(GET_PATH).header("Authorization", token))
                .andExpect(status().isOk())
                .andExpect(header().string("content-type", CONTENT_TYPE))
                .andExpect(header().string("Access-Control-Allow-Origin", "*"))
                .andExpect(header().string("Access-Control-Allow-Methods", "*"))
                .andExpect(header().string("Access-Control-Allow-Headers", "*"))
                .andReturn();
    }
    @Test
    void test_user_cannot_get_others_orders_by_own_user_id() throws Exception {
        final int ORDER_ID = 4;
        Order order = orderRepo.findById(ORDER_ID).orElseThrow(RuntimeException::new);
        User user = userRepo.findById(order.getUser().getUserId())
                .orElseThrow(RuntimeException::new);
        String token = tokenService.generateToken(new Principal(user));

        final String GET_PATH = GET_BY_USER_ID + (user.getUserId()+1);
        mockMvc.perform(get(GET_PATH).header("Authorization", token))
                .andExpect(status().isUnauthorized())
                .andExpect(header().string("content-type", CONTENT_TYPE))
                .andExpect(header().string("Access-Control-Allow-Origin", "*"))
                .andExpect(header().string("Access-Control-Allow-Methods", "*"))
                .andExpect(header().string("Access-Control-Allow-Headers", "*"))
                .andReturn();
    }



}
