package com.example.shop_web.model.dto.response;
import lombok.Data;

@Data
public class CategoryResponse {
    private long categoryId;
    private String categoryName;
    private String description;
    private Boolean status;
}
