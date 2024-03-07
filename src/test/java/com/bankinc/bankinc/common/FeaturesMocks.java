package com.bankinc.bankinc.common;

import com.bankinc.bankinc.domain.model.card.BalanceManager;
import com.bankinc.bankinc.domain.model.card.Card;

import java.time.LocalDate;

public class FeaturesMocks {


    public static final Card CARD = Card.builder()
            .cardId("1234567894567891")
            .productId("1234")
            .customerName("Alex Mendez")
            .currency(500.0)
            .expirationDate(LocalDate.now())
            .isActive(Boolean.TRUE)
            .build();

    public static final BalanceManager BALANCE_MANAGER = BalanceManager.builder()
            .cardId("1234567894567891")
            .balance(250.2)
            .build();


    public static final String SUCCESS_MSG = "SUCCESS";

    public static final String CARD_IS_EXIST_MSG = "The card already exist";

}
