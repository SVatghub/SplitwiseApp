package com.sahil.SplitwiseApp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Entity
@Table(name = "DebtUsers")
public class DebtUsers {

    @Id
    private int id;
    private int userId;
    private int expenseId;
    private BigDecimal debtAmount;
    private boolean isSettled;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    public DebtUsers() {
    }

    public DebtUsers(int id, Timestamp updatedAt, int expenseId, boolean isSettled, BigDecimal debtAmount, int userId, Timestamp createdAt) {
        this.id = id;
        this.updatedAt = updatedAt;
        this.expenseId = expenseId;
        this.isSettled = isSettled;
        this.debtAmount = debtAmount;
        this.userId = userId;
        this.createdAt = createdAt;
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

    public int getExpenseId() {
        return expenseId;
    }

    public void setExpenseId(int expenseId) {
        this.expenseId = expenseId;
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
}
