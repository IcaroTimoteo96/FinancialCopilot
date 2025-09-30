package com.example.FinancialCopilot.useCases.port;

import com.example.FinancialCopilot.entities.model.Transaction;

import java.util.List;

public interface FindAllTransactionsInput {
    List<Transaction> findAllTransactions();
}
