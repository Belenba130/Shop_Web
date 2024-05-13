package com.example.shop_web.model.dto.response;

import com.example.shop_web.model.entity.UserRoleEntity;
import lombok.Data;

import java.util.List;
@Data

public class SignInResponse {
    private long userId;
    private String userName;
    private String token;
    private String tokenType = "Bearer Token";
    private List<String> roles;
}
