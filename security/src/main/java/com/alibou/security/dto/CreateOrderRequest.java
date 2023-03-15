package com.alibou.security.dto;

import com.alibou.security.model.BuyItem;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CreateOrderRequest {

    @NotEmpty
    private List<BuyItem> buyItemList;
}
