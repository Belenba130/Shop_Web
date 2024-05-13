package com.example.shop_web.repository;

import com.example.shop_web.model.entity.AddressEntity;
import com.example.shop_web.model.entity.UsersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface AddressRepository extends JpaRepository<AddressEntity,String> {
    AddressEntity findByAddressIdAndUsersByUserId(Long addressId, UsersEntity user);


}
