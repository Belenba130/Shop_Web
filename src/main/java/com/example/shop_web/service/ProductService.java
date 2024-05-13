package com.example.shop_web.service;

import com.example.shop_web.model.dto.response.ProductResponse;
import com.example.shop_web.model.entity.ProductsEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService {

    Page<ProductsEntity> getAllProducts(Pageable pageable);

    ProductResponse getProductById(Long productId);

    void deleteProduct(Long productId);
}
