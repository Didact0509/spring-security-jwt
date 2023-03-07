package com.alibou.security.service.Impl;

import com.alibou.security.dao.ProductRepository;
import com.alibou.security.model.Product;
import com.alibou.security.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Product findProductById(Integer productId) {
        return productRepository.findById(productId).orElse(null);
    }
}
