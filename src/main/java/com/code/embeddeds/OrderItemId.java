package com.code.embeddeds;

import java.io.Serializable;
import java.util.Objects;

import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class OrderItemId implements Serializable {

    private static final long serialVersionUID = 1L;

    @JdbcTypeCode(SqlTypes.BIGINT)
    @Column(name = "order_id", updatable = false, nullable = false)
    private Long orderId;

    @JdbcTypeCode(SqlTypes.BIGINT)
    @Column(name = "variant_id", updatable = false, nullable = false)
    private Long variantId;

    public OrderItemId() { }

    public OrderItemId(Long orderId, Long variantId) {
        this.orderId = orderId;
        this.variantId = variantId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getVariantId() {
        return variantId;
    }

    public void setVariantId(Long variantId) {
        this.variantId = variantId;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + Objects.hashCode(this.orderId);
        hash = 17 * hash + Objects.hashCode(this.variantId);
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
        final OrderItemId other = (OrderItemId) obj;
        if (!Objects.equals(this.orderId, other.orderId)) {
            return false;
        }
        return Objects.equals(this.variantId, other.variantId);
    }

}