package com.example.shop_web.model.entity;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "address", schema = "shop_md5", catalog = "")
public class AddressEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "address_id")
    private long addressId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private UsersEntity usersByUserId;

    @Column(name = "full_address")
    private String fullAddress;

    @Column(name = "phone")
    private String phone;

    @Column(name = "receive_name")
    private String receiveName;

    public long getAddressId() {
        return addressId;
    }

    public void setAddressId(long addressId) {
        this.addressId = addressId;
    }

    public UsersEntity getUsersByUserId() {
        return usersByUserId;
    }

    public void setUsersByUserId(UsersEntity usersByUserId) {
        this.usersByUserId = usersByUserId;
    }

    public String getFullAddress() {
        return fullAddress;
    }

    public void setFullAddress(String fullAddress) {
        this.fullAddress = fullAddress;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getReceiveName() {
        return receiveName;
    }

    public void setReceiveName(String receiveName) {
        this.receiveName = receiveName;
    }
}