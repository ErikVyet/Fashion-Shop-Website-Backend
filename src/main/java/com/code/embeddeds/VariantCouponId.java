package com.code.embeddeds;

import java.io.Serializable;
import java.util.Objects;

import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class VariantCouponId implements Serializable {

    private static final long serialVersionUID = 1L;

    @JdbcTypeCode(SqlTypes.BIGINT)
    @Column(name = "variant_id", updatable = false, nullable = false)
    private Long variantId;

    @JdbcTypeCode(SqlTypes.INTEGER)
    @Column(name = "coupon_id", updatable = false, nullable = false)
    private Integer couponId;

    public VariantCouponId() { }

    public VariantCouponId(Integer couponId, Long variantId) {
        this.couponId = couponId;
        this.variantId = variantId;
    }

    public Long getVariantId() {
        return variantId;
    }

    public void setVariantId(Long variantId) {
        this.variantId = variantId;
    }

    public Integer getCouponId() {
        return couponId;
    }

    public void setCouponId(Integer couponId) {
        this.couponId = couponId;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 53 * hash + Objects.hashCode(this.variantId);
        hash = 53 * hash + Objects.hashCode(this.couponId);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final VariantCouponId other = (VariantCouponId) obj;
        if (!Objects.equals(this.variantId, other.variantId)) {
            return false;
        }
        return Objects.equals(this.couponId, other.couponId);
    }

}