package com.bankinc.bankinc.domain.usecase.card;

import com.bankinc.bankinc.domain.model.card.gateway.CardRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.lang.annotation.Inherited;

import static com.bankinc.bankinc.common.FeaturesMocks.CARD;
import static com.bankinc.bankinc.common.FeaturesMocks.CARD_IS_EXIST_MSG;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CardNumberGeneratorTest {


    @Mock
    private CardRepository cardRepository;

    @InjectMocks
    private CardNumberGenerator cardNumberGenerator;


    @Test
    void numberGeneratorIsExistCardTest(){

        when(cardRepository.getCardById(anyString()))
                .thenReturn(Mono.just(CARD));


        Mono<String> result = cardNumberGenerator.numberGenerator(CARD.getProductId());

        StepVerifier.create(result)
                .assertNext(response -> {
                    Assertions.assertInstanceOf(String.class, response);
                    Assertions.assertEquals(CARD_IS_EXIST_MSG, response);
                }).verifyComplete();


        verify(cardRepository, times(1)).getCardById(anyString());


    }



    @Test
    void numberGeneratorIsNotExistCardTest(){

        when(cardRepository.getCardById(anyString()))
                .thenReturn(Mono.empty());


        Mono<String> result = cardNumberGenerator.numberGenerator(CARD.getProductId());

        StepVerifier.create(result)
                .assertNext(response -> {
                    Assertions.assertInstanceOf(String.class, response);
                Assertions.assertNotEquals(CARD.getCardId(), response);
                }).verifyComplete();


        verify(cardRepository, times(1)).getCardById(anyString());

    }



}
