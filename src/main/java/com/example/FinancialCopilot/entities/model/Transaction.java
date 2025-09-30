package com.example.FinancialCopilot.entities.model;
import java.time.LocalDateTime;

public class Transaction {

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

    public Transaction() {}

    public Transaction(String description, double amount, LocalDateTime date) {
        this.description = description;
        this.amount = amount;
        this.date = date;
    }

    public Transaction(long id, String description, double amount, LocalDateTime date) {
        this.Id = id;
        this.description = description;
        this.amount = amount;
        this.date = date;
    }

    private long Id;

    private String description;

    private double amount;

    private LocalDateTime date;
}
