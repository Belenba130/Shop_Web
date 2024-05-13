package com.example.shop_web.model.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "order_details", schema = "shop_md5", catalog = "")
@IdClass(OrderDetailsEntityPK.class)
public class OrderDetailsEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "order_id")
    private long orderId;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "product_id")
    private long productId;
    @Basic
    @Column(name = "name")
    private String name;
    @Basic
    @Column(name = "unit_price")
    private BigDecimal unitPrice;
    @Basic
    @Column(name = "order_quantity")
    private Integer orderQuantity;

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Integer getOrderQuantity() {
        return orderQuantity;
    }

    public void setOrderQuantity(Integer orderQuantity) {
        this.orderQuantity = orderQuantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderDetailsEntity that = (OrderDetailsEntity) o;
        return orderId == that.orderId && productId == that.productId && Objects.equals(name, that.name) && Objects.equals(unitPrice, that.unitPrice) && Objects.equals(orderQuantity, that.orderQuantity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderId, productId, name, unitPrice, orderQuantity);
    }
}
