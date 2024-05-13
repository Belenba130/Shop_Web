package com.example.shop_web.model.entity;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "wish_list", schema = "shop_md5", catalog = "")
public class WishListEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "wish_list_id")
    private long wishListId;
    @Basic
    @Column(name = "user_id")
    private Long userId;
    @Basic
    @Column(name = "product_id")
    private Long productId;

    public long getWishListId() {
        return wishListId;
    }

    public void setWishListId(long wishListId) {
        this.wishListId = wishListId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WishListEntity that = (WishListEntity) o;
        return wishListId == that.wishListId && Objects.equals(userId, that.userId) && Objects.equals(productId, that.productId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(wishListId, userId, productId);
    }
}
