package com.example.shop_web.model.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "orders", schema = "shop_md5", catalog = "")
public class OrdersEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "order_id")
    private long orderId;
    @Basic
    @Column(name = "serial_number")
    private String serialNumber;
    @Basic
    @Column(name = "user_id")
    private long userId;
    @Basic
    @Column(name = "total_price")
    private BigDecimal totalPrice;
    @Basic
    @Column(name = "status")
    private String status;
    @Basic
    @Column(name = "note")
    private String note;
    @Basic
    @Column(name = "receive_name")
    private String receiveName;
    @Basic
    @Column(name = "receive_address")
    private String receiveAddress;
    @Basic
    @Column(name = "receive_phone")
    private String receivePhone;





    private Timestamp CreatedAt;
    private Timestamp receiveAt;

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Object getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getReceiveName() {
        return receiveName;
    }

    public void setReceiveName(String receiveName) {
        this.receiveName = receiveName;
    }

    public String getReceiveAddress() {
        return receiveAddress;
    }

    public void setReceiveAddress(String receiveAddress) {
        this.receiveAddress = receiveAddress;
    }

    public String getReceivePhone() {
        return receivePhone;
    }

    public void setReceivePhone(String receivePhone) {
        this.receivePhone = receivePhone;
    }

    public Timestamp getCreatedAt() {
        return CreatedAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        CreatedAt = createdAt;
    }

    public Timestamp getReceiveAt() {
        return receiveAt;
    }

    public void setReceiveAt(Timestamp receiveAt) {
        this.receiveAt = receiveAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrdersEntity that = (OrdersEntity) o;
        return orderId == that.orderId && userId == that.userId && Objects.equals(serialNumber, that.serialNumber) && Objects.equals(totalPrice, that.totalPrice) && Objects.equals(status, that.status) && Objects.equals(note, that.note) && Objects.equals(receiveName, that.receiveName) && Objects.equals(receiveAddress, that.receiveAddress) && Objects.equals(receivePhone, that.receivePhone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderId, serialNumber, userId, totalPrice, status, note, receiveName, receiveAddress, receivePhone);
    }
}
