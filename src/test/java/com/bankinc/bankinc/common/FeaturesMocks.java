package com.bankinc.bankinc.common;

import com.bankinc.bankinc.domain.model.card.BalanceManager;
import com.bankinc.bankinc.domain.model.card.Card;
import com.bankinc.bankinc.domain.model.transaction.InvalidatedTransaction;
import com.bankinc.bankinc.domain.model.transaction.SaveTransaction;
import com.bankinc.bankinc.domain.model.transaction.Transaction;
import com.bankinc.bankinc.infrastructure.adapter.cardadapter.model.CardDTO;

import java.time.LocalDate;
import java.time.LocalDateTime;

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


    public static final SaveTransaction SAVE_TRANSACTION = SaveTransaction.builder()
            .transactionId("1020308090")
            .cardId(CARD.getCardId())
            .price(60.0)
            .build();

    public static final Transaction TRANSACTION = Transaction.builder()
            .transactionId(SAVE_TRANSACTION.getTransactionId())
            .cardId(CARD.getCardId())
            .transactionDate(LocalDateTime.now())
            .price(60.0)
            .isInvalidated(Boolean.FALSE)
            .build();


    public static final InvalidatedTransaction INVALIDATED_TRANSACTION = InvalidatedTransaction
            .builder()
            .transactionId(TRANSACTION.getTransactionId())
            .cardId(CARD.getCardId())
            .build();

    public static final CardDTO CARD_DTO = CardDTO.builder()
            .cardId("1234567894567891")
            .productId("1234")
            .customerName("Alex Mendez")
            .currency(500.0)
            .expirationDate(LocalDate.now())
            .isActive(Boolean.TRUE)
            .build();



    public static final String SUCCESS_MSG = "SUCCESS";

    public static final String CARD_IS_EXIST_MSG = "The card already exist";

    public static final String INVALIDATED_TRANSACTION_MSG = "Transaction was invalidated";

    public static final String CARD_CREATED_MSG = "the card has been saved successfully";
}
