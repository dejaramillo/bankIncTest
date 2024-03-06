package com.bankinc.bankinc.infrastructure.adapter.cardadapter.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDate;

@Table("card")
@Builder(toBuilder = true)
@Data
public class CardDTO {

    @Id
    private String cardId;
    private String productId;
    private String customerName;
    private LocalDate expirationDate;
    private Double currency;
    private Boolean isActive;

}
