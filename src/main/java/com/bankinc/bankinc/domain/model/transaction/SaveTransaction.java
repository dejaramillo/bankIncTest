package com.bankinc.bankinc.domain.model.transaction;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class SaveTransaction {
    private String cardId;
    private String transactionId;
    private Double price;
}
