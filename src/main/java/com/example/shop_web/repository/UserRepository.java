package com.example.shop_web.repository;

import com.example.shop_web.model.entity.UsersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository

public interface UserRepository extends JpaRepository<UsersEntity,Long> {
    UsersEntity findUsersEntityByUsername(String username);
}
