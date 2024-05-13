package com.example.shop_web.repository;

import com.example.shop_web.model.entity.ProductsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<ProductsEntity,Long> {

    @Query("SELECT p FROM ProductsEntity p WHERE UPPER(p.productName) LIKE UPPER(?1) OR UPPER(p.description) LIKE UPPER(?2)")
    List<ProductsEntity> findProductsByNameOrDescription(String name, String description);
}
