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
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CardDeleterTest {

    @Mock
    private CardRepository cardRepository;

    @InjectMocks
    private CardDeleter cardDeleter;


    @Test
    void deleteCardById(){

        when(cardRepository.deleteCardById(anyString()))
                .thenReturn(Mono.empty());

        Mono<String> result = cardDeleter.deleteCardById(CARD.getCardId());


        StepVerifier.create(result)
                .assertNext(response -> {
                    Assertions.assertInstanceOf(String.class, response);
                })
                .verifyComplete();


        verify(cardRepository, times(1)).deleteCardById(anyString());



    }

}
