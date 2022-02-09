package com.amazing.gaming.wallet.services;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.amazing.gaming.wallet.entities.Player;
import com.amazing.gaming.wallet.entities.Transaction;
import com.amazing.gaming.wallet.entities.Wallet;
import com.amazing.gaming.wallet.enums.TransactionType;
import com.amazing.gaming.wallet.exceptions.TransactionException;
import com.amazing.gaming.wallet.repositories.TransactionRepository;
import com.amazing.gaming.wallet.repositories.WalletRepository;
import com.amazing.gaming.wallet.services.impl.TransactionServiceImpl;
import com.amazing.gaming.wallet.services.impl.WalletServiceImpl;


@SpringBootTest
public class TransactionMockTest
{
	@Mock
	private TransactionRepository transactionRepository;

	@Mock
	private WalletRepository walletRepository;

	private WalletService walletService;
	private TransactionService transactionService;

	@BeforeEach
	public void begin()
	{
		MockitoAnnotations.initMocks(this);
		walletService = new WalletServiceImpl(walletRepository);
		transactionService = new TransactionServiceImpl(transactionRepository, walletService);
	}

	@Test
	public void when_deposit_return_wallet()
	{
		Player playerMock = Player.builder().id(1L).name("Maria").lastName("Sanchez").build();
		Transaction transaction = Transaction.builder().player(playerMock).amount(34D).type(TransactionType.DEPOSIT.getId()).build();

		Wallet wallet = Wallet.builder().id(1L).player(playerMock)
				.cashBalance(134D).bonusBalance(100D).build();

		Mockito.when(walletService.updateByDeposit(transaction)).thenReturn(wallet);

		transactionService.deposit(transaction);
		Assertions.assertThat(wallet.getCashBalance()).isEqualTo(134D);
		Assertions.assertThat(wallet.getBonusBalance()).isEqualTo(100D);
	}

	@Test
	public void when_withdraw_return_wallet() throws TransactionException
	{
		Player playerMock = Player.builder().id(1L).name("Maria").lastName("Sanchez").build();
		Transaction transaction = Transaction.builder().player(playerMock).amount(100D)
				.type(TransactionType.WITHDRAW.getId()).build();

		Wallet wallet = Wallet.builder().id(1L).player(playerMock)
				.cashBalance(134D).bonusBalance(100D).build();

		Mockito.when(walletService.getBalance(playerMock.getId())).thenReturn(wallet);

		Mockito.when(walletService.save(wallet)).thenReturn(Wallet.builder().id(1L).player(playerMock)
				.cashBalance(34D).bonusBalance(100D).build());

		Wallet walletReturn = transactionService.withdraw(transaction);
		Assertions.assertThat(walletReturn.getCashBalance()).isEqualTo(34D);
		Assertions.assertThat(walletReturn.getBonusBalance()).isEqualTo(100D);
	}

	@Test
	public void when_registerBet_return_wallet() throws TransactionException
	{
		Player playerMock = Player.builder().id(1L).name("Maria").lastName("Sanchez").build();
		Transaction transaction = Transaction.builder().player(playerMock).amount(200D)
				.type(TransactionType.BET.getId()).build();

		Wallet wallet = Wallet.builder().id(1L).player(playerMock)
				.cashBalance(134D).bonusBalance(100D).build();

		Mockito.when(walletService.getBalance(playerMock.getId())).thenReturn(wallet);

		Mockito.when(walletService.save(wallet)).thenReturn(Wallet.builder().id(1L).player(playerMock)
				.cashBalance(0D).bonusBalance(44D).build());

		Wallet walletReturn = transactionService.registerBet(transaction);
		Assertions.assertThat(walletReturn.getCashBalance()).isEqualTo(0D);
		Assertions.assertThat(walletReturn.getBonusBalance()).isEqualTo(44D);
	}

	@Test
	public void when_registerWin_return_wallet() throws TransactionException
	{
		Player playerMock = Player.builder().id(1L).name("Maria").lastName("Sanchez").build();
		Transaction transaction = Transaction.builder().player(playerMock).amount(300D)
				.type(TransactionType.WIN.getId()).build();

		Transaction transactionLast = Transaction.builder().player(playerMock).amount(200D)
				.type(TransactionType.BET.getId()).proportionalBet(0.67).build();

		Wallet wallet = Wallet.builder().id(1L).player(playerMock)
				.cashBalance(134D).bonusBalance(100D).build();

		Mockito.when(walletService.getBalance(playerMock.getId())).thenReturn(wallet);
		Mockito.when(transactionRepository.getByTypeOrderByIdAsc(TransactionType.BET.getId())).thenReturn(transactionLast);

		Mockito.when(walletService.save(wallet)).thenReturn(Wallet.builder().id(1L).player(playerMock)
				.cashBalance(201D).bonusBalance(143D).build());

		Wallet walletReturn = transactionService.registerWin(transaction);
		Assertions.assertThat(walletReturn.getCashBalance()).isEqualTo(201D);
		Assertions.assertThat(walletReturn.getBonusBalance()).isEqualTo(143D);
	}
}
