package com.example.shop_web.controller;

import com.example.shop_web.model.dto.response.ProductResponse;
import com.example.shop_web.model.entity.ProductsEntity;
import com.example.shop_web.repository.ProductRepository;
import com.example.shop_web.service.imp.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1")
public class ProductController {
    @Autowired
    ProductServiceImpl productService;
    @Autowired
    ProductRepository productRepository;

    @GetMapping("/admin/products/{id}")
    public ProductResponse findById(@PathVariable Long id) {
        return productService.getProductById(id);
    }

    @GetMapping("/products")
    public Page<ProductsEntity> getAllProducts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "productId") String sortBy,
            @RequestParam(defaultValue = "asc") String sortOrder
    ) {
        Sort.Direction direction = sortOrder.equalsIgnoreCase("asc") ?
                Sort.Direction.ASC : Sort.Direction.DESC;
        PageRequest pageRequest = PageRequest.of(page, size, Sort.by(direction, sortBy));
        return productService.getAllProducts(pageRequest);
    }
    @GetMapping("/products/search")
    public List<ProductsEntity>findProductsByNameOrDescription(@RequestBody String name, String description){
        return productRepository.findProductsByNameOrDescription(name,description);
    }
}
