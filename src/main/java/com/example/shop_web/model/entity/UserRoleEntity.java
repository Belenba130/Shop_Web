package com.example.shop_web.model.entity;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "user_role", schema = "shop_md5", catalog = "")
@IdClass(UserRoleEntityPK.class)
public class UserRoleEntity {

    private Long userId;

    private Long roleId;
    private RoleEntity rolesByRoleId;
    private UsersEntity usersByUserId;
    @Id
    @Basic
    @Column(name = "user_id")
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
    @Id
    @Basic
    @Column(name = "role_id")
    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserRoleEntity that = (UserRoleEntity) o;
        return Objects.equals(userId, that.userId) && Objects.equals(roleId, that.roleId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, roleId);
    }
    @ManyToOne
    @JoinColumn(name = "roleId", referencedColumnName = "role_id", nullable = false)
    public RoleEntity getRolesByRoleId() {
        return rolesByRoleId;
    }

    public void setRolesByRoleId(RoleEntity rolesByRoleId) {
        this.rolesByRoleId = rolesByRoleId;
    }

    @ManyToOne
    @JoinColumn(name = "userId", referencedColumnName = "user_id", nullable = false)
    public UsersEntity getUsersByUserId() {
        return usersByUserId;
    }

    public void setUsersByUserId(UsersEntity usersByUserId) {
        this.usersByUserId = usersByUserId;
    }


}
