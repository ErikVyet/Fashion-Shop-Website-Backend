package com.code.models;

import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import com.code.embeddeds.OrderItemId;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;

@Entity
@Table(name = "order_items")
public class OrderItem {

    @EmbeddedId
    private OrderItemId id;

    @JdbcTypeCode(SqlTypes.INTEGER)
    @Column(name = "quantity", updatable = false, nullable = false)
    private int quantity;

    @JdbcTypeCode(SqlTypes.VARCHAR)
    @Column(name = "applied_coupon_code", length = 20, nullable = true)
    private String appliedCouponCode;

    @JdbcTypeCode(SqlTypes.BIGINT)
    @Column(name = "discount", updatable = false, nullable = true)
    private long discount;

    @ManyToOne
    @JoinColumn(name = "order_id", updatable = false, nullable = false)
    @MapsId("orderId")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "variant_id", updatable = false, nullable = false)
    @MapsId("variantId")
    private Variant variant;

    public OrderItem() { }

    public OrderItemId getId() {
        return id;
    }

    public void setId(OrderItemId id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getAppliedCouponCode() {
        return appliedCouponCode;
    }

    public void setAppliedCouponCode(String appliedCouponCode) {
        this.appliedCouponCode = appliedCouponCode;
    }

    public long getDiscount() {
        return discount;
    }

    public void setDiscount(long discount) {
        this.discount = discount;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Variant getVariant() {
        return variant;
    }

    public void setVariant(Variant variant) {
        this.variant = variant;
    }

}