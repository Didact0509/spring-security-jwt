package com.alibou.security.controller;

import com.alibou.security.entity.Product;
import com.alibou.security.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
@Validated
@Tag(name = "Product RESTfull", description = " 產品 CRUD")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/allProducts")
    @Operation(summary = " 取得所有產品(分頁)", description = " 輸入 page(第幾頁), size(呈現數量), sort(依項目排序, default = desc)",
            responses = {
                    @ApiResponse(responseCode = "200", description = " 成功找到產品"),
                    @ApiResponse(responseCode = "404", description = " 找不到相關產品")})
    public ResponseEntity<Page<Product>> findAllByParams(Pageable pageable) {
        return ResponseEntity.ok().body(productService.findAllByParams(pageable));
    }

    @GetMapping("/product/{productId}")
    @Operation(summary = " 取得產品 By Id", description = " 輸入 Product Id")
    public ResponseEntity<Product> getProductById(@PathVariable Integer productId) {
        Product product = productService.getProductById(productId);

        if (product != null) {
            return ResponseEntity.status(HttpStatus.OK).body(product);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping("/product")
    @Operation(summary = " 創建新產品", description = " 輸入產品資訊(All NotNull)")
    public ResponseEntity<Product> saveProduct(@RequestBody @Valid Product product) {
        Product product1 = productService.saveProduct(product);

        return ResponseEntity.status(HttpStatus.CREATED).body(product1);
    }

    @PostMapping("/product/{productId}")
    @Operation(summary = " 更新產品 By Id", description = " 輸入 Product Id 及 更新資訊(All NotNull)")
    public ResponseEntity<Product> updateProduct(@RequestBody @Valid Product product,
                                                 @PathVariable Integer productId) {
        product.setProductId(productId);
        Product updated = productService.updateProduct(product);

        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/product/{productId}")
    @Operation(summary = " 刪除產品 By Id", description = " 輸入 Product Id")
    public ResponseEntity<?> deleteProduct(@PathVariable Integer productId) {
        productService.deleteProduct(productId);

        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
