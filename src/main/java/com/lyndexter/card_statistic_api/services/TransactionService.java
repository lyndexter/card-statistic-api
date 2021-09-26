package com.lyndexter.card_statistic_api.services;

import com.lyndexter.card_statistic_api.models.Transaction;
import com.lyndexter.card_statistic_api.repositories.TransactionRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TransactionService {

  private final TransactionRepository transactionRepository;

  public TransactionService(TransactionRepository transactionRepository) {
    this.transactionRepository = transactionRepository;
  }

  public void writeTransactions(Iterable<Transaction> transactions) {
    transactionRepository.saveAll(transactions);
  }

  public List<Transaction> getTransactions(int startTime, int endTime) {
    return transactionRepository.findByTimeBetween(startTime, endTime);
  }
}
