package com.bankinc.bankinc.config;

import com.bankinc.bankinc.domain.model.card.gateway.CardRepository;
import com.bankinc.bankinc.domain.model.transaction.gateway.TransactionRepository;
import com.bankinc.bankinc.domain.usecase.transaction.TransactionManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TransactionConfig {

    @Bean
    public TransactionManager transactionManager(TransactionRepository repository,
                                                 CardRepository cardRepository){
        return new TransactionManager(repository, cardRepository);
    }


}
