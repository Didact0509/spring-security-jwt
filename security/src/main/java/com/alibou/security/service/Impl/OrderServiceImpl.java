package com.alibou.security.service.Impl;

import com.alibou.security.dao.OrderItemRepository;
import com.alibou.security.dao.OrderRepository;
import com.alibou.security.dao.ProductRepository;
import com.alibou.security.dto.CreateOrderRequest;
import com.alibou.security.entity.OrderItem;
import com.alibou.security.entity.OrderSum;
import com.alibou.security.entity.Product;
import com.alibou.security.model.BuyItem;
import com.alibou.security.service.OrderService;
import com.alibou.security.user.User;
import com.alibou.security.user.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

@Component
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    private final static Logger log = LoggerFactory.getLogger(OrderServiceImpl.class);

    @Override
    public List<OrderSum> getOrdersById(Integer userId) {
        List<OrderSum> orderSumList = orderRepository.findOrderSumsByUserId(userId);

        return orderSumList;
    }

    @Transactional
    @Override
    public OrderSum createOrder(Integer userId, CreateOrderRequest createOrderRequest) {
        Optional<User> user = userRepository.findById(userId);

        if (user.isEmpty()) {
            log.warn("該 userId {} 不存在", userId);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        int totalAmount = 0;
        List<OrderItem> orderItemList = new ArrayList<>();

        for (BuyItem buyItem : createOrderRequest.getBuyItemList()) {
            Product product = productRepository.findByProductId(buyItem.getProductId());

            if (product == null) {
                log.warn("該商品 {} 不存在", buyItem.getProductId());
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
            } else if (product.getStock() < buyItem.getQuantity()){
                log.warn("該商品 {} 庫存不足, 無法購買。 剩餘庫存 {} , 欲購買數量 {}",
                        buyItem.getProductId(), product.getStock(), buyItem.getQuantity());
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
            }

            // update 產品庫存
//            Product productOrigin = productRepository.findByProductId(product.getProductId());
//            productOrigin.setProductId(productOrigin.getProductId());
//            productOrigin.setProductName(productOrigin.getProductName());
//            productOrigin.setPrice(productOrigin.getPrice());
//            productOrigin.setProductCategory(productOrigin.getProductCategory());
//            productOrigin.setStock(productOrigin.getStock() - buyItem.getQuantity());
//            productOrigin.setCreatedDate(productOrigin.getCreatedDate());

            productRepository.updateProduct(product.getProductId(), buyItem.getQuantity());

            //
            int amount = buyItem.getQuantity() * product.getPrice();
            totalAmount = amount + totalAmount;

            OrderItem orderItem = new OrderItem();
            orderItem.setProductId(buyItem.getProductId());
            orderItem.setQuantity(buyItem.getQuantity());
            orderItem.setAmount(amount);

            orderItemList.add(orderItem);
        }

        OrderSum orderSum = new OrderSum();
        orderSum.setUserId(userId);
        orderSum.setTotalAmount(totalAmount);
        OrderSum created = orderRepository.save(orderSum);

        List<OrderItem> orderItemList1 = orderItemRepository.saveAll(orderItemList);
        created.setOrderItemList(orderItemList1);
        return created;
    }
}
