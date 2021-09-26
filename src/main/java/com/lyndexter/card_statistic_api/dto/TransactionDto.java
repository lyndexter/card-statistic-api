package com.lyndexter.card_statistic_api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.hateoas.RepresentationModel;



@Data
@AllArgsConstructor
public class TransactionDto extends RepresentationModel<TransactionDto> {
    private String description;
    private int time;
    private int amount;
    private int cashbackAmount;
    private int balance;
    
    
    
}
