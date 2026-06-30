package com.code.models;

import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import com.code.enums.DiscountVariant;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "coupons")
public class Coupon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JdbcTypeCode(SqlTypes.INTEGER)
    @Column(name = "id", updatable = false, nullable = false)
    private int id;

    @JdbcTypeCode(SqlTypes.VARCHAR)
    @Column(name = "code", unique = true, length = 20, nullable = false)
    private String code;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "discount_variant", nullable = false)
    private DiscountVariant discountVariant;

    @JdbcTypeCode(SqlTypes.BIGINT)
    @Column(name = "discount_value", nullable = true)
    private long value;

    @JdbcTypeCode(SqlTypes.INTEGER)
    @Column(name = "discount_percentage", nullable = true)
    private int percentage;

    @JdbcTypeCode(SqlTypes.BIGINT)
    @Column(name = "min_order_value", nullable = true)
    private long min;

    @JdbcTypeCode(SqlTypes.LONGVARCHAR)
    @Column(name = "description", nullable = true)
    private String description;

    @JdbcTypeCode(SqlTypes.TIMESTAMP)
    @Column(name = "start_time", nullable = false)
    private LocalDateTime start;

    @JdbcTypeCode(SqlTypes.TIMESTAMP)
    @Column(name = "end_time", nullable = false)
    private LocalDateTime end;

    @JdbcTypeCode(SqlTypes.INTEGER)
    @Column(name = "usage_limit", nullable = false)
    private int usageLimit;

    @JdbcTypeCode(SqlTypes.INTEGER)
    @Column(name = "used_count", nullable = false)
    private int usedCount;

    @JdbcTypeCode(SqlTypes.BOOLEAN)
    @Column(name = "is_active", nullable = false)
    private boolean active;

    @OneToMany(mappedBy = "coupon", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    private List<UserCoupon> userCoupons;

    @OneToMany(mappedBy = "coupon", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    private List<VariantCoupon> variantCoupons;

    public Coupon() { }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public DiscountVariant getDiscountVariant() {
        return discountVariant;
    }

    public void setDiscountVariant(DiscountVariant discountVariant) {
        this.discountVariant = discountVariant;
    }

    public long getValue() {
        return value;
    }

    public void setValue(long value) {
        this.value = value;
    }

    public int getPercentage() {
        return percentage;
    }

    public void setPercentage(int percentage) {
        this.percentage = percentage;
    }

    public long getMin() {
        return min;
    }

    public void setMin(long min) {
        this.min = min;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getStart() {
        return start;
    }

    public void setStart(LocalDateTime start) {
        this.start = start;
    }

    public LocalDateTime getEnd() {
        return end;
    }

    public void setEnd(LocalDateTime end) {
        this.end = end;
    }

    public int getUsageLimit() {
        return usageLimit;
    }

    public void setUsageLimit(int usageLimit) {
        this.usageLimit = usageLimit;
    }

    public int getUsedCount() {
        return usedCount;
    }

    public void setUsedCount(int usedCount) {
        this.usedCount = usedCount;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public List<UserCoupon> getUserCoupons() {
        return userCoupons;
    }

    public void setUserCoupons(List<UserCoupon> userCoupons) {
        this.userCoupons = userCoupons;
    }

    public List<VariantCoupon> getVariantCoupons() {
        return variantCoupons;
    }

    public void setVariantCoupons(List<VariantCoupon> variantCoupons) {
        this.variantCoupons = variantCoupons;
    }

}