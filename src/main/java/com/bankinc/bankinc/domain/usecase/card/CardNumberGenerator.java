package com.bankinc.bankinc.domain.usecase.card;

import com.bankinc.bankinc.domain.model.card.gateway.CardRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.util.Random;

@RequiredArgsConstructor
public class CardNumberGenerator {

    private  final CardRepository cardRepository;


    public Mono<String> numberGenerator(String  cardId){
        Random random = new Random();

        Long randomCardNumber = 1000000000L + ((long) (random.nextDouble() * 9000000000L));

        return cardRepository.getCardById(cardId)
                .flatMap(card -> Mono.just("The card already exist"))
                .switchIfEmpty(Mono
                        .defer(() -> Mono
                                .just(cardId + String.valueOf(randomCardNumber))));
    }

}
