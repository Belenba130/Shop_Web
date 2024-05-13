package com.example.shop_web.model.dto.request;

import com.example.shop_web.model.entity.UserRoleEntity;
import lombok.Data;

import java.util.List;

@Data
public class SignUpRequest {
    private long userId;
    private String username;
    private String email;
    private String fullname;
    private Boolean status;
    private String password;
    private String avatar;
    private String phone;
    private String address;
    private List<String> roles;
}
