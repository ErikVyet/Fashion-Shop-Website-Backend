package com.code.models;

import com.code.embeddeds.VariantCouponId;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;

@Entity
@Table(name = "variant_coupons")
public class VariantCoupon {

    @EmbeddedId
    private VariantCouponId id;

    @ManyToOne
    @JoinColumn(name = "variant_id", updatable = false, nullable = false)
    @MapsId("variantId")
    private Variant variant;

    @ManyToOne
    @JoinColumn(name = "coupon_id", updatable = false, nullable = false)
    @MapsId("couponId")
    private Coupon coupon;

    public VariantCoupon() { }

    public VariantCouponId getId() {
        return id;
    }

    public void setId(VariantCouponId id) {
        this.id = id;
    }

    public Variant getVariant() {
        return variant;
    }

    public void setVariant(Variant variant) {
        this.variant = variant;
    }

    public Coupon getCoupon() {
        return coupon;
    }

    public void setCoupon(Coupon coupon) {
        this.coupon = coupon;
    }

}