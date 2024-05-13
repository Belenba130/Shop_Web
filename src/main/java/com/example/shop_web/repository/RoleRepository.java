package com.example.shop_web.repository;

import com.example.shop_web.model.entity.RoleEntity;
import com.example.shop_web.model.entity.UserRoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository

public interface RoleRepository extends JpaRepository<RoleEntity,Long> {
    RoleEntity findByRoleId(Long roleId);
    RoleEntity findByRoleName(String name);
}
