package com.example.FinancialCopilot.useCases.port;
import com.example.FinancialCopilot.useCases.dto.TransferRequestDTO;
import com.example.FinancialCopilot.useCases.dto.TransferResponseDTO;

import java.io.IOException;

public interface CreateTransactionInput {
    TransferResponseDTO createTransaction(TransferRequestDTO request);
}
