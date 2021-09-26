package com.lyndexter.card_statistic_api.controllers;

import com.lyndexter.card_statistic_api.models.Transaction;
import com.lyndexter.card_statistic_api.models.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import java.util.Collections;

@RestController
@RequestMapping("/lyndexter")
public class RedirectionController {
  
//  @Value("${BANK_TOKEN}")
  @Value("${lyndexter.bank-token}")
  private String BANK_TOKEN ;
  
  private final RestTemplate restTemplate;

  public RedirectionController(RestTemplateBuilder restTemplateBuilder) {
    this.restTemplate = restTemplateBuilder.build();
  }

  @GetMapping("/statistic")
  public ResponseEntity<Transaction[]> getStatistic() {
    String url = "https://api.monobank.ua/personal/statement/0/1630497000/1632661515";
    HttpHeaders httpHeaders = new HttpHeaders();
    httpHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
    httpHeaders.add("X-Token", BANK_TOKEN);
    HttpEntity request = new HttpEntity(httpHeaders);
    ResponseEntity<Transaction[]> transactions =
        restTemplate.exchange(url, HttpMethod.GET, request, Transaction[].class);
    return transactions;
  }
  
  @GetMapping("/client-info")
  public ResponseEntity<User> getUserInfo(){
    String url = "https://api.monobank.ua/personal/client-info";
    HttpHeaders httpHeaders = new HttpHeaders();
    httpHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
    httpHeaders.add("X-Token", BANK_TOKEN);
    HttpEntity request = new HttpEntity(httpHeaders);
    ResponseEntity<User> user =
        restTemplate.exchange(url, HttpMethod.GET, request, User.class);
    return user;
  }
}
