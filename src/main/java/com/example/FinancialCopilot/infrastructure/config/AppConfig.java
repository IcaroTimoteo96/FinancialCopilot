package com.example.FinancialCopilot.infrastructure.config;

import com.example.FinancialCopilot.useCases.FindAllTransactionsUseCase;
import com.example.FinancialCopilot.useCases.CreateTransactionUseCase;
import com.example.FinancialCopilot.useCases.port.FindAllTransactionsInput;
import com.example.FinancialCopilot.useCases.port.CreateTransactionInput;
import com.example.FinancialCopilot.useCases.port.TransactionRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Bean
    public CreateTransactionInput createTransactionInput(TransactionRepository transactionRepository) {
        return new CreateTransactionUseCase(transactionRepository);
    }

    @Bean
    public FindAllTransactionsInput askTransactionInput(TransactionRepository transactionRepository) {
        return new FindAllTransactionsUseCase(transactionRepository);
    }
}
