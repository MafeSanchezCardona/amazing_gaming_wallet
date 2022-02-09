package com.amazing.gaming.wallet.services;

import java.util.Optional;

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
import com.amazing.gaming.wallet.repositories.WalletRepository;
import com.amazing.gaming.wallet.services.impl.WalletServiceImpl;


@SpringBootTest
public class WalletServiceMockTest
{
	@Mock
	private WalletRepository walletRepository;

	private WalletService walletService;

	@BeforeEach
	public void begin()
	{
		MockitoAnnotations.initMocks(this);
		walletService = new WalletServiceImpl(walletRepository);

		Player playerMock = Player.builder().id(1L).name("Maria").lastName("Sanchez").build();

		Mockito.when(walletRepository.findByPlayer(1L))
				.thenReturn(Wallet.builder().id(1L).player(playerMock)
						.cashBalance(100D).bonusBalance(100D).build());
	}

	@Test
	public void when_getBalance_return_balance()
	{
		Wallet wallet = walletService.getBalance(1L);
		Assertions.assertThat(wallet.getCashBalance()).isEqualTo(100D);
	}

	@Test
	public void when_updateByDeposit_return_wallet()
	{
		Player playerMock = Player.builder().id(1L).name("Maria").lastName("Sanchez").build();
		Transaction transaction = Transaction.builder().player(playerMock).type(TransactionType.DEPOSIT.getId())
				.amount(34D).build();

		Wallet wallet = Wallet.builder().id(1L).player(playerMock)
				.cashBalance(134D).bonusBalance(100D).build();

		Mockito.when(walletRepository.save(wallet)).thenReturn(wallet);

		walletService.updateByDeposit(transaction);
		Assertions.assertThat(wallet.getCashBalance()).isEqualTo(134D);
		Assertions.assertThat(wallet.getBonusBalance()).isEqualTo(100D);
	}
}
