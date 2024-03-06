package com.bankinc.bankinc.domain.usecase.card;

import com.bankinc.bankinc.domain.model.card.BalanceManager;
import com.bankinc.bankinc.domain.model.card.gateway.CardRepository;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class BalanceAdministrator {

    private final CardRepository cardRepository;

    public Mono<BalanceManager> rechargeCardBalance(BalanceManager rechargeBalance){
        return cardRepository.getCardById(rechargeBalance.getCardId())
                .map(card -> card.toBuilder()
                        .currency(card.getCurrency() + rechargeBalance.getBalance())
                        .build())
                .flatMap(cardRepository::updateCard)
                .map(card -> rechargeBalance.toBuilder()
                        .balance(card.getCurrency())
                        .build());
    }

    public Mono<BalanceManager> checkBalance(String  cardId){
        return cardRepository.getCardById(cardId)
                .map(card -> BalanceManager.builder()
                        .cardId(cardId)
                        .balance(card.getCurrency())
                        .build());
    }

}
