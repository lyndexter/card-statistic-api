package com.lyndexter.card_statistic_api.repositories;

import com.lyndexter.card_statistic_api.models.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, String> {}
