package com.alibou.security.entity;

import com.alibou.security.model.ProductCategory;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @SequenceGenerator(name = "product_seq", allocationSize = 1)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Integer productId;
    @NotNull
    private String productName;
    @NotNull
    private Integer price;
    @NotNull
    private Integer stock;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @CreatedDate
    private Date createdDate;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @LastModifiedDate
    private Date lastModifiedDate;

    @Enumerated(EnumType.STRING)
    @NotNull
    private ProductCategory productCategory;
}
