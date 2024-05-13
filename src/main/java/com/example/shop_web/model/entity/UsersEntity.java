package com.example.shop_web.model.entity;

import jakarta.persistence.*;

import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "users", schema = "shop_md5", catalog = "")
public class UsersEntity {
    private long userId;
    private String username;
    private String email;
    private String fullname;
    private Boolean status;
    private String password;
    private String avatar;
    private String phone;
    private String address;
    private Date createdTime;



    private List<UserRoleEntity> userRoleEntity;



    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "user_id")
    public Long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "username")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Basic
    @Column(name = "email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "fullname")
    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    @Basic
    @Column(name = "status")
    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    @Basic
    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "avatar")
    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    @Basic
    @Column(name = "phone")
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Basic
    @Column(name = "address")
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UsersEntity that = (UsersEntity) o;
        return userId == that.userId && Objects.equals(username, that.username) && Objects.equals(email, that.email) && Objects.equals(fullname, that.fullname) && Objects.equals(status, that.status) && Objects.equals(password, that.password) && Objects.equals(avatar, that.avatar) && Objects.equals(phone, that.phone) && Objects.equals(address, that.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, username, email, fullname, status, password, avatar, phone, address);
    }

    @OneToMany(mappedBy = "usersByUserId")
    public List<UserRoleEntity> getUserRoleEntities() {
        return userRoleEntity;
    }

    public void setUserRoleEntities(List<UserRoleEntity> userRoleEntities) {
        this.userRoleEntity = userRoleEntities;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }
}
