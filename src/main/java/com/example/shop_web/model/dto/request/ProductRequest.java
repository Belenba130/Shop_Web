package com.example.shop_web.model.dto.request;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductRequest {
    private long productId;
    private String sku;
    private String productName;
    private String description;
    private BigDecimal unitPrice;
    private Integer stockQuantity;
    private String image;
    private Long categoryId;
}
