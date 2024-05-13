package com.example.shop_web.repository;

import com.example.shop_web.model.entity.UserRoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface UserRoleRepository extends JpaRepository<UserRoleEntity,Long> {
    UserRoleEntity findByUserIdAndRoleId(long userId, long roleId);
}
