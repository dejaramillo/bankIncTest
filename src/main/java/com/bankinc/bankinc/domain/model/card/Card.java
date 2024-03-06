package com.bankinc.bankinc.domain.model.card;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Card {

    private String cardId;
    private String productId;
    private String customerName;
    private LocalDate expirationDate;
    private Double currency;
    private Boolean isActive;
}
