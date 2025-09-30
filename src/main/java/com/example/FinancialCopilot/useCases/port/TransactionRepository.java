package com.example.FinancialCopilot.useCases.port;

import com.example.FinancialCopilot.entities.model.Transaction;

import java.util.List;

public interface TransactionRepository {
    Transaction save(Transaction transaction, List<Float> embedding);
    List<Transaction> findAll();
}
