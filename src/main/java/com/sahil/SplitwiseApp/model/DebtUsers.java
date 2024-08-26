package com.sahil.SplitwiseApp.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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
}
