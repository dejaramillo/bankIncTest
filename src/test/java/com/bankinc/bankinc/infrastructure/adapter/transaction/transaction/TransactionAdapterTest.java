package com.bankinc.bankinc.infrastructure.adapter.transaction.transaction;

import com.bankinc.bankinc.domain.model.transaction.Transaction;
import com.bankinc.bankinc.infrastructure.adapter.transactionadapter.TransactionAdapter;
import com.bankinc.bankinc.infrastructure.adapter.transactionadapter.TransactionRepositoryIntegration;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static com.bankinc.bankinc.common.FeaturesMocks.TRANSACTION;
import static com.bankinc.bankinc.common.FeaturesMocks.TRANSACTION_DTO;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TransactionAdapterTest {

    @Mock
    private TransactionRepositoryIntegration repositoryIntegration;


    @InjectMocks
    private TransactionAdapter transactionAdapter;


    @Test
    void getTransactionByIdTest(){

        when(repositoryIntegration.findById(anyString()))
                .thenReturn(Mono.just(TRANSACTION_DTO));

        Mono<Transaction> result = transactionAdapter.getTransactionById(TRANSACTION.getTransactionId());


        StepVerifier.create(result)
                .assertNext(transaction -> {
                    Assertions.assertEquals(TRANSACTION_DTO.getTransactionId(),
                            transaction.getTransactionId());

                }).verifyComplete();

    }

    @Test
    void saveTransactionTest(){

        when(repositoryIntegration.insetTransaction(anyString(), anyDouble(), anyString(), any()))
                .thenReturn(Mono.empty());

        Mono<Transaction> result = transactionAdapter.saveTransaction(TRANSACTION);

        StepVerifier.create(result)
                .assertNext(transaction -> {
                    Assertions.assertEquals(TRANSACTION.getTransactionId(), transaction.getTransactionId());
                }).verifyComplete();
    }

    @Test
    void updateTransactionTest(){

        when(repositoryIntegration.save(any()))
                .thenReturn(Mono.just(TRANSACTION_DTO));

        Mono<Transaction> result = transactionAdapter.updateTransaction(TRANSACTION);

        StepVerifier.create(result)
                .assertNext(transaction -> {

                    Assertions.assertEquals(TRANSACTION.getTransactionId(), transaction.getTransactionId());

                }).verifyComplete();

    }





}
