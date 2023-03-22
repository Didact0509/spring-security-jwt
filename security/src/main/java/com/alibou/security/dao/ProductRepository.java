package com.alibou.security.dao;

import com.alibou.security.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    Product findByProductId(Integer productId);

    @Modifying
    @Transactional
    @Query(value = "UPDATE Product p SET p.stock = p.stock - :stock WHERE p.productId = :productId")
    void updateProduct(@Param(value = "productId") Integer productId, @Param(value = "stock") Integer stock);
}
