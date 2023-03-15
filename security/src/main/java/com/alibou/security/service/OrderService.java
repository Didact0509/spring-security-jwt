package com.alibou.security.service;

import com.alibou.security.dto.CreateOrderRequest;
import com.alibou.security.entity.OrderSum;

import java.util.List;

public interface OrderService {

    List<OrderSum> getOrdersById(Integer userId);

    OrderSum createOrder(Integer userId, CreateOrderRequest createOrderRequest);
}
