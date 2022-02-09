package com.amazing.gaming.wallet.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.amazing.gaming.wallet.entities.Transaction;
import com.amazing.gaming.wallet.entities.Wallet;
import com.amazing.gaming.wallet.enums.TransactionType;
import com.amazing.gaming.wallet.exceptions.TransactionException;
import com.amazing.gaming.wallet.repositories.TransactionRepository;
import com.amazing.gaming.wallet.services.TransactionService;
import com.amazing.gaming.wallet.services.WalletService;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService
{
	private static final String WITHDRAW_ERROR = "It does not have that value to withdraw";
	private static final String BET_ERROR = "You have no money available for the bet";
	private static final String WIN_ERROR = "The previous bet is not registered to be able to register the win";

	private final TransactionRepository transactionRepository;
	private final WalletService walletService;

	@Override
	@Transactional(rollbackFor = Exception.class)
	public Wallet deposit(final Transaction transaction)
	{
		transaction.setType(TransactionType.DEPOSIT.getId());
		transactionRepository.save(transaction);
		return walletService.updateByDeposit(transaction);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public Wallet withdraw(final Transaction transaction) throws TransactionException
	{
		Wallet wallet = walletService.getBalance(transaction.getPlayer().getId());

		if (Objects.nonNull(wallet) && Objects.nonNull(wallet.getCashBalance()) && wallet.getCashBalance() >= transaction
				.getAmount())
		{
			transaction.setType(TransactionType.WITHDRAW.getId());
			transactionRepository.save(transaction);

			wallet.setCashBalance(wallet.getCashBalance() - transaction.getAmount());
			return walletService.save(wallet);
		}
		else
		{
			throw new TransactionException(WITHDRAW_ERROR);
		}
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public Wallet registerBet(final Transaction transaction) throws TransactionException
	{
		Wallet wallet = walletService.getBalance(transaction.getPlayer().getId());
		if (Objects.nonNull(wallet))
		{
			Double moneyTotal = wallet.getBonusBalance() + wallet.getCashBalance();

			if (moneyTotal >= transaction.getAmount())
			{
				transaction.setType(TransactionType.BET.getId());

				Double transactionValue = transaction.getAmount() - wallet.getCashBalance();

				if (transactionValue > NumberUtils.DOUBLE_ZERO)
				{
					transaction.setProportionalBet(wallet.getCashBalance() / transaction.getAmount());

					wallet.setCashBalance(NumberUtils.DOUBLE_ZERO);
					wallet.setBonusBalance(wallet.getBonusBalance() - transactionValue);
				}
				else
				{
					transaction.setProportionalBet(NumberUtils.DOUBLE_ONE);
					wallet.setCashBalance(wallet.getCashBalance() - transaction.getAmount());
				}
				transactionRepository.save(transaction);
				return walletService.save(wallet);
			}
			else
			{
				throw new TransactionException(BET_ERROR);
			}
		}
		else
		{
			throw new TransactionException(BET_ERROR);
		}
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public Wallet registerWin(final Transaction transaction) throws TransactionException
	{
		Wallet wallet = walletService.getBalance(transaction.getPlayer().getId());

		Transaction lastBetTransaction = transactionRepository.getFirstByTypeOrderByIdAsc(TransactionType.BET.getId());

		if (Objects.nonNull(lastBetTransaction) && Objects.nonNull(wallet))
		{
			transaction.setType(TransactionType.WIN.getId());
			transactionRepository.save(transaction);

			Double cashWin = lastBetTransaction.getProportionalBet() * transaction.getAmount();
			wallet.setCashBalance(wallet.getCashBalance() + cashWin);
			wallet.setBonusBalance(wallet.getBonusBalance() + (transaction.getAmount() - cashWin));

			return walletService.save(wallet);
		}
		else
		{
			throw new TransactionException(WIN_ERROR);
		}
	}

	@Override
	@Transactional(readOnly = true)
	public List<Transaction> findAll(final Integer pageNumber, final Integer pageSize, final String sortBy)
	{
		Page<Transaction> pagedResult = transactionRepository.findAll(PageRequest.of(pageNumber, pageSize, Sort.by(sortBy)));

		return pagedResult.hasContent() ? pagedResult.getContent() : new ArrayList<>();
	}

	@Override
	public boolean validateTransactionId(final Long transactionId)
	{
		return Objects.isNull(transactionRepository.getByTransactionId(transactionId));
	}
}
