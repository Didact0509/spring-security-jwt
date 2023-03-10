package com.alibou.security.service;

import com.alibou.security.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductService {

    Page<Product> findAllByParams(Pageable pageable);

    Product getProductById(Integer productId);

    Product saveProduct(Product product);

    Product updateProduct(Product product);

    void deleteProduct(Integer productId);
}
