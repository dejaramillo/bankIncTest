package com.bankinc.bankinc.common.mapper;


import com.bankinc.bankinc.domain.model.card.BalanceManager;
import com.bankinc.bankinc.domain.model.transaction.InvalidatedTransaction;
import com.bankinc.bankinc.domain.model.transaction.SaveTransaction;
import com.bankinc.bankinc.domain.model.transaction.Transaction;
import com.bankinc.bankinc.infrastructure.adapter.transactionadapter.model.TransactionDTO;
import com.bankinc.bankinc.infrastructure.controller.card.dto.BalanceManagerDTO;
import com.bankinc.bankinc.infrastructure.controller.transaction.dto.InvalidatedTransactionDTO;
import com.bankinc.bankinc.infrastructure.controller.transaction.dto.SaveTransactionDTO;

public  class CommonMapper {

    private CommonMapper(){}
    public static BalanceManager mapRechargeToModel(BalanceManagerDTO balanceManagerDTO){
        return BalanceManager.builder()
                .cardId(balanceManagerDTO.getCardId())
                .balance(balanceManagerDTO.getBalance())
                .build();
    }

    public static BalanceManagerDTO mapRechargeToDTO(BalanceManager balanceManager){
        return BalanceManagerDTO.builder()
                .cardId(balanceManager.getCardId())
                .balance(balanceManager.getBalance())
                .build();
    }

    public static TransactionDTO mapTransactionToEntity(Transaction transaction){
        return TransactionDTO.builder()
                .transactionId(transaction.getTransactionId())
                .cardId(transaction.getCardId())
                .price(transaction.getPrice())
                .isInvalidated(transaction.getIsInvalidated())
                .transactionDate(transaction.getTransactionDate())
                .build();
    }


    public static Transaction mapTransactionToModel(TransactionDTO transactionDTO){
        return Transaction.builder()
                .transactionId(transactionDTO.getTransactionId())
                .cardId(transactionDTO.getCardId())
                .price(transactionDTO.getPrice())
                .isInvalidated(transactionDTO.getIsInvalidated())
                .transactionDate(transactionDTO.getTransactionDate())
                .build();
    }

    public static SaveTransaction mapSaveTransactionToModel(SaveTransactionDTO saveTransactionDTO){
        return SaveTransaction.builder()
                .cardId(saveTransactionDTO.getCardId())
                .transactionId(saveTransactionDTO.getTransactionId())
                .price(saveTransactionDTO.getPrice())
                .build();
    }

    public static SaveTransactionDTO mapSaveTransactionToDTO(SaveTransaction saveTransaction){
        return SaveTransactionDTO.builder()
                .cardId(saveTransaction.getCardId())
                .transactionId(saveTransaction.getTransactionId())
                .price(saveTransaction.getPrice())
                .build();
    }

    public static InvalidatedTransaction mapInvalidTransactionToModel(InvalidatedTransactionDTO
                                                                              invalidatedTransactionDTO){
        return InvalidatedTransaction.builder()
                .cardId(invalidatedTransactionDTO.getCardId())
                .transactionId(invalidatedTransactionDTO.getTransactionId())
                .build();
    }

}
