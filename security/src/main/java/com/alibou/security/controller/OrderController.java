package com.alibou.security.controller;

import com.alibou.security.dto.CreateOrderRequest;
import com.alibou.security.entity.OrderSum;
import com.alibou.security.service.OrderService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Validated
@Tag(name = "Order RESTfull", description = " 訂單 CRUD")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/users/{userId}/orders")
    public ResponseEntity<List<OrderSum>> getAllOrder(@PathVariable Integer userId) {
        List<OrderSum> orderSumList = orderService.getOrdersById(userId);

        return ResponseEntity.ok().body(orderSumList);
    }

    @PostMapping("/users/{userId}/orders")
    public ResponseEntity<OrderSum> createOrder(@PathVariable Integer userId,
                                                @RequestBody @Valid CreateOrderRequest createOrderRequest) {
        OrderSum ordersum = orderService.createOrder(userId, createOrderRequest);

        return ResponseEntity.status(HttpStatus.CREATED).body(ordersum);
    }
}
