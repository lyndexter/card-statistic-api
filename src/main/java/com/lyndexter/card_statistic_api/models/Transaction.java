package com.lyndexter.card_statistic_api.models;

import lombok.Data;

@Data
public class Transaction {

  private String id;
  private int time;
  private String description;
  private int mcc;
  private int originalMcc;
  private int amount;
  private int operationAmount;
  private int currencyCode;
  private int commissionRate;
  private int cashbackAmount;
  private int balance;
  private boolean hold;
  private String receiptId;
}
