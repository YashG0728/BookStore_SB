package com.example.bookstore.service;

import com.example.bookstore.dto.OrderDto;
import com.example.bookstore.model.OrderModel;

import java.util.List;


public interface IOrderService {
    OrderModel orderItem(OrderDto orderDTO);

    OrderModel getOrder(int orderId);

    OrderModel cancelOrder(int orderId, String token);

    List<OrderModel> getAllData();

    OrderModel updateOrderRecord(Integer id, OrderDto dto);
}
