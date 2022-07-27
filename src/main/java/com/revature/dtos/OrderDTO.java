package com.revature.dtos;

import com.revature.models.Order;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO {
    private int orderId;
    private int userId;
    private AddressDTO address;
    private List<ProductInfo> items;
    private String status;

    public OrderDTO(Order order) {
        this.orderId = order.getOrderId();
        this.userId = order.getUser().getUserId();
        this.address = new AddressDTO(order.getAddress());
        this.items = order.getItems().stream().map(ProductInfo::new).collect(Collectors.toList());
        this.status = order.getStatus().getName();
    }
}
