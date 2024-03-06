package com.bankinc.bankinc.infrastructure.adapter.transactionadapter.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;


@Table("transaction")
@Builder(toBuilder = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionDTO {

    @Id
    private String transactionId;
    private Double price;
    private String cardId;
    private LocalDateTime transactionDate;
    private Boolean isInvalidated;

}
