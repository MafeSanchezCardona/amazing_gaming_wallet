package com.amazing.gaming.wallet.services;

import com.amazing.gaming.wallet.entities.Transaction;
import com.amazing.gaming.wallet.entities.Wallet;


public interface WalletService
{
	Wallet getBalance(final Long playerId);

	Wallet save(final Wallet wallet);

	Wallet updateByDeposit(final Transaction transaction);
}
