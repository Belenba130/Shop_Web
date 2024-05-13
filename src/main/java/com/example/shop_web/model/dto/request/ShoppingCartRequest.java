package com.example.shop_web.model.dto.request;

import lombok.Data;

@Data
public class ShoppingCartRequest {
    private long productId;
    private Integer orderQuantity;
}
