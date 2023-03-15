package com.alibou.security.model;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BuyItem {

    @NotNull
    public Integer productId;
    @NotNull
    public Integer quantity;
}
