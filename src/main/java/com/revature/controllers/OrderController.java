package com.revature.controllers;

import com.revature.annotations.AdminOnly;
import com.revature.dtos.OrderDTO;
import com.revature.services.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/order")
public class OrderController {

    private final OrderService orderService;
    private static final String AUTHORIZATION = "Authorization";

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @AdminOnly
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(produces = "application/json")
    public List<OrderDTO> getAllOrders() {
        return orderService.getAllOrders();
    }

    @AdminOnly
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(path = "/orderid/{orderId}", produces = "application/json")
    public OrderDTO getOrderByOrderId(@PathVariable int orderId) {
        return orderService.getOrderByOrderId(orderId);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(path = "/userid/{orderId}", produces = "application/json")
    public List<OrderDTO> getOrdersByUserId(
            @RequestHeader(AUTHORIZATION) String token,
            @PathVariable int userId
    ) {
        return orderService.getOrdersByUserId(token, userId);
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping(path = "/place", consumes = "application/json", produces = "application/json")
    public OrderDTO placeOrder(
            @RequestHeader(value = AUTHORIZATION, required = false) String token,
            @RequestBody OrderDTO orderDetails
    ) {
        return orderService.placeOrder(token, orderDetails);
    }

}
