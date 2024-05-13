package com.example.shop_web.repository;

import com.example.shop_web.model.entity.OrderDetailsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface OderDetailRepository extends JpaRepository<OrderDetailsEntity,String> {
}
