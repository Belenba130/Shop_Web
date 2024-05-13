package com.example.shop_web.model.dto.request;

import lombok.Data;

@Data
public class CategoryRequest {
    private long categoryId;
    private String categoryName;
    private String description;
    private Boolean status;
}
