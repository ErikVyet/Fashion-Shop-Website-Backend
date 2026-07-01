package com.code.models;

import java.util.List;

import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "categories")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JdbcTypeCode(SqlTypes.INTEGER)
    @Column(name = "id", updatable = false, nullable = false)
    private int id;

    @JdbcTypeCode(SqlTypes.INTEGER)
    @Column(name = "priority", nullable = false)
    private int order;

    @JdbcTypeCode(SqlTypes.VARCHAR)
    @Column(name = "name", unique = true, length = 100, nullable = false)
    private String name;

    @JdbcTypeCode(SqlTypes.BOOLEAN)
    @Column(name = "is_active", nullable = false)
    private boolean active;

    @ManyToOne
    @JoinColumn(name = "parent_id", nullable = false)
    private Category parent;

    @OneToMany(mappedBy = "parent", cascade = CascadeType.REMOVE)
    private List<Category> categories;

    @OneToMany(mappedBy = "category", cascade = CascadeType.REMOVE)
    private List<Product> products;

    public Category() { }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
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

    public Category getParent() {
        return parent;
    }

    public void setParent(Category parent) {
        this.parent = parent;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

}