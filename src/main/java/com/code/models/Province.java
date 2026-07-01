package com.code.models;

import java.util.List;

import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "provinces")
public class Province {

    @Id
    @JdbcTypeCode(SqlTypes.VARCHAR)
    @Column(name = "code", updatable = false, length = 10, nullable = false)
    private String code;

    @JdbcTypeCode(SqlTypes.VARCHAR)
    @Column(name = "name", unique = true, length = 255, nullable = false)
    private String name;

    @JdbcTypeCode(SqlTypes.BOOLEAN)
    @Column(name = "is_active", nullable = false)
    private boolean active;

    @OneToMany(mappedBy = "province", fetch = FetchType.LAZY)
    private List<Ward> wards;

    @OneToMany(mappedBy = "province", fetch = FetchType.LAZY)
    private List<Order> orders;

    public Province() { }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public List<Ward> getWards() {
        return wards;
    }

    public void setWards(List<Ward> wards) {
        this.wards = wards;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

}