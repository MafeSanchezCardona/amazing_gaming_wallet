package com.amazing.gaming.wallet.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.amazing.gaming.wallet.entities.Transaction;


@Repository
public interface TransactionRepository extends PagingAndSortingRepository<Transaction,Long>
{
	Transaction getFirstByTypeOrderByIdAsc(final Integer type);

	Transaction getByTransactionId(final Long transactionId);
}
