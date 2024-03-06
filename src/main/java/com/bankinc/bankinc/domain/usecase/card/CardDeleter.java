package com.bankinc.bankinc.domain.usecase.card;

import com.bankinc.bankinc.domain.model.card.gateway.CardRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class CardDeleter {

    private final CardRepository cardRepository;

    public Mono<String> deleteCardById(String id){
        return cardRepository.deleteCardById(id)
                .then(Mono.fromCallable(() -> "The card has been deleted successfully"));
    }


}
