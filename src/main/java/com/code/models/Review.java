package com.code.models;

import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.annotations.Generated;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.generator.EventType;
import org.hibernate.type.SqlTypes;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "reviews")
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JdbcTypeCode(SqlTypes.BIGINT)
    @Column(name = "id", updatable = false, nullable = false)
    private long id;

    @JdbcTypeCode(SqlTypes.INTEGER)
    @Column(name = "rating", updatable = false, nullable = false)
    private int rating;

    @JdbcTypeCode(SqlTypes.LONGVARCHAR)
    @Column(name = "comment", updatable = false, nullable = true)
    private String comment;

    @Column(name = "image_urls", columnDefinition = "text[]", updatable = false, nullable = false)
    private List<String> images;

    @JdbcTypeCode(SqlTypes.TIMESTAMP)
    @Generated(event = EventType.INSERT)
    @Column(name = "created_at", insertable = false, updatable = false, nullable = false)
    private LocalDateTime created;

    @JdbcTypeCode(SqlTypes.BOOLEAN)
    @Column(name = "is_hidden", nullable = false)
    private boolean hidden;

    @ManyToOne
    @JoinColumn(name = "user_id", updatable = false, nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "product_id", updatable = false, nullable = false)
    private Product product;

    @ManyToOne
    @JoinColumn(name = "order_id", updatable = false, nullable = false)
    private Order order;

    public Review() { }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public boolean isHidden() {
        return hidden;
    }

    public void setHidden(boolean hidden) {
        this.hidden = hidden;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

}