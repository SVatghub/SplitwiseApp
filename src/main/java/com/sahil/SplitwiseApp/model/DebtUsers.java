package com.sahil.SplitwiseApp.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.Instant;

@Entity
@Table(name = "debt_users")
public class DebtUsers {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "user_id",nullable = false)
    private int userId;

    @Column(name = "debt_amount",nullable = false)
    private BigDecimal debtAmount;

    @Column(name = "is_settled",nullable = false)
    private boolean isSettled;

    @Column(name = "created_at",nullable = false,updatable = false)
    private Timestamp createdAt;

    @Column(name = "updated_at",nullable = false)
    private Timestamp updatedAt;

    @ManyToOne
    @JoinColumn(name ="expense_id",referencedColumnName = "id")
    @JsonBackReference
    private Expenses expense;

    public DebtUsers() {
    }

    public DebtUsers(int id, Timestamp updatedAt, boolean isSettled, BigDecimal debtAmount, int userId, Timestamp createdAt) {
        this.id = id;
        this.updatedAt = updatedAt;
        this.isSettled = isSettled;
        this.debtAmount = debtAmount;
        this.userId = userId;
        this.createdAt = createdAt;
    }

    @PrePersist
    public void onCreate(){
        Timestamp now = Timestamp.from(Instant.now());
        this.createdAt = now;
        this.updatedAt = now;
    }

    @PreUpdate
    public void onUpdate(){
        this.updatedAt = Timestamp.from(Instant.now());
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public BigDecimal getDebtAmount() {
        return debtAmount;
    }

    public void setDebtAmount(BigDecimal debtAmount) {
        this.debtAmount = debtAmount;
    }

    public boolean isSettled() {
        return isSettled;
    }

    public void setSettled(boolean settled) {
        isSettled = settled;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Expenses getExpense() {
        return expense;
    }

    public void setExpense(Expenses expense) {
        this.expense = expense;
    }
}
