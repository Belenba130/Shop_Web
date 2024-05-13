package com.example.shop_web.model.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "products", schema = "shop_md5", catalog = "")
public class ProductsEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "product_id")
    private long productId;
    @Basic
    @Column(name = "sku")
    private String sku;
    @Basic
    @Column(name = "product_name")
    private String productName;
    @Basic
    @Column(name = "description")
    private String description;
    @Basic
    @Column(name = "unit_price")
    private BigDecimal unitPrice;
    @Basic
    @Column(name = "stock_quantity")
    private Integer stockQuantity;
    @Basic
    @Column(name = "image")
    private String image;
    @Basic
    @Column(name = "category_id")
    private Long categoryId;



    private Timestamp CreatedAt;



    private Timestamp UpdatedAt;

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Integer getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(Integer stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }
    public Timestamp getCreatedAt() {
        return CreatedAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        CreatedAt = createdAt;
    }
    public Timestamp getUpdatedAt() {
        return UpdatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        UpdatedAt = updatedAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductsEntity that = (ProductsEntity) o;
        return productId == that.productId && Objects.equals(sku, that.sku) && Objects.equals(productName, that.productName) && Objects.equals(description, that.description) && Objects.equals(unitPrice, that.unitPrice) && Objects.equals(stockQuantity, that.stockQuantity) && Objects.equals(image, that.image) && Objects.equals(categoryId, that.categoryId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId, sku, productName, description, unitPrice, stockQuantity, image, categoryId);
    }
}
