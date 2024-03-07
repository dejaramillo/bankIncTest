package com.bankinc.bankinc.domain.usecase.card;

import com.bankinc.bankinc.common.FeaturesMocks;
import com.bankinc.bankinc.domain.model.card.BalanceManager;
import com.bankinc.bankinc.domain.model.card.Card;
import com.bankinc.bankinc.domain.model.card.gateway.CardRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.time.LocalDate;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BalanceAdministratorTest {

    @Mock
    private CardRepository cardRepository;

    @InjectMocks
    private BalanceAdministrator balanceAdministrator;


    @Test
    void rechargeCardBalance(){

        when(cardRepository.getCardById(anyString()))
                .thenReturn(Mono.just(FeaturesMocks.CARD));


        when(cardRepository.updateCard(any()))
                .thenReturn(Mono.just(FeaturesMocks.CARD));


        Mono<BalanceManager> result = balanceAdministrator.rechargeCardBalance(FeaturesMocks.BALANCE_MANAGER);

        StepVerifier.create(result)
                .assertNext(balanceManager -> {
                    Assertions.assertEquals(balanceManager.getCardId(), FeaturesMocks.CARD.getCardId());

                }).verifyComplete();

        verify(cardRepository, times(1)).getCardById(anyString());
        verify(cardRepository, times(1)).updateCard(any());

    }

    @Test
    void checkBalanceTest(){

        when(cardRepository.getCardById(anyString()))
                .thenReturn(Mono.just(FeaturesMocks.CARD));

        Mono<BalanceManager> result = balanceAdministrator.checkBalance(FeaturesMocks.CARD.getCardId());

        StepVerifier.create(result)
                .assertNext(balanceManager -> {
                    Assertions.assertInstanceOf(BalanceManager.class, balanceManager);
                    Assertions.assertEquals(balanceManager.getCardId(), FeaturesMocks.CARD.getCardId());
                }).verifyComplete();

        verify(cardRepository, times(1)).getCardById(anyString());

    }

}
