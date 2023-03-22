package com.alibou.security.dao;

import com.alibou.security.entity.OrderSum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<OrderSum, Integer> {

    List<OrderSum> findOrderSumsByUserId(Integer userId);

}
