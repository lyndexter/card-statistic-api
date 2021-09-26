package com.lyndexter.card_statistic_api.services;

import com.lyndexter.card_statistic_api.models.Transaction;
import com.lyndexter.card_statistic_api.repositories.TransactionRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {

  private JpaRepository<Transaction, String> transactionRepository;

  public TransactionService(TransactionRepository transactionRepository) {
    this.transactionRepository = transactionRepository;
  }

  public int writeTransactions(Iterable<Transaction> transactions) {
    transactionRepository.saveAll(transactions);
    return 1;
  }
}
