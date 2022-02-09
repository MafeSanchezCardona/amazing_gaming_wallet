package com.amazing.gaming.wallet.populators;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import com.amazing.gaming.wallet.dto.TransactionDTO;
import com.amazing.gaming.wallet.entities.Transaction;
import com.amazing.gaming.wallet.services.PlayerService;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class TransactionPopulator
{
	private final ModelMapper modelMapper = new ModelMapper();

	private final PlayerService playerService;

	public Transaction convertToEntity(@RequestBody final @Valid TransactionDTO transactionDTO)
	{
		final Transaction transaction = modelMapper.map(transactionDTO, Transaction.class);
		transaction.setPlayer(playerService.findById(transactionDTO.getPlayerId()));
		return transaction;
	}

	public TransactionDTO convertToDTO(final Transaction transaction)
	{
		return modelMapper.map(transaction, TransactionDTO.class);
	}
}
