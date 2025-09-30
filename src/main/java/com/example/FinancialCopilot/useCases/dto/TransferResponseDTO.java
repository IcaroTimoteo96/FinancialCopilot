package com.example.FinancialCopilot.useCases.dto;

import java.time.LocalDateTime;

public record TransferResponseDTO(long Id, String description,
                                  double amount,
                                  LocalDateTime date) {
}
