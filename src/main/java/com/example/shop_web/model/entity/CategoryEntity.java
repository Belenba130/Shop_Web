package com.example.shop_web.model.entity;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "category", schema = "shop_md5", catalog = "")
public class CategoryEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "category_id")
    private long categoryId;
    @Basic
    @Column(name = "category_name")
    private String categoryName;
    @Basic
    @Column(name = "description")
    private String description;
    @Basic
    @Column(name = "status")
    private Boolean status;

    public long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(long categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CategoryEntity that = (CategoryEntity) o;
        return categoryId == that.categoryId && Objects.equals(categoryName, that.categoryName) && Objects.equals(description, that.description) && Objects.equals(status, that.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(categoryId, categoryName, description, status);
    }
}
