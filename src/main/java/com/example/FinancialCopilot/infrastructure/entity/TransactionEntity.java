package com.example.FinancialCopilot.infrastructure.entity;

import com.pgvector.PGvector;
import jakarta.persistence.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.math.BigInteger;
import java.time.LocalDateTime;

@Entity
@Table(name="transactions")
public class TransactionEntity {

    public long getId() {
        return Id;
    }

    public void setId(long id) {
        Id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public PGvector getEmbedding() {
        return embedding;
    }

    public void setEmbedding(PGvector embedding) {
        this.embedding = embedding;
    }

    public TransactionEntity() {}

    public TransactionEntity(String description, double amount, LocalDateTime date) {
        this.description = description;
        this.amount = amount;
        this.date = date;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long Id;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "amount", nullable = false)
    private double amount;

    @Column(name = "transaction_date", nullable = false)
    private LocalDateTime date;

    @Column(name = "embedding", columnDefinition = "vector(768)")
    private PGvector embedding;
}
