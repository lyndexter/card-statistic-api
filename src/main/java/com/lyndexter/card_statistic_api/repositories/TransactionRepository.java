package com.lyndexter.card_statistic_api.repositories;

import com.lyndexter.card_statistic_api.models.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, String> {

  List<Transaction> findByTimeBetween(int start, int end);
}
