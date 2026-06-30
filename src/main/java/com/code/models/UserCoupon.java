package com.code.models;

import com.code.embeddeds.UserCouponId;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;

@Entity
@Table(name = "user_coupons")
public class UserCoupon {

    @EmbeddedId
    private UserCouponId id;

    @ManyToOne
    @JoinColumn(name = "user_id", updatable = false, nullable = false)
    @MapsId("userId")
    private User user;

    @ManyToOne
    @JoinColumn(name = "coupon_id", updatable = false, nullable = false)
    @MapsId("couponId")
    private Coupon coupon;

    public UserCoupon() { }

    public UserCouponId getId() {
        return id;
    }

    public void setId(UserCouponId id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Coupon getCoupon() {
        return coupon;
    }

    public void setCoupon(Coupon coupon) {
        this.coupon = coupon;
    }

}