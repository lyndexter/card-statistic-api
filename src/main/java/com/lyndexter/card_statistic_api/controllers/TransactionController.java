package com.lyndexter.card_statistic_api.controllers;

import com.lyndexter.card_statistic_api.dto.TransactionDto;
import com.lyndexter.card_statistic_api.models.Transaction;
import com.lyndexter.card_statistic_api.services.TransactionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/lyndexter")
public class TransactionController {

  private final TransactionService transactionService;

  public TransactionController(TransactionService transactionService) {
    this.transactionService = transactionService;
  }

  @GetMapping("/transactions/day/{time}")
  public ResponseEntity<TransactionDto[]> getTransactionsForDay(@PathVariable int time) {

    List<Transaction> transactions = transactionService.getTransactions(time, time + 86400);
    TransactionDto[] transactionList =
        transactions.stream()
            .map(
                transaction ->
                    new TransactionDto(
                        transaction.getDescription(),
                        transaction.getTime(),
                        transaction.getAmount(),
                        transaction.getCashbackAmount(),
                        transaction.getBalance()))
            .toArray(TransactionDto[]::new);

    return new ResponseEntity<>(transactionList, HttpStatus.OK);
  }

  @GetMapping("/transactions/week/{time}")
  public ResponseEntity<TransactionDto[]> getTransactionsForWeek(@PathVariable int time) {
    List<Transaction> transactions = transactionService.getTransactions(time, time + 604800);
    TransactionDto[] transactionList =
        transactions.stream()
            .map(
                transaction ->
                    new TransactionDto(
                        transaction.getDescription(),
                        transaction.getTime(),
                        transaction.getAmount(),
                        transaction.getCashbackAmount(),
                        transaction.getBalance()))
            .toArray(TransactionDto[]::new);

    return new ResponseEntity<>(transactionList, HttpStatus.OK);
  }
}
