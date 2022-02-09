package com.amazing.gaming.wallet.services;

import com.amazing.gaming.wallet.entities.Transaction;
import com.amazing.gaming.wallet.entities.Wallet;


/**
 * Wallet Service
 */
public interface WalletService
{
	/**
	 * Method to get balance of the specific player
	 * @param playerId
	 * @return Wallet
	 */
	Wallet getBalance(final Long playerId);

	/**
	 * Method to save the wallet entity
	 * @param wallet
	 * @return Wallet
	 */
	Wallet save(final Wallet wallet);

	/**
	 * Method to save or update the wallet of the player
	 * @param transaction
	 * @return Wallet
	 */
	Wallet updateByDeposit(final Transaction transaction);
}
