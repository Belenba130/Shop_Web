package com.example.shop_web.service;

import com.example.shop_web.model.dto.request.ShoppingCartRequest;
import com.example.shop_web.model.dto.response.ShoppingCartResponse;
import com.example.shop_web.model.entity.ShoppingCartEntity;
import com.example.shop_web.model.entity.UsersEntity;

import java.math.BigDecimal;
import java.util.List;

public interface ShoppingService {
    UsersEntity userUsing();

    List<ShoppingCartResponse> showAll();

    ShoppingCartResponse addToCart(ShoppingCartRequest shoppingCartRequest);

    ShoppingCartResponse updateCartItemQuantity(String cartItemId, int quantity);

    void deleteCartItem(String cartItemId);

    void deleteAllCartItem();


    BigDecimal Payment();

    List<ShoppingCartEntity> getAllListCart();
}

