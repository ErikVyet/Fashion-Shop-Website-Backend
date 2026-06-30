package com.code.models;

import java.util.List;
import java.util.UUID;

import org.hibernate.annotations.Generated;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.generator.EventType;
import org.hibernate.type.SqlTypes;

import com.code.enums.VariantSize;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "variants")
public class Variant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JdbcTypeCode(SqlTypes.INTEGER)
    @Column(name = "id", updatable = false, nullable = false)
    private int id;

    @JdbcTypeCode(SqlTypes.UUID)
    @Generated(event = EventType.INSERT)
    @Column(name = "code", unique = true, insertable = false, updatable = false, nullable = false)
    private UUID sku;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "size", nullable = false)
    private VariantSize size;

    @JdbcTypeCode(SqlTypes.VARCHAR)
    @Column(name = "color", length = 50, nullable = false)
    private String color;

    @JdbcTypeCode(SqlTypes.VARCHAR)
    @Column(name = "color_code", length = 7, nullable = false)
    private String colorCode;

    @JdbcTypeCode(SqlTypes.INTEGER)
    @Column(name = "stock")
    private int stock;

    @JdbcTypeCode(SqlTypes.BIGINT)
    @Column(name = "price", nullable = false)
    private long price;

    @Column(name = "image_url", columnDefinition = "text", nullable = true)
    private String image;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @OneToMany(mappedBy = "variant", fetch = FetchType.LAZY)
    private List<AiTryonHistory> tryonHistory;

    @OneToMany(mappedBy = "variant", cascade = CascadeType.REMOVE)
    private List<VariantCoupon> variantCoupons;

    @OneToMany(mappedBy = "variant", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    private List<Cart> carts;

    @OneToMany(mappedBy = "variant", fetch = FetchType.LAZY)
    private List<OrderItem> orderItems;

    public Variant() { }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public UUID getSku() {
        return sku;
    }

    public void setSku(UUID sku) {
        this.sku = sku;
    }

    public VariantSize getSize() {
        return size;
    }

    public void setSize(VariantSize size) {
        this.size = size;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getColorCode() {
        return colorCode;
    }

    public void setColorCode(String colorCode) {
        this.colorCode = colorCode;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public List<AiTryonHistory> getTryonHistory() {
        return tryonHistory;
    }

    public void setTryonHistory(List<AiTryonHistory> tryonHistory) {
        this.tryonHistory = tryonHistory;
    }

    public List<VariantCoupon> getVariantCoupons() {
        return variantCoupons;
    }

    public void setVariantCoupons(List<VariantCoupon> variantCoupons) {
        this.variantCoupons = variantCoupons;
    }

    public List<Cart> getCarts() {
        return carts;
    }

    public void setCarts(List<Cart> carts) {
        this.carts = carts;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

}