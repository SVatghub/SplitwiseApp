package com.sahil.SplitwiseApp.model;

import jakarta.persistence.*;
import org.springframework.context.annotation.Scope;

import java.sql.Timestamp;

@Scope("prototype")
@Entity
@Table(name = "Users")
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;

    private String name;
    private String email;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    public Users() {
    }

    public Users(int userId, String name, String email, Timestamp updatedAt, Timestamp createdAt) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.updatedAt = updatedAt;
        this.createdAt = createdAt;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
