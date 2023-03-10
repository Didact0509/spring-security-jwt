package com.alibou.security.dto;

import com.alibou.security.model.ProductCategory;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductRequest {

    @NotNull
    private String productName;
    @NotNull
    private ProductCategory productCategory;
    @NotNull
    private Integer price;
    @NotNull
    private Integer stock;
}
