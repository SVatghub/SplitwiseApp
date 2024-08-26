package com.sahil.SplitwiseApp.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "expenses")
public class Expenses {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "user_id",nullable = false)
    private int userId;

    @Column(name = "title",nullable = true)
    private String title;

    @Column(name = "amount",nullable = false)
    private BigDecimal amount;

    @Column(name = "created_at",nullable = false,updatable = false)
    private Timestamp createdAt;

    @Column(name = "updated_at",nullable = false)
    private Timestamp updatedAt;

    @OneToMany(mappedBy = "expense" ,cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<DebtUsers> debtUsersList;

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
