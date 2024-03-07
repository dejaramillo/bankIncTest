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

import static com.bankinc.bankinc.common.FeaturesMocks.CARD;
import static com.bankinc.bankinc.common.FeaturesMocks.SUCCESS_MSG;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CardActivatorTest {

    @Mock
    private CardRepository cardRepository;

    @InjectMocks
    private CardActivator cardActivator;


    @Test
    void saveCardTest(){

        when(cardRepository.saveCard(any()))
                .thenReturn(Mono.just(SUCCESS_MSG));

        Mono<String> result = cardActivator.saveCard(CARD.getCardId());


        StepVerifier.create(result)
                .assertNext(response -> {
                    assertInstanceOf(String.class, response);
                    assertEquals(SUCCESS_MSG, response);
                }).verifyComplete();


        verify(cardRepository, times(1)).saveCard(any());



    }

}
