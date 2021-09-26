package com.lyndexter.card_statistic_api.models;

import lombok.Data;

@Data
public class Account {
  private String id;
  private String sendId;
  private int currencyCode;
  private String cashbackType;
  private int balance;
  private int creditLimit;
  private String[] maskedPan;
  private String type;
  private String iban;
}
