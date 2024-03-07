package com.bankinc.bankinc.infrastructure.controller.card;

import com.bankinc.bankinc.domain.usecase.card.BalanceAdministrator;
import com.bankinc.bankinc.domain.usecase.card.CardActivator;
import com.bankinc.bankinc.domain.usecase.card.CardDeleter;
import com.bankinc.bankinc.domain.usecase.card.CardNumberGenerator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static com.bankinc.bankinc.common.FeaturesMocks.BALANCE_MANAGER;
import static com.bankinc.bankinc.common.FeaturesMocks.CARD;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CardControllerTest {

    @Mock
    private CardNumberGenerator cardNumberGenerator;

    @Mock
    private CardActivator cardActivator;

    @Mock
    private CardDeleter cardDeleter;

    @Mock
    private BalanceAdministrator balanceAdministrator;

    @InjectMocks
    private CardController cardController;


    @Test
    void generateCardNumberTest(){

        String productId = CARD.getCardId().substring(0, 6);

        when(cardNumberGenerator.numberGenerator(anyString()))
                .thenReturn(Mono.just(CARD.getCardId()));

        StepVerifier.create(cardController.generateCardNumber(productId))
                .assertNext(response -> {
                    Assertions.assertTrue(response.getStatusCode().is2xxSuccessful());
                }).verifyComplete();


    }

    @Test
    void checkBalanceTest(){

        when(balanceAdministrator.checkBalance(anyString()))
                .thenReturn(Mono.just(BALANCE_MANAGER));

        StepVerifier.create(cardController.checkBalance(CARD.getCardId()))
                .assertNext(balanceAdministrator -> {
                    Assertions.assertTrue(balanceAdministrator.getStatusCode().is2xxSuccessful());
                }).verifyComplete();

    }

}
