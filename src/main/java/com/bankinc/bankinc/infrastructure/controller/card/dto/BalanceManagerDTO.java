package com.bankinc.bankinc.infrastructure.controller.card.dto;

import lombok.Setter;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Setter
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BalanceManagerDTO {

    private String cardId;
    private Double balance;

}
