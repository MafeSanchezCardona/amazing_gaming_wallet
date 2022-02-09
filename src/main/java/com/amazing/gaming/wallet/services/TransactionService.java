package com.amazing.gaming.wallet.services;

import java.util.List;

import com.amazing.gaming.wallet.entities.Transaction;
import com.amazing.gaming.wallet.entities.Wallet;
import com.amazing.gaming.wallet.exceptions.TransactionException;


/**
 * Transaction Service
 */
public interface TransactionService
{
	/**
	 * Method to save the transaction type DEPOSIT
	 * @param transaction
	 * @return Wallet
	 */
	Wallet deposit(final Transaction transaction);

	/**
	 * Method to save the transaction type WITHDRAW
	 * @param transaction
	 * @return Wallet
	 * @throws TransactionException
	 */
	Wallet withdraw(final Transaction transaction) throws TransactionException;

	/**
	 * Method to save the transaction type BET
	 * @param transaction
	 * @return Wallet
	 * @throws TransactionException
	 */
	Wallet registerBet(final Transaction transaction) throws TransactionException;

	/**
	 * Method to save the transaction type WIN
	 * @param transaction
	 * @return Wallet
	 * @throws TransactionException
	 */
	Wallet registerWin(final Transaction transaction) throws TransactionException;

	/**
	 * Method to list of transactions
	 * @param pageNumber
	 * @param pageSize
	 * @param sortBy
	 * @return List<Transaction>
	 */
	List<Transaction> findAll(final Integer pageNumber, final Integer pageSize, final String sortBy);

	/**
	 * Method to validate if the transactionId already exists
	 * @param transactionId
	 * @return Wallet
	 */
	boolean validateTransactionId(final Long transactionId);
}
