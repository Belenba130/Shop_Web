package com.example.shop_web.model.entity;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "role", schema = "shop_md5", catalog = "")
public class RoleEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "role_id")
    private long roleId;
    @Basic
    @Column(name = "role_name")
    private String roleName;

    public long getRoleId() {
        return roleId;
    }

    public void setRoleId(long roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RoleEntity that = (RoleEntity) o;
        return roleId == that.roleId && Objects.equals(roleName, that.roleName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(roleId, roleName);
    }
}
