package com.example.shop_web.model.dto.response;

import lombok.Data;

@Data
public class TokenResponse {
    private String token;
    private String tokenType = "Bearer";
}
