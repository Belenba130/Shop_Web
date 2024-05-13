package com.example.shop_web.repository;

import com.example.shop_web.model.entity.OrdersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OderRepository extends JpaRepository<OrdersEntity,String> {
}
