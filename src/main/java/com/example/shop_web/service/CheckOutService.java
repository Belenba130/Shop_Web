package com.example.shop_web.service;

import com.example.shop_web.model.dto.response.CheckOutResponse;

public interface CheckOutService {
    CheckOutResponse paymentInfo(Long addressId);
}
