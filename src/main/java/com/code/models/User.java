package com.code.models;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.hibernate.annotations.Generated;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.generator.EventType;
import org.hibernate.type.SqlTypes;

import com.code.enums.LoginVariant;
import com.code.enums.Role;

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
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name =  "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @JdbcTypeCode(SqlTypes.UUID)
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @JdbcTypeCode(SqlTypes.VARCHAR)
    @Column(name = "email", length = 254, unique = true, nullable = false)
    private String email;

    @JdbcTypeCode(SqlTypes.CHAR)
    @Column(name = "pw_hash", length = 64, nullable = false)
    private String password;

    @JdbcTypeCode(SqlTypes.VARCHAR)
    @Column(name = "fullname", length = 100, nullable = false)
    private String fullname;

    @JdbcTypeCode(SqlTypes.VARCHAR)
    @Column(name = "phone", length = 15, nullable = true)
    private String phone;

    @Column(name = "avatar_url", columnDefinition = "text", nullable = true)
    private String avatar;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "role", nullable = false)
    private Role role;

    @JdbcTypeCode(SqlTypes.TIMESTAMP)
    @Generated(event = EventType.INSERT)
    @Column(name = "created_at", insertable = false, updatable = false, nullable = false)
    private LocalDateTime created;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "login_variant", nullable = false)
    private LoginVariant loginVariant;

    @OneToOne(cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    private UserProfile profile;

    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    private List<AiTryonHistory> tryonHistory;

    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    private List<Cart> carts;

    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    private List<UserCoupon> userCoupons;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Order> orders;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Review> reviews;

    public User() {}

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public LoginVariant getLoginVariant() {
        return loginVariant;
    }

    public void setLoginVariant(LoginVariant loginVariant) {
        this.loginVariant = loginVariant;
    }

    public UserProfile getProfile() {
        return profile;
    }

    public void setProfile(UserProfile profile) {
        this.profile = profile;
    }

    public List<AiTryonHistory> getTryonHistory() {
        return tryonHistory;
    }

    public void setTryonHistory(List<AiTryonHistory> tryonHistory) {
        this.tryonHistory = tryonHistory;
    }

    public List<Cart> getCarts() {
        return carts;
    }

    public void setCarts(List<Cart> carts) {
        this.carts = carts;
    }

    public List<UserCoupon> getUserCoupons() {
        return userCoupons;
    }

    public void setUserCoupons(List<UserCoupon> userCoupons) {
        this.userCoupons = userCoupons;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

}