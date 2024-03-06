package com.bankinc.bankinc.domain.usecase.transaction;

import com.bankinc.bankinc.domain.model.card.gateway.CardRepository;
import com.bankinc.bankinc.domain.model.transaction.InvalidatedTransaction;
import com.bankinc.bankinc.domain.model.transaction.SaveTransaction;
import com.bankinc.bankinc.domain.model.transaction.Transaction;
import com.bankinc.bankinc.domain.model.transaction.gateway.TransactionRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.Random;

@RequiredArgsConstructor
public class TransactionManager {

    private final TransactionRepository repository;

    private final CardRepository cardRepository;


    public Mono<SaveTransaction> saveCardTransaction(SaveTransaction saveTransaction){

        return confirmTransaction(saveTransaction)
                .flatMap(saveTransaction1 -> repository
                        .saveTransaction(buildTransaction(saveTransaction)))
                .map(transaction -> saveTransaction.toBuilder()
                        .transactionId(transaction.getTransactionId())
                        .build());

    }

    public Mono<Transaction> getTransactionById(String transactionId){
        return repository.getTransactionById(transactionId);
    }

    public Mono<String> invalidatedTransaction(InvalidatedTransaction invalidatedTransaction){
        LocalDateTime today = LocalDateTime.now();
        return repository.getTransactionById(invalidatedTransaction.getTransactionId())
                .filter(transaction -> transaction.getTransactionDate()
                        .plusHours(24).isAfter(today) || transaction.getTransactionDate().plusHours(24)
                        .equals(today))
                .flatMap(this::payBackMoneyCard)
                .map(transaction -> transaction.toBuilder()
                        .isInvalidated(Boolean.TRUE)
                        .build())
                .flatMap(repository::updateTransaction)
                .map(transaction -> "Transaction was invalidated");

    }

    private Mono<Transaction> payBackMoneyCard(Transaction transaction){
        return cardRepository.getCardById(transaction.getCardId())
                .map(card -> card.toBuilder()
                        .currency(card.getCurrency() + transaction.getPrice())
                        .build())
                .flatMap(cardRepository::updateCard)
                .map(card -> transaction);
    }

    private Mono<SaveTransaction> confirmTransaction(SaveTransaction saveTransaction){
        return cardRepository.getCardById(saveTransaction.getCardId())
                .filter(card -> card.getCurrency() > saveTransaction.getPrice())
                .flatMap(card -> cardRepository.updateCard(
                        card.toBuilder()
                                .currency(card.getCurrency() - saveTransaction.getPrice())
                                .build()
                ))
                .map(card -> saveTransaction)
                .switchIfEmpty(Mono.error(new RuntimeException("Transaction failed, insufficient balance")));
    }

    private Transaction buildTransaction(SaveTransaction saveTransaction){
        return Transaction.builder()
                .cardId(saveTransaction.getCardId())
                .transactionId(buildTransactionId())
                .transactionDate(LocalDateTime.now())
                .price(saveTransaction.getPrice())
                .build();
    }

    private String buildTransactionId(){
        Random random = new Random();
        Long randomTransactionNumber = 1000000000 + ((long) (random.nextDouble() * 9000000000L));
        return String.valueOf(randomTransactionNumber);
    }

}
