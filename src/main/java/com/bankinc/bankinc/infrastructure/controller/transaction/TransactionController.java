package com.bankinc.bankinc.infrastructure.controller.transaction;

import com.bankinc.bankinc.common.dto.ResponseDTO;
import com.bankinc.bankinc.common.mapper.CommonMapper;
import com.bankinc.bankinc.common.utils.CommonResponses;
import com.bankinc.bankinc.common.utils.IdValidator;
import com.bankinc.bankinc.domain.usecase.transaction.TransactionManager;
import com.bankinc.bankinc.infrastructure.controller.transaction.dto.InvalidatedTransactionDTO;
import com.bankinc.bankinc.infrastructure.controller.transaction.dto.SaveTransactionDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/transaction/")
public class TransactionController {

    private final TransactionManager transactionManager;

    @Autowired
    public TransactionController(TransactionManager transactionManager) {
        this.transactionManager = transactionManager;
    }

    @GetMapping("{transactionId}")
    public Mono<ResponseEntity<ResponseDTO>> getTransaction(@PathVariable String transactionId) {
        return transactionManager.getTransactionById(transactionId)
                .map(transaction -> ResponseEntity.ok().body(
                        ResponseDTO.builder()
                                .status(HttpStatus.OK)
                                .data(transaction)
                                .build()
                ));
    }


    @PostMapping("purchase")
    public Mono<ResponseEntity<ResponseDTO>> saveTransaction(@RequestBody SaveTransactionDTO saveTransactionDTO){
        if (IdValidator.idSizeValidator(saveTransactionDTO.getCardId(), IdValidator.CARD_ID_SIZE)){
            return transactionManager.saveCardTransaction(CommonMapper.mapSaveTransactionToModel(saveTransactionDTO))
                    .map(response -> ResponseEntity.status(HttpStatus.CREATED).body(
                            ResponseDTO.builder()
                                    .status(HttpStatus.CREATED)
                                    .data(response)
                                    .build()
                    ));
        }

        return CommonResponses.wrongCardIdResponse();

    }

    @PostMapping("anulation")
    public Mono<ResponseEntity<ResponseDTO>> invalidatedTransaction(@RequestBody InvalidatedTransactionDTO
                                                                                invalidatedTransactionDTO){
        if (IdValidator.idSizeValidator(invalidatedTransactionDTO.getCardId(),
                IdValidator.CARD_ID_SIZE)){
            return transactionManager.invalidatedTransaction(
                    CommonMapper.mapInvalidTransactionToModel(invalidatedTransactionDTO)
            ).map(response -> ResponseEntity.ok().body(
                    ResponseDTO.builder()
                            .status(HttpStatus.OK)
                            .data(response)
                            .build()
            ));
        }

        return CommonResponses.wrongCardIdResponse();
    }




}
