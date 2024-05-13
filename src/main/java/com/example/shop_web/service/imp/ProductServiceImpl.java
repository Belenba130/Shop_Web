package com.example.shop_web.service.imp;

import com.example.shop_web.exception.BaseException;
import com.example.shop_web.model.dto.request.ProductRequest;
import com.example.shop_web.model.dto.response.ProductResponse;
import com.example.shop_web.model.entity.ProductsEntity;
import com.example.shop_web.repository.ProductRepository;
import com.example.shop_web.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;


@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductRepository productRepository;

    @Override
    public Page<ProductsEntity> getAllProducts(Pageable pageable) {
        return productRepository.findAll(pageable);
    }


    @Override
    public ProductResponse getProductById(Long productId) {
        ProductsEntity productEntity = productRepository.findById(productId)
                .orElseThrow(() -> new BaseException("RA-43-401"));
        return convertToResponse(productEntity);
    }


    @Override
    public void deleteProduct(Long productId){
        ProductsEntity productsEntity = productRepository.findById(productId)
                .orElseThrow(() -> new BaseException("RA-C46-404"));
        productRepository.delete(productsEntity);
    }

    public ProductsEntity addProduct(ProductRequest request){
        ProductsEntity product = new ProductsEntity();
        product.setProductName(request.getProductName());
        product.setDescription(request.getDescription());
        product.setCategoryId(request.getCategoryId());
        product.setUnitPrice(request.getUnitPrice());
        product.setStockQuantity(request.getStockQuantity());
        product.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        return productRepository.save(product);
    }

    public ProductsEntity updateProduct(Long productId, ProductRequest request) {
        ProductsEntity product = productRepository.findById(productId)
                .orElseThrow(() -> new BaseException("RA-C46-404"));
        product.setProductName(request.getProductName());
        product.setDescription(request.getDescription());
        product.setCategoryId(request.getCategoryId());
        product.setUnitPrice(request.getUnitPrice());
        product.setStockQuantity(request.getStockQuantity());
        product.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
        return productRepository.save(product);
    }
    private ProductResponse convertToResponse(ProductsEntity productEntity) {
        ProductResponse response = new ProductResponse();
        response.setProductId(productEntity.getProductId());
        response.setProductName(productEntity.getProductName());
        response.setDescription(productEntity.getDescription());
        response.setUnitPrice(productEntity.getUnitPrice());
        response.setImage(productEntity.getImage());
        return response;
    }
}
