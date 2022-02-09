package com.amazing.gaming.wallet.services;

import java.util.List;

import com.amazing.gaming.wallet.entities.Transaction;
import com.amazing.gaming.wallet.entities.Wallet;
import com.amazing.gaming.wallet.exceptions.TransactionException;


public interface TransactionService
{
	Wallet deposit(final Transaction transaction);
	Wallet withdraw(final Transaction transaction) throws TransactionException;
	Wallet registerBet(final Transaction transaction) throws TransactionException;
	Wallet registerWin(final Transaction transaction) throws TransactionException;
	List<Transaction> findAll(final Integer pageNumber, final Integer pageSize, final String sortBy);
	boolean validateTransactionId(final Long transactionId);
}
