package com.bankinc.bankinc.infrastructure.adapter.transactionadapter;

import com.bankinc.bankinc.common.mapper.CommonMapper;
import com.bankinc.bankinc.domain.model.transaction.Transaction;
import com.bankinc.bankinc.domain.model.transaction.gateway.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class TransactionAdapter implements TransactionRepository {

    private final TransactionRepositoryIntegration repository;

    @Autowired
    public TransactionAdapter(TransactionRepositoryIntegration repository) {
        this.repository = repository;
    }

    @Override
    public Mono<Transaction> getTransactionById(String id) {
        return repository.findById(id)
                .map(CommonMapper::mapTransactionToModel);
    }

    @Override
    public Mono<Transaction> saveTransaction(Transaction transaction) {
        return repository.insetTransaction(transaction.getTransactionId(),
                transaction.getPrice(), transaction.getCardId(), transaction.getTransactionDate())
                .then(Mono.fromCallable(() -> transaction));
    }

    @Override
    public Mono<Transaction> updateTransaction(Transaction transaction) {
        return repository.save(CommonMapper.mapTransactionToEntity(transaction))
                .map(CommonMapper::mapTransactionToModel);
    }
}
