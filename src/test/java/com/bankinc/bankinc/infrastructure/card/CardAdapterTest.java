package com.bankinc.bankinc.infrastructure.card;

import com.bankinc.bankinc.domain.model.card.Card;
import com.bankinc.bankinc.infrastructure.adapter.cardadapter.CardAdapter;
import com.bankinc.bankinc.infrastructure.adapter.cardadapter.CardRepositoryIntegration;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static com.bankinc.bankinc.common.FeaturesMocks.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CardAdapterTest {

    @Mock
    private CardRepositoryIntegration cardRepositoryIntegration;

    @InjectMocks
    private CardAdapter cardAdapter;


    @Test
    void getCardByIdTest(){

        when(cardRepositoryIntegration.findById(anyString()))
                .thenReturn(Mono.just(CARD_DTO));

        Mono<Card> result = cardAdapter.getCardById(anyString());

        StepVerifier.create(result)
                .assertNext(card -> {
                    Assertions.assertEquals(CARD_DTO.getCardId(), card.getCardId());
                }).verifyComplete();


        verify(cardRepositoryIntegration, times(1)).findById(anyString());

    }

    @Test
    void saveCardTest(){

        when(cardRepositoryIntegration.findById(anyString()))
                .thenReturn(Mono.just(CARD_DTO));

        Mono<String> result = cardAdapter.saveCard(CARD);

        StepVerifier.create(result)
                .assertNext(response -> {
                    Assertions.assertEquals(CARD_IS_EXIST_MSG, response);
                }).verifyComplete();


        verify(cardRepositoryIntegration, times(1)).findById(anyString());


    }

    @Test
    void saveCardIsNotExistTest(){

        when(cardRepositoryIntegration.findById(anyString()))
                .thenReturn(Mono.empty());

        when(cardRepositoryIntegration.insertCard(anyString(),anyString(), anyString(), any(), anyDouble(), anyBoolean()))
                .thenReturn(Mono.empty());

        Mono<String> result = cardAdapter.saveCard(CARD);

        StepVerifier.create(result)
                .assertNext(response -> {
                    Assertions.assertEquals(CARD_CREATED_MSG, response);
                }).verifyComplete();


        verify(cardRepositoryIntegration, times(1)).findById(anyString());



    }

    @Test
    void updateCardTest(){

        when(cardRepositoryIntegration.save(any()))
                .thenReturn(Mono.just(CARD_DTO));

        Mono<Card> result = cardAdapter.updateCard(CARD);

        StepVerifier.create(result)
                .assertNext(card -> {
                    Assertions.assertEquals(CARD_DTO.getCardId(), card.getCardId());
                }).verifyComplete();


        verify(cardRepositoryIntegration, times(1)).save(any());


    }



}
