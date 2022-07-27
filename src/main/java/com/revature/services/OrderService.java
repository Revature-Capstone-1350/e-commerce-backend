package com.revature.services;

import com.revature.dtos.OrderDTO;
import com.revature.dtos.Principal;
import com.revature.dtos.ProductInfo;
import com.revature.exceptions.BadRequestException;
import com.revature.exceptions.NotFoundException;
import com.revature.exceptions.UnauthorizedException;
import com.revature.models.*;
import com.revature.repositories.*;
import com.revature.services.jwt.TokenService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {
    private final OrderRepository orderRepo;
    private final UserRepository userRepo;
    private final TokenService tokenService;
    private final AddressRepository addressRepo;
    private final OrderStatusRepository statusRepo;
    private final ProductRepository productRepo;

    public OrderService(OrderRepository orderRepo, UserRepository userRepo, TokenService tokenService, AddressRepository addressRepo, OrderStatusRepository statusRepo, ProductRepository productRepo) {
        this.orderRepo = orderRepo;
        this.userRepo = userRepo;
        this.tokenService = tokenService;
        this.addressRepo = addressRepo;
        this.statusRepo = statusRepo;
        this.productRepo = productRepo;
    }

    // get all orders
    public List<OrderDTO> getAllOrders() {
        return orderRepo.findAll().stream().map(OrderDTO::new).collect(Collectors.toList());
    }

    public OrderDTO getOrderByOrderId(int orderId) {
        Order order = orderRepo.findById(orderId)
                .orElseThrow(NotFoundException::new);
        return new OrderDTO(order);
    }

    public List<OrderDTO> getOrdersByUserId(String token, int userId) {
        Principal prin = tokenService.extractTokenDetails(token);
        User user = userRepo.findByUserIdAndEmailIgnoreCase(
                prin.getAuthUserId(),
                prin.getAuthUserEmail()
        ).orElseThrow(BadRequestException::new);
        if (user.getUserId() == userId ||
                user.getRole().getName().equalsIgnoreCase("Admin")
        )
        {
            User userToGetOrdersFrom = userRepo.findById(userId)
                    .orElseThrow(NotFoundException::new);
            return userToGetOrdersFrom.getUserOrders().stream()
                    .map(OrderDTO::new).collect(Collectors.toList());
        }
        throw new UnauthorizedException();

    }

    public OrderDTO placeOrder(String token, OrderDTO orderDetails) {
        User user;
        if (token != null) {
            Principal prin = tokenService.extractTokenDetails(token);
            user = userRepo.findByUserIdAndEmailIgnoreCase(
                    prin.getAuthUserId(),
                    prin.getAuthUserEmail()
            ).orElseThrow(BadRequestException::new);
        }
        else {
            user = userRepo.getById(2); // No login => Order goes on Tester McTesterson
        }
        Address address;
        try {
            address = addressRepo.findById(orderDetails.getAddress().getAddressId())
                    .orElseThrow(RuntimeException::new);
        } catch (Exception e) {
            address = new Address(
                    orderDetails.getAddress().getStreet(),
                    orderDetails.getAddress().getStreet2(),
                    orderDetails.getAddress().getCity(),
                    orderDetails.getAddress().getState(),
                    orderDetails.getAddress().getPostalCode()
            );
        }
        try {
            address = addressRepo.save(address);
        } catch (Exception e) {
            throw new BadRequestException();
        }
        Order order = new Order();
        order.setAddress(address);
        order.setUser(user);
        order.setStatus(statusRepo.getById(2)); // Pending
        List<Product> products = new ArrayList<>();
        for (ProductInfo info: orderDetails.getItems()) {
            Product product = productRepo.findById(info.getProductId())
                    .orElseThrow(BadRequestException::new);
            products.add(product);
        }
        order.setItems(products);
        orderRepo.save(order);
        return new OrderDTO(order);
    }

    public void deleteOrdersByProductId(int productId) {
        if(productRepo.getById(productId) == null) {
            throw new NotFoundException();
        }
        List<Order> orders = orderRepo.findAll();
        for (Order order: orders) {
            for (Product product: order.getItems()) {
                if (product.getProductId() == productId) {
                    orderRepo.delete(order);
                    break;
                }
            }
        }
    }

    /*
    able to
    place an order ( all orders without login go to Tester )

    modify an order ( set status )
    delete an order
    get all orders

     */
}
