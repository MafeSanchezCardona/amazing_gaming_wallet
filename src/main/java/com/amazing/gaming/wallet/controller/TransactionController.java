package com.amazing.gaming.wallet.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.amazing.gaming.wallet.dto.TransactionDTO;
import com.amazing.gaming.wallet.entities.Transaction;
import com.amazing.gaming.wallet.entities.Wallet;
import com.amazing.gaming.wallet.exceptions.TransactionException;
import com.amazing.gaming.wallet.populators.TransactionPopulator;
import com.amazing.gaming.wallet.populators.WalletPopulator;
import com.amazing.gaming.wallet.services.TransactionService;

import lombok.RequiredArgsConstructor;


@RestController
@RequiredArgsConstructor
public class TransactionController
{
	private final TransactionService transactionService;

	private final TransactionPopulator transactionPopulator;
	private final WalletPopulator walletPopulator = new WalletPopulator();

	@PostMapping("/deposit")
	public ResponseEntity<Object> deposit(@Valid @RequestBody TransactionDTO transactionDTO, BindingResult result)
	{
		if (result.hasErrors())
		{
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

		if (!transactionService.validateTransactionId(transactionDTO.getTransactionId())) {
			return new ResponseEntity<>("transactionId already exists", HttpStatus.INTERNAL_SERVER_ERROR);
		}

		final Wallet wallet = transactionService.deposit(transactionPopulator.convertToEntity(transactionDTO));

		return new ResponseEntity<>(walletPopulator.convertToDTO(wallet), HttpStatus.OK);
	}

	@PostMapping("/withdraw")
	public ResponseEntity<Object> withdraw(@Valid @RequestBody TransactionDTO transactionDTO, BindingResult result)
	{
		if (result.hasErrors())
		{
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

		if (!transactionService.validateTransactionId(transactionDTO.getTransactionId())) {
			return new ResponseEntity<>("transactionId already exists", HttpStatus.INTERNAL_SERVER_ERROR);
		}

		try
		{
			final Wallet wallet = transactionService.withdraw(transactionPopulator.convertToEntity(transactionDTO));
			return new ResponseEntity<>(walletPopulator.convertToDTO(wallet), HttpStatus.OK);
		}
		catch (TransactionException e)
		{
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("/bet")
	public ResponseEntity<Object> registerBet(@Valid @RequestBody TransactionDTO transactionDTO, BindingResult result)
	{
		if (result.hasErrors())
		{
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

		if (!transactionService.validateTransactionId(transactionDTO.getTransactionId())) {
			return new ResponseEntity<>("transactionId already exists", HttpStatus.INTERNAL_SERVER_ERROR);
		}

		try
		{
			final Wallet wallet = transactionService.registerBet(transactionPopulator.convertToEntity(transactionDTO));
			return new ResponseEntity<>(walletPopulator.convertToDTO(wallet), HttpStatus.OK);
		}
		catch (TransactionException e)
		{
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("/win")
	public ResponseEntity<Object> registerWin(@Valid @RequestBody TransactionDTO transactionDTO, BindingResult result)
	{
		if (result.hasErrors())
		{
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

		if (!transactionService.validateTransactionId(transactionDTO.getTransactionId())) {
			return new ResponseEntity<>("transactionId already exists", HttpStatus.INTERNAL_SERVER_ERROR);
		}

		try
		{
			final Wallet wallet = transactionService.registerWin(transactionPopulator.convertToEntity(transactionDTO));
			return new ResponseEntity<>(walletPopulator.convertToDTO(wallet), HttpStatus.OK);
		}
		catch (TransactionException e)
		{
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/transactions")
	public ResponseEntity<Object> findAll(@RequestParam(defaultValue = "0") Integer pageNumber,
			@RequestParam(defaultValue = "10") Integer pageSize, @RequestParam(defaultValue = "id") String sortBy)
	{
		List<Transaction> transactions = transactionService.findAll(pageNumber, pageSize, sortBy);

		if (transactions.isEmpty())
		{
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(transactions.stream().map(transaction -> transactionPopulator.convertToDTO(transaction))
				.collect(Collectors.toList()), HttpStatus.OK);
	}

}
