package com.example.shop_web.controller;

import com.example.shop_web.model.dto.request.CheckoutRequest;
import com.example.shop_web.model.dto.request.ShoppingCartRequest;
import com.example.shop_web.model.dto.request.UserUpdateRequest;
import com.example.shop_web.model.dto.response.CheckOutResponse;
import com.example.shop_web.model.dto.response.ShoppingCartResponse;
import com.example.shop_web.model.dto.response.UserResponse;
import com.example.shop_web.service.UserService;
import com.example.shop_web.service.imp.CheckOutServiceImp;
import com.example.shop_web.service.imp.ShoppingServiceImp;
import com.example.shop_web.service.imp.UserDetailService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/v1/user")
@AllArgsConstructor
public class UserController {
    private UserDetailService userService;
    private ShoppingServiceImp shoppingService;
    private CheckOutServiceImp checkOutService;

    @GetMapping("/shopping-cart")
    public List<ShoppingCartResponse> showAll(){
        return shoppingService.showAll();
    }

    @PostMapping("/shopping-cart")
    public ShoppingCartResponse addToCart(@RequestBody ShoppingCartRequest request){
        return shoppingService.addToCart(request);
    }

    @PutMapping("/shopping-cart/{id}")
    public ShoppingCartResponse updateCartItemQuantity(@PathVariable String id, int quantity) {
        return shoppingService.updateCartItemQuantity(id,quantity);
    }
    @DeleteMapping("/shopping-cart/{id}")
    public void deleteCartItem(@PathVariable String cartItemId){
       shoppingService.deleteCartItem(cartItemId);
    }

    @DeleteMapping("/shopping-cart")
    public void deleteAllItem(){
        shoppingService.deleteAllCartItem();
    }

    @PostMapping("/shopping-cart/checkout")
    public CheckOutResponse checkOut(@RequestBody CheckoutRequest request){
        Long addId = request.getAddressId();
       return checkOutService.paymentInfo(addId);
    }

    @GetMapping("/account")
    public ResponseEntity<UserResponse> showInfo() {
        UserResponse response = userService.showUser();
        if (response != null) {
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/account")
    public UserResponse updateUser(@RequestBody UserUpdateRequest request, MultipartFile file){
        return userService.update(request,file);
    }
}
