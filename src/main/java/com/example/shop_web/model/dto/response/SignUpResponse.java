package com.example.shop_web.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class SignUpResponse {
    private String message;

    public SignUpResponse (String message) {
        this.message = message;
    }
}
