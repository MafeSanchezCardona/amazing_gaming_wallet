package com.amazing.gaming.wallet.controller;

import java.util.Objects;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.amazing.gaming.wallet.entities.Wallet;
import com.amazing.gaming.wallet.populators.WalletPopulator;
import com.amazing.gaming.wallet.services.WalletService;

import lombok.RequiredArgsConstructor;


@RestController
@RequiredArgsConstructor
public class WalletController
{
	private final WalletService walletService;

	private final WalletPopulator walletPopulator = new WalletPopulator();

	@GetMapping("/balance/{playerId}")
	public ResponseEntity<Object> getBalance(@PathVariable("playerId") Long playerId)
	{
		Wallet wallet = walletService.getBalance(playerId);

		if (Objects.isNull(wallet))
		{
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}

		return new ResponseEntity<>(walletPopulator.convertToDTO(wallet), HttpStatus.OK);
	}
}