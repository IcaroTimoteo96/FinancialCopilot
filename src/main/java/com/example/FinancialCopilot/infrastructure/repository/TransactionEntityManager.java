package com.example.FinancialCopilot.infrastructure.repository;

import com.example.FinancialCopilot.infrastructure.entity.TransactionEntity;
import com.pgvector.PGvector;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class TransactionEntityManager {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public TransactionEntity insertWithEmbedding(String description, Double amount, LocalDateTime date, String embedding) {
        TransactionEntity entity = new TransactionEntity();

        try{
            Query insertQuery = entityManager.createNativeQuery(
                    "INSERT INTO transactions (description, amount, transaction_date, embedding) VALUES (?1, ?2, ?3, CAST(?4 AS vector)) RETURNING id"
            );
            insertQuery.setParameter(1, description);
            insertQuery.setParameter(2, amount);
            insertQuery.setParameter(3, date);
            insertQuery.setParameter(4, embedding);

            Long id = ((Number) insertQuery.getSingleResult()).longValue();

            Query selectQuery = entityManager.createNativeQuery(
                    "SELECT id, description, amount, transaction_date, embedding::text FROM transactions WHERE id = ?1"
            );
            selectQuery.setParameter(1, id);

            Object[] result = (Object[]) selectQuery.getSingleResult();

            entity.setId(((Number) result[0]).longValue());
            entity.setDescription((String) result[1]);
            entity.setAmount(((Number) result[2]).doubleValue());
            entity.setDate(((java.sql.Timestamp) result[3]).toLocalDateTime());
            entity.setEmbedding(new PGvector((String) result[4]));

        }catch(Exception e){
            throw new RuntimeException(e);
        }
        return entity;
    }

    public List<TransactionEntity> findAllTransactions(){
        Query query = entityManager.createNativeQuery(
                "SELECT id, description, amount, transaction_date FROM transactions"
        );

        List<Object[]> results = query.getResultList();
        List<TransactionEntity> entityList = new ArrayList<>();

        for (Object[] result : results) {
            TransactionEntity entity = new TransactionEntity();
            entity.setId(((Number) result[0]).longValue());
            entity.setDescription((String) result[1]);
            entity.setAmount(((Number) result[2]).doubleValue());
            entity.setDate(((java.sql.Timestamp) result[3]).toLocalDateTime());

            entityList.add(entity);
        }

        return entityList;
    }

    public List<TransactionEntity> findAllTransactionsWithEmbedding() {
        List<TransactionEntity> entityList = new ArrayList<>();

        try{
            Query query = entityManager.createNativeQuery(
                    "SELECT id, description, amount, transaction_date, embedding::text FROM transactions"
            );

            List<Object[]> results = query.getResultList();

            for(Object[] result : results){
                TransactionEntity entity
                        = new TransactionEntity();

                entity.setId(((Number) result[0]).longValue());
                entity.setDescription((String) result[1]);
                entity.setAmount(((Number) result[2]).doubleValue());
                entity.setDate(((java.sql.Timestamp) result[3]).toLocalDateTime());

                entityList.add(entity);
            }

        }catch (Exception e) {
            throw new RuntimeException("Erro ao converter embedding: " + e.getMessage(), e);
        }

        return entityList;
    }
}
