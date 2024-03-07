package com.bankinc.bankinc.infrastructure.controller.card;

import com.bankinc.bankinc.common.mapper.CommonMapper;
import com.bankinc.bankinc.common.utils.CommonResponses;
import com.bankinc.bankinc.common.utils.IdValidator;
import com.bankinc.bankinc.domain.usecase.card.BalanceAdministrator;
import com.bankinc.bankinc.domain.usecase.card.CardActivator;
import com.bankinc.bankinc.domain.usecase.card.CardDeleter;
import com.bankinc.bankinc.domain.usecase.card.CardNumberGenerator;
import com.bankinc.bankinc.infrastructure.controller.card.dto.ActivateCardDTO;
import com.bankinc.bankinc.infrastructure.controller.card.dto.BalanceManagerDTO;
import com.bankinc.bankinc.common.dto.ResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/card/")
public class CardController {


    private final CardNumberGenerator cardNumberGenerator;
    private final CardActivator cardActivator;
    private final CardDeleter cardDeleter;
    private final BalanceAdministrator balanceAdministrator;

    @Autowired
    public CardController(CardNumberGenerator cardNumberGenerator, CardActivator cardActivator, CardDeleter cardDeleter, BalanceAdministrator balanceRecharger) {
        this.cardNumberGenerator = cardNumberGenerator;
        this.cardActivator = cardActivator;
        this.cardDeleter = cardDeleter;
        this.balanceAdministrator = balanceRecharger;
    }


    @GetMapping("{productId}/number")
    public Mono<ResponseEntity<ResponseDTO>> generateCardNumber(@PathVariable String productId){
        if (IdValidator.idSizeValidator(productId, IdValidator.PRODUCT_ID_SIZE)){
            return cardNumberGenerator.numberGenerator(productId)
                    .map(cardId -> ResponseEntity.ok().body(
                            ResponseDTO.builder()
                                    .status(HttpStatus.OK)
                                    .data(cardId)
                                    .build()
                    ));
        }
        return CommonResponses.wrongCardIdResponse();

    }

    @GetMapping("balance/{cardId}")
    public Mono<ResponseEntity<ResponseDTO>> checkBalance(@PathVariable String cardId){
        if (IdValidator.idSizeValidator(cardId, IdValidator.CARD_ID_SIZE)){
            return balanceAdministrator.checkBalance(cardId)
                    .map(balanceManager -> ResponseEntity.ok().body(
                            ResponseDTO.builder()
                                    .status(HttpStatus.OK)
                                    .data(CommonMapper.mapRechargeToDTO(balanceManager))
                                    .build()
                    ));
        }

        return CommonResponses.wrongCardIdResponse();
    }

    @PostMapping("enroll")
    public Mono<ResponseEntity<ResponseDTO>> activateCard(@RequestBody ActivateCardDTO activateCardDTO){
        if (IdValidator.idSizeValidator(activateCardDTO.getCardId(), IdValidator.CARD_ID_SIZE)){
            return cardActivator.saveCard(activateCardDTO.getCardId())
                    .map(response -> ResponseEntity.status(HttpStatus.CREATED).body(
                            ResponseDTO.builder()
                                    .status(HttpStatus.CREATED)
                                    .data(response)
                                    .build()
                    ));
        }

        return CommonResponses.wrongCardIdResponse();
    }

    @PostMapping("balance")
    public Mono<ResponseEntity<ResponseDTO>> rechargeBalance(@RequestBody BalanceManagerDTO rechargeBalanceDTO){
        if (IdValidator.idSizeValidator(rechargeBalanceDTO.getCardId(), IdValidator.CARD_ID_SIZE)){
            return balanceAdministrator.rechargeCardBalance(CommonMapper
                            .mapRechargeToModel(rechargeBalanceDTO))
                    .map(rechargeBalance -> ResponseEntity.ok().body(
                            ResponseDTO.builder()
                                    .status(HttpStatus.OK)
                                    .data(rechargeBalance)
                                    .build()
                    ));

        }

        return CommonResponses.wrongCardIdResponse();
    }

    @DeleteMapping("{cardId}")
    public Mono<ResponseEntity<ResponseDTO>> deleteCard(@PathVariable String cardId){

        if (IdValidator.idSizeValidator(cardId, IdValidator.CARD_ID_SIZE)){
            return cardDeleter.deleteCardById(cardId)
                    .map(deleteMessage -> ResponseEntity.ok().body(
                            ResponseDTO.builder()
                                    .status(HttpStatus.OK)
                                    .data(deleteMessage)
                                    .build()
                    ));
        }

        return CommonResponses.wrongCardIdResponse();

    }

}
