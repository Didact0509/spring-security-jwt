package com.alibou.security.service;

import com.alibou.security.model.Product;

import java.util.Optional;

public interface ProductService {

    Product findProductById(Integer productId);
}
