package com.alibou.security.dao;

import com.alibou.security.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;


public interface ProductRepository extends JpaRepository<Product, Integer> {

}
