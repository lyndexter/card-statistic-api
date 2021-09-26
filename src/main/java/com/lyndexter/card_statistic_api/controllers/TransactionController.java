package com.lyndexter.card_statistic_api.controllers;

import com.lyndexter.card_statistic_api.dto.TransactionDto;
import com.lyndexter.card_statistic_api.models.Transaction;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import java.util.Arrays;
import java.util.Collections;

@RestController
@RequestMapping("/lyndexter")
public class TransactionController {

  @Value("${lyndexter.bank-token}")
  private String BANK_TOKEN;

  private final RestTemplate restTemplate;

  public TransactionController(RestTemplateBuilder restTemplateBuilder) {
    this.restTemplate = restTemplateBuilder.build();
  }

  @GetMapping("/transactions/day/{time}")
  public ResponseEntity<TransactionDto[]> getTransactionForDay(@PathVariable int time) {

    String url = "https://api.monobank.ua/personal/statement/0/1630497000/1632661515";
    HttpHeaders httpHeaders = new HttpHeaders();
    httpHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
    httpHeaders.add("X-Token", BANK_TOKEN);
    HttpEntity request = new HttpEntity(httpHeaders);
    ResponseEntity<Transaction[]> transactions =
        restTemplate.exchange(url, HttpMethod.GET, request, Transaction[].class);

    TransactionDto[] transactionList =
        Arrays.stream(transactions.getBody())
            .filter(
                transaction -> transaction.getTime() > time && transaction.getTime() < time + 86400)
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
  public ResponseEntity<TransactionDto[]> getTransactionForWeek(@PathVariable int time) {

    String url = "https://api.monobank.ua/personal/statement/0/1630497000/1632661515";
    HttpHeaders httpHeaders = new HttpHeaders();
    httpHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
    httpHeaders.add("X-Token", BANK_TOKEN);
    HttpEntity request = new HttpEntity(httpHeaders);
    ResponseEntity<Transaction[]> transactions =
        restTemplate.exchange(url, HttpMethod.GET, request, Transaction[].class);

    TransactionDto[] transactionList =
        Arrays.stream(transactions.getBody())
            .filter(
                transaction ->
                    transaction.getTime() > time && transaction.getTime() < time + 604800)
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
