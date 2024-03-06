package com.bankinc.bankinc.infrastructure.controller.transaction.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SaveTransactionDTO {

    private String cardId;
    private String transactionId;
    private Double price;

}
