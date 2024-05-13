package com.example.shop_web.controller;

import com.example.shop_web.model.dto.request.ProductRequest;

import com.example.shop_web.model.entity.ProductsEntity;
import com.example.shop_web.model.entity.UsersEntity;

import com.example.shop_web.service.imp.ProductServiceImpl;

import com.example.shop_web.service.imp.UserDetailService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/v1/admin")
public class AdminController {
    @Autowired
    private UserDetailService userService;
    @Autowired
    private ProductServiceImpl productService;


    @GetMapping("/users")
    public Page<UsersEntity> getUsers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "userId") String sortBy,
            @RequestParam(defaultValue = "asc") String sortOrder
    ) {
        Sort.Direction direction = sortOrder.equalsIgnoreCase("asc") ?
                Sort.Direction.ASC : Sort.Direction.DESC;
        PageRequest pageRequest = PageRequest.of(page, size, Sort.by(direction, sortBy));
        return userService.getUser(pageRequest);
    }

    @PostMapping("/users/{userId}/role/{roleId}")
    public void addRole(@PathVariable Long userId, @PathVariable Long roleId ){
        userService.addRole(userId,roleId);
    }

    @DeleteMapping("/users/{userId}/role/{roleId}")
    public void deleteRole(@PathVariable Long userId, @PathVariable Long roleId){
        userService.deleteRole(userId,roleId);
    }

    @PutMapping("users/{userId}")
    public void changeUserStatus(@PathVariable Long userId){
        userService.UserStatus(userId);
    }
    @PostMapping("/products")
    public ProductsEntity addProduct(@RequestBody ProductRequest request){
        return productService.addProduct(request);
    }

    @PutMapping("/products/{productId}")
    public ProductsEntity updateProduct(@RequestBody ProductRequest request, @PathVariable Long productId){
        return productService.updateProduct(productId,request);
    }

    @DeleteMapping("/products/{productId}")
    public void deleteProduct(@PathVariable Long productId){
        productService.deleteProduct(productId);
    }


}
