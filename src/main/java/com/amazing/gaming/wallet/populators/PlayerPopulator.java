package com.amazing.gaming.wallet.populators;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.RequestBody;

import com.amazing.gaming.wallet.dto.PlayerDTO;
import com.amazing.gaming.wallet.entities.Player;

import lombok.AllArgsConstructor;


@AllArgsConstructor
public class PlayerPopulator
{
	private final ModelMapper modelMapper = new ModelMapper();

	public Player convertToEntity(@RequestBody final @Valid PlayerDTO playerDTO)
	{
		return modelMapper.map(playerDTO, Player.class);
	}

	public PlayerDTO convertToDTO(final Player player)
	{
		return modelMapper.map(player, PlayerDTO.class);
	}
}
