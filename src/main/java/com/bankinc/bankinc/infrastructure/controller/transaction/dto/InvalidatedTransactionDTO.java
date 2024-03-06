package com.bankinc.bankinc.infrastructure.controller.transaction.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder(toBuilder = true)
public class InvalidatedTransactionDTO {

    private String cardId;
    private String transactionId;

}
