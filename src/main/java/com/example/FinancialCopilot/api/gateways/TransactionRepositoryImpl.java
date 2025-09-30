package com.example.FinancialCopilot.api.gateways;

import com.example.FinancialCopilot.entities.model.Transaction;
import com.example.FinancialCopilot.infrastructure.entity.TransactionEntity;
import com.example.FinancialCopilot.infrastructure.repository.TransactionEntityManager;
import com.example.FinancialCopilot.useCases.port.TransactionRepository;
import com.pgvector.PGvector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;

@Repository
public class TransactionRepositoryImpl implements TransactionRepository {
    @Autowired
    private TransactionEntityManager transactionEntityManager;

    public TransactionRepositoryImpl() {
    }

    @Override
    public Transaction save(Transaction transaction, List<Float> embedding) {
        TransactionEntity transactionEntity =
                new TransactionEntity(transaction.getDescription(),
                transaction.getAmount(),
                transaction.getDate());

        TransactionEntity savedEntity = transactionEntityManager.insertWithEmbedding(
                transactionEntity.getDescription(),
                transactionEntity.getAmount(),
                transactionEntity.getDate(),
                new PGvector(embedding).toString()
        );

        Transaction response = new Transaction(
                        savedEntity.getId(),
                        savedEntity.getDescription(),
                        savedEntity.getAmount(),
                        savedEntity.getDate());

        return response;
    }

    @Override
    public List<Transaction> findAll(){
        List<TransactionEntity> listTransactionEntity = transactionEntityManager.findAllTransactions();
        List<Transaction> transactions = new ArrayList<>();

        listTransactionEntity.forEach(te -> {
            transactions.add(new Transaction(te.getId(),te.getDescription(),te.getAmount(),te.getDate()));
        });

        return transactions;
    }
}
