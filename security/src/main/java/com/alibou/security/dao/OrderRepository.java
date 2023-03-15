package com.alibou.security.dao;

import com.alibou.security.entity.OrderItem;
import com.alibou.security.entity.OrderSum;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<OrderSum, Integer> {

    List<OrderSum> findOrderSumsByUserId(Integer userId);

//    @Modifying(clearAutomatically = true)
//    @Transactional
//    @Query(value = "INSERT INTO OrderSum o set o.userId = :userId, o.totalAmount = :totalAmount")
//    OrderSum saveOrderSum(@Param(value = "userId") Integer userId, @Param(value = "totalAmount") Integer totalAmount);
}
