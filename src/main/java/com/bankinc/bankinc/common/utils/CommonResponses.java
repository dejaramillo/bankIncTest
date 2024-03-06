package com.bankinc.bankinc.common.utils;

import com.bankinc.bankinc.common.dto.ResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Mono;

public class CommonResponses {

    private CommonResponses(){}

    private static final String WRONG_CARD_ID_MSG = "Wrong card id";

    public static Mono<ResponseEntity<ResponseDTO>> wrongCardIdResponse(){
        return Mono.just(ResponseEntity.badRequest().body(
                ResponseDTO.builder()
                        .status(HttpStatus.BAD_REQUEST)
                        .data(WRONG_CARD_ID_MSG)
                        .build()));
    }

}
