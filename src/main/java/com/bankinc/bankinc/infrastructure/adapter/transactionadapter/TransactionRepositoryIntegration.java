package com.bankinc.bankinc.infrastructure.adapter.transactionadapter;

import com.bankinc.bankinc.infrastructure.adapter.transactionadapter.model.TransactionDTO;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

public interface TransactionRepositoryIntegration extends ReactiveCrudRepository<TransactionDTO, String> {
    @Query("INSERT INTO bankinc.`transaction` " +
            "(transaction_id, price, card_id, transaction_date) " +
            "VALUES(:transactionId, :price, :cardId, :transactionDate);")
    Mono<Void> insetTransaction(String transactionId, Double price, String cardId,
                                LocalDateTime transactionDate);

}
