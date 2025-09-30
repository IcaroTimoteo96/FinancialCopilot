package com.example.FinancialCopilot.useCases;

import com.example.FinancialCopilot.entities.model.Transaction;
import com.example.FinancialCopilot.useCases.port.FindAllTransactionsInput;
import com.example.FinancialCopilot.useCases.port.TransactionRepository;

import java.util.List;

public class FindAllTransactionsUseCase implements FindAllTransactionsInput {
    private final TransactionRepository _transactionRepository;

    public FindAllTransactionsUseCase(TransactionRepository transactionRepository) {
        _transactionRepository = transactionRepository;
    }

    @Override
    public List<Transaction> findAllTransactions() {
        List<Transaction> allTransactions = _transactionRepository.findAll();
        return allTransactions;
    }

}
