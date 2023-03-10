package com.alibou.security.service.Impl;

import com.alibou.security.dao.ProductRepository;
import com.alibou.security.entity.Product;
import com.alibou.security.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Page<Product> findAllByParams(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    @Override
    public Product getProductById(Integer productId) {
        return productRepository.findByProductId(productId);
    }

    @Override
    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product updateProduct(Product product) {
        // reset created date, or createdDate will change to Null
        Product origin = productRepository.findByProductId(product.getProductId());
        product.setCreatedDate(origin.getCreatedDate());

        return productRepository.save(product);
    }

    @Override
    public void deleteProduct(Integer productId) {
        productRepository.deleteById(productId);
    }
}
