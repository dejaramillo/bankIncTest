package com.bankinc.bankinc.infrastructure.adapter.cardadapter;

import com.bankinc.bankinc.infrastructure.adapter.cardadapter.model.CardDTO;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

import java.time.LocalDate;


public interface CardRepositoryIntegration extends ReactiveCrudRepository<CardDTO, String> {
    @Query("INSERT INTO bankinc.card " +
            "(card_id, product_id, customer_name, expiration_date, currency, is_active) " +
            "VALUES(:id, :cardNumber, :customerName, :expirationDate, :currency, :isActive);")
    Mono<Void> insertCard(String id, String cardNumber, String customerName, LocalDate expirationDate,
                          double currency, boolean isActive);
}
