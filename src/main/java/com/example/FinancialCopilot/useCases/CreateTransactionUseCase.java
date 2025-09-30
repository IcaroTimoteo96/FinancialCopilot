package com.example.FinancialCopilot.useCases;

import com.example.FinancialCopilot.entities.model.Transaction;
import com.example.FinancialCopilot.infrastructure.entity.GeminiEmbeddingService;
import com.example.FinancialCopilot.useCases.dto.TransferRequestDTO;
import com.example.FinancialCopilot.useCases.dto.TransferResponseDTO;
import com.example.FinancialCopilot.useCases.port.CreateTransactionInput;
import com.example.FinancialCopilot.useCases.port.TransactionRepository;
import io.github.cdimascio.dotenv.Dotenv;

import java.util.List;

public class CreateTransactionUseCase implements CreateTransactionInput {
    private final TransactionRepository _transactionRepository;

    public CreateTransactionUseCase(TransactionRepository transactionRepository) {
        _transactionRepository = transactionRepository;
    }

    @Override
    public TransferResponseDTO createTransaction(TransferRequestDTO request){
        Dotenv dotenv = Dotenv.load();
        String api_key = dotenv.get("API_KEY_GEMINI");

        Transaction newTransaction =
                new Transaction(request.description(),
                        request.amount(),
                        request.date());

        GeminiEmbeddingService service = new GeminiEmbeddingService(api_key);

        List<Float> embedding = service.getEmbeddingAsList(request.description());

        Transaction savedTransaction = _transactionRepository.save(newTransaction, embedding);

        TransferResponseDTO response = new TransferResponseDTO(savedTransaction.getId(),
                savedTransaction.getDescription(),
                savedTransaction.getAmount(),
                savedTransaction.getDate());

        return response;
    }
}
