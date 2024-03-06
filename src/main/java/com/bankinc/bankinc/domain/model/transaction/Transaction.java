package com.bankinc.bankinc.domain.model.transaction;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {

    private String transactionId;
    private Double price;
    private String cardId;
    private LocalDateTime transactionDate;
    private Boolean isInvalidated;

}
