package com.bankinc.bankinc.infrastructure.adapter.cardadapter;

import com.bankinc.bankinc.domain.model.card.Card;
import com.bankinc.bankinc.domain.model.card.gateway.CardRepository;
import com.bankinc.bankinc.infrastructure.adapter.cardadapter.model.CardDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class CardAdapter implements CardRepository {

    private final CardRepositoryIntegration repository;

    @Autowired
    public CardAdapter(CardRepositoryIntegration repository) {
        this.repository = repository;
    }


    @Override
    public Mono<Card> getCardById(String id) {
        return repository.findById(id)
                .map(this::mapToModel);
    }

    @Override
    public Mono<String> saveCard(Card card) {
        CardDTO cardDTO = mapToEntity(card);

        return repository.findById(card.getCardId())
                .map(cardDTO1 -> "The card already exist")
                .switchIfEmpty(Mono.defer(() -> repository.insertCard(cardDTO.getCardId(), cardDTO.getProductId(),
                                cardDTO.getCustomerName(), cardDTO.getExpirationDate(),
                                cardDTO.getCurrency(), cardDTO.getIsActive())
                        .then(Mono.fromCallable(() -> "the card has been saved successfully"))
                ));

    }

    @Override
    public Mono<Void> deleteCardById(String id) {
        return repository.findById(id)
                .flatMap(cardDTO -> repository.deleteById(cardDTO.getCardId()));
    }

    @Override
    public Mono<Card> updateCard(Card card) {
        return repository.save(mapToEntity(card))
                .map(this::mapToModel);
    }

    private Card mapToModel(CardDTO cardDTO) {
        return Card.builder()
                .cardId(cardDTO.getCardId())
                .productId(cardDTO.getProductId())
                .customerName(cardDTO.getCustomerName())
                .expirationDate(cardDTO.getExpirationDate())
                .currency(cardDTO.getCurrency())
                .isActive(cardDTO.getIsActive())
                .build();
    }

    private CardDTO mapToEntity(Card card) {
        return CardDTO.builder()
                .cardId(card.getCardId())
                .productId(card.getProductId())
                .customerName(card.getCustomerName())
                .expirationDate(card.getExpirationDate())
                .currency(card.getCurrency())
                .isActive(card.getIsActive())
                .build();
    }
}
