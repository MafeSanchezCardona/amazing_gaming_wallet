package com.amazing.gaming.wallet.populators;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.RequestBody;

import com.amazing.gaming.wallet.dto.WalletDTO;
import com.amazing.gaming.wallet.entities.Wallet;

import lombok.AllArgsConstructor;


@AllArgsConstructor
public class WalletPopulator
{
	private final ModelMapper modelMapper = new ModelMapper();

	public Wallet convertToEntity(@RequestBody final @Valid WalletDTO walletDTO)
	{
		return modelMapper.map(walletDTO, Wallet.class);
	}

	public WalletDTO convertToDTO(final Wallet wallet)
	{
		return modelMapper.map(wallet, WalletDTO.class);
	}
}
