package com.bankinc.bankinc.domain.usecase.transaction;

import com.bankinc.bankinc.domain.model.card.gateway.CardRepository;
import com.bankinc.bankinc.domain.model.transaction.SaveTransaction;
import com.bankinc.bankinc.domain.model.transaction.Transaction;
import com.bankinc.bankinc.domain.model.transaction.gateway.TransactionRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static com.bankinc.bankinc.common.FeaturesMocks.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TransactionManagerTest {

    @Mock
    private  TransactionRepository repository;

    @Mock
    private  CardRepository cardRepository;


    @InjectMocks
    private TransactionManager transactionManager;


    @Test
    void saveCardTransaction(){
        when(cardRepository.getCardById(anyString()))
                .thenReturn(Mono.just(CARD));

        when(cardRepository.updateCard(any()))
            .thenReturn((Mono.just(CARD)));

        when(repository.saveTransaction(any()))
                .thenReturn(Mono.just(TRANSACTION));

        Mono<SaveTransaction> result = transactionManager.saveCardTransaction(SAVE_TRANSACTION);

        StepVerifier.create(result)
                .assertNext(saveTransaction -> {
                    assertEquals(TRANSACTION.getTransactionId(), saveTransaction.getTransactionId());
                    assertEquals(TRANSACTION.getCardId(), saveTransaction.getCardId());
                }).verifyComplete();

        verify(cardRepository, times(1)).getCardById(anyString());
        verify(cardRepository, times(1)).updateCard(any());
        verify(repository, times(1)).saveTransaction(any());

    }

    @Test
    void getTransactionByIdTest(){

        when(repository.getTransactionById(anyString()))
                .thenReturn(Mono.just(TRANSACTION));


        Mono<Transaction> result = transactionManager.getTransactionById(TRANSACTION.getTransactionId());

        StepVerifier.create(result)
                .assertNext(transaction -> {

                    Assertions.assertInstanceOf(Transaction.class, transaction);
                    Assertions.assertEquals(TRANSACTION.getTransactionId(), transaction.getTransactionId());

                }).verifyComplete();

        verify(repository, times(1)).getTransactionById(anyString());

    }

    @Test
    void invalidatedTransactionTest(){

        when(repository.getTransactionById(anyString()))
                .thenReturn(Mono.just(TRANSACTION));

        when(cardRepository.getCardById(anyString()))
                .thenReturn(Mono.just(CARD));


        when(cardRepository.updateCard(any()))
                .thenReturn(Mono.just(CARD));

        when(repository.updateTransaction(any()))
                .thenReturn(Mono.just(TRANSACTION));

        Mono<String> result = transactionManager.invalidatedTransaction(INVALIDATED_TRANSACTION);

        StepVerifier.create(result)
                .assertNext(response -> {
                    Assertions.assertInstanceOf(String.class, response);
                    Assertions.assertEquals(INVALIDATED_TRANSACTION_MSG, response);
                }).verifyComplete();

        verify(repository, times(1)).getTransactionById(anyString());
        verify(cardRepository, times(1)).getCardById(anyString());
        verify(cardRepository, times(1)).updateCard(any());
        verify(repository, times(1)).updateTransaction(any());


    }



}
