package com.bankinc.bankinc.domain.model.card.gateway;

import com.bankinc.bankinc.domain.model.card.Card;
import reactor.core.publisher.Mono;

public interface CardRepository {
    Mono<Card> getCardById(String id);

    Mono<String> saveCard(Card card);

    Mono<Void> deleteCardById(String id);

    Mono<Card> updateCard(Card card);
}
