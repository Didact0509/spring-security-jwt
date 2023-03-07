package com.alibou.security.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue
    private Integer productId;
    private String productName;
    private Integer price;
    private Integer stock;
    private Date createdDate;
    private Date lastModifiedDate;

    @Enumerated(EnumType.STRING)
    private ProductCategory productCategory;
}
