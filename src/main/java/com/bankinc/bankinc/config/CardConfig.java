package com.bankinc.bankinc.config;

import com.bankinc.bankinc.domain.model.card.gateway.CardRepository;
import com.bankinc.bankinc.domain.usecase.card.BalanceAdministrator;
import com.bankinc.bankinc.domain.usecase.card.CardActivator;
import com.bankinc.bankinc.domain.usecase.card.CardDeleter;
import com.bankinc.bankinc.domain.usecase.card.CardNumberGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CardConfig {

    @Bean
    public CardNumberGenerator cardNumberGenerator(CardRepository cardRepository){
        return new CardNumberGenerator(cardRepository);
    }

    @Bean
    public CardActivator cardActivator(CardRepository cardRepository){
        return new CardActivator(cardRepository);
    }


    @Bean
    public CardDeleter cardDeleter(CardRepository cardRepository){
        return new CardDeleter(cardRepository);
    }

    @Bean
    public BalanceAdministrator balanceRecharger(CardRepository cardRepository){
        return new BalanceAdministrator(cardRepository);
    }

}
