package com.example.shop_web.model.dto.request;

import lombok.Data;

@Data
public class SignInRequest {
    private String username;
    private String password;
}
