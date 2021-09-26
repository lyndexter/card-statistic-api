package com.lyndexter.card_statistic_api.controllers;

import com.lyndexter.card_statistic_api.models.Transaction;
import com.lyndexter.card_statistic_api.models.User;
import com.lyndexter.card_statistic_api.services.TransactionService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Arrays;
import java.util.Collections;
import java.util.Objects;

@Controller
@EnableScheduling
public class UpdateController {

  @Value("${lyndexter.bank-token}")
  private String BANK_TOKEN;

  private final RestTemplate restTemplate;
  private final TransactionService transactionService;

  public UpdateController(
      RestTemplateBuilder restTemplateBuilder, TransactionService transactionService) {
    this.restTemplate = restTemplateBuilder.build();
    this.transactionService = transactionService;
  }

  private static final String STATISTIC_URL = "https://api.monobank.ua/personal/statement/0/";
  private static final String CLIENT_INFO_URL = "https://api.monobank.ua/personal/client-info";

  @Scheduled(fixedRate = 1000 * 60 * 60)
  @GetMapping("/statistic")
  public void updateHistory() {
    String url =
        STATISTIC_URL
            + LocalDateTime.now().toEpochSecond(ZoneOffset.of("+03:00"))
            + "/"
            + LocalDateTime.now().plusMonths(1).toEpochSecond(ZoneOffset.of("+03:00"));

    HttpEntity<?> request = generateRequestEntity();
    ResponseEntity<Transaction[]> transactions =
        restTemplate.exchange(url, HttpMethod.GET, request, Transaction[].class);
    transactionService.writeTransactions(
        Arrays.asList(Objects.requireNonNull(transactions.getBody())));
  }

  @GetMapping("/client-info")
  public ResponseEntity<User> getUserInfo() {
    HttpEntity<?> request = generateRequestEntity();
    return restTemplate.exchange(CLIENT_INFO_URL, HttpMethod.GET, request, User.class);
  }

  private HttpEntity<?> generateRequestEntity() {
    HttpHeaders httpHeaders = new HttpHeaders();
    httpHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
    httpHeaders.add("X-Token", BANK_TOKEN);
    return new HttpEntity<>(httpHeaders);
  }
}
