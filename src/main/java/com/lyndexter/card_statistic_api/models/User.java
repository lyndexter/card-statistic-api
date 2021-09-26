package com.lyndexter.card_statistic_api.models;

import lombok.Data;
import java.util.List;

@Data
public class User {
  private String id;
  private String name;
  private String webHookUrl;
  private String permissions;
  private List<Account> accounts;
}
