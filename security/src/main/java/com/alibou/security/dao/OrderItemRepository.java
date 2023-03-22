package com.alibou.security.dao;

import com.alibou.security.entity.OrderItem;
import com.alibou.security.entity.OrderSum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderItemRepository extends JpaRepository<OrderItem, Integer> {

}
