package com.code.models;

import java.time.LocalDateTime;
import java.util.Map;

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
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "user_profiles")
public class UserProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JdbcTypeCode(SqlTypes.INTEGER)
    @Column(name = "id", updatable = false, nullable = false)
    private int id;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "metadata")
    private Map<String, Integer> metadata;

    @JdbcTypeCode(SqlTypes.TIMESTAMP)
    @Generated(event = { EventType.INSERT, EventType.UPDATE })
    @Column(name = "updated_at", insertable = false, updatable = false, nullable = false)
    private LocalDateTime updated;

    @JdbcTypeCode(SqlTypes.BOOLEAN)
    @Column(name = "is_active", nullable = false)
    private boolean active;

    @OneToOne
    @JoinColumn(name = "user_id", updatable = false, nullable = false)
    private User user;

    public UserProfile() { }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Map<String, Integer> getMetadata() {
        return metadata;
    }

    public void setMetadata(Map<String, Integer> metadata) {
        this.metadata = metadata;
    }

    public LocalDateTime getUpdated() {
        return updated;
    }

    public void setUpdated(LocalDateTime updated) {
        this.updated = updated;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}