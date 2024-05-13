package com.example.shop_web.repository;

import com.example.shop_web.model.entity.ProductsEntity;
import com.example.shop_web.model.entity.ShoppingCartEntity;
import com.example.shop_web.model.entity.UsersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShoppingCartRepository extends JpaRepository<ShoppingCartEntity,String> {
    List<ShoppingCartEntity> findByUsersByUserId(UsersEntity usersEntity);

    ShoppingCartEntity findByUsersByUserIdAndProductsByProductId(UsersEntity usersEntity, ProductsEntity product);
}
