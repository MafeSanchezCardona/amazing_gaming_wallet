package com.amazing.gaming.wallet.services.impl;

import java.util.Objects;

import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.amazing.gaming.wallet.entities.Player;
import com.amazing.gaming.wallet.entities.Transaction;
import com.amazing.gaming.wallet.entities.Wallet;
import com.amazing.gaming.wallet.repositories.WalletRepository;
import com.amazing.gaming.wallet.services.PlayerService;
import com.amazing.gaming.wallet.services.WalletService;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class WalletServiceImpl implements WalletService
{
	private static final Double BONUS_VALUE = 100D;

	private final WalletRepository walletRepository;
	private final PlayerService playerService;

	@Override
	@Transactional(readOnly = true)
	public Wallet getBalance(final Long playerId)
	{
		final Player player = playerService.findById(playerId);
		return walletRepository.findByPlayer(player);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public Wallet save(final Wallet wallet)
	{
		return walletRepository.save(wallet);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public Wallet updateByDeposit(final Transaction transaction)
	{
		Wallet wallet = getBalance(transaction.getPlayer().getId());

		if (Objects.isNull(wallet))
		{
			wallet = new Wallet();
			wallet.setCashBalance(transaction.getAmount());
		}
		else
		{
			wallet.setCashBalance((Objects.nonNull(wallet.getCashBalance()) ? wallet.getCashBalance() :
					NumberUtils.DOUBLE_ZERO) + transaction.getAmount());
		}

		if (transaction.getAmount() >= BONUS_VALUE)
		{
			wallet.setBonusBalance((Objects.nonNull(wallet.getBonusBalance()) ? wallet.getBonusBalance() :
					NumberUtils.DOUBLE_ZERO) + transaction.getAmount());
		}
		wallet.setPlayer(transaction.getPlayer());
		return save(wallet);
	}
}
