package com.bankinc.bankinc.domain.model.transaction.gateway;

import com.bankinc.bankinc.domain.model.transaction.Transaction;
import reactor.core.publisher.Mono;

public interface TransactionRepository {

    Mono<Transaction> getTransactionById(String id);

    Mono<Transaction> saveTransaction(Transaction transaction);

    Mono<Transaction> updateTransaction(Transaction transaction);

}
