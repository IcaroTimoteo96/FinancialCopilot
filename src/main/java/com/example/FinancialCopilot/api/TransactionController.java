package com.example.FinancialCopilot.api;

import com.example.FinancialCopilot.entities.model.Transaction;
import com.example.FinancialCopilot.useCases.dto.TransferRequestDTO;
import com.example.FinancialCopilot.useCases.dto.TransferResponseDTO;
import com.example.FinancialCopilot.useCases.port.FindAllTransactionsInput;
import com.example.FinancialCopilot.useCases.port.CreateTransactionInput;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SpringBootApplication
@RestController
public class TransactionController {

    private final CreateTransactionInput _transactionInput;
    private final FindAllTransactionsInput _findAllTransactionsInput;

    public TransactionController(CreateTransactionInput transactionInput, FindAllTransactionsInput findAllTransactionsInput) {
        _transactionInput = transactionInput;
        _findAllTransactionsInput = findAllTransactionsInput;
    }

    @PostMapping("/insert-transaction")
    public ResponseEntity<TransferResponseDTO> insertTransaction(@RequestBody TransferRequestDTO request){
        TransferResponseDTO response = _transactionInput.createTransaction(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/findAll-transaction")
    public ResponseEntity<List<Transaction>> findAllTransaction() {
        List<Transaction> response = _findAllTransactionsInput.findAllTransactions();
        return ResponseEntity.ok(response);
    }
}
