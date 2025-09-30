package com.example.FinancialCopilot.useCases.dto;

import java.time.LocalDateTime;

public record TransferRequestDTO(String description,
         double amount,
         LocalDateTime date) { }
