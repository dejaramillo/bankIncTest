package com.bankinc.bankinc.domain.usecase.card;

import com.bankinc.bankinc.domain.model.card.Card;
import com.bankinc.bankinc.domain.model.card.gateway.CardRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.time.LocalDate;

@RequiredArgsConstructor
public class CardActivator {

    private static final String DEFAULT_CUSTOMER_NAME = "Alex Martínez";
    private static final double ACTIVATE_CURRENCY = 0;
    private static final int YEAR_EXPIRATION_CARD = 3;
    private static final int START_CARD_ID = 0;
    private static final int END_CARD_ID = 6;


    private final CardRepository cardRepository;


    public Mono<String> saveCard(String cardNumber){
        return cardRepository.saveCard(configNewCard(cardNumber));
    }

    private Card configNewCard(String cardNumber){
        return Card.builder()
                .cardId(cardNumber)
                .productId(cardNumber.substring(START_CARD_ID,
                        END_CARD_ID))
                .customerName(DEFAULT_CUSTOMER_NAME)
                .expirationDate(LocalDate.now().plusYears(YEAR_EXPIRATION_CARD))
                .currency(ACTIVATE_CURRENCY)
                .isActive(Boolean.TRUE)
                .build();
    }

}
