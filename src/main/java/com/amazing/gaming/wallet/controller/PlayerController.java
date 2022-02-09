package com.amazing.gaming.wallet.controller;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.amazing.gaming.wallet.dto.PlayerDTO;
import com.amazing.gaming.wallet.entities.Player;
import com.amazing.gaming.wallet.populators.PlayerPopulator;
import com.amazing.gaming.wallet.services.PlayerService;

import lombok.RequiredArgsConstructor;


@RestController
@RequiredArgsConstructor
@RequestMapping("/player")
public class PlayerController
{
	private final PlayerService playerService;

	private final PlayerPopulator playerPopulator = new PlayerPopulator();

	@PostMapping
	public ResponseEntity<String> save(@Valid @RequestBody PlayerDTO playerDTO, BindingResult result)
	{
		if (result.hasErrors())
		{
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

		playerService.save(playerPopulator.convertToEntity(playerDTO));

		return new ResponseEntity<>(HttpStatus.OK);
	}

	@GetMapping
	public ResponseEntity<Object> findAll()
	{
		List<Player> players = playerService.findAll();

		if (players.isEmpty())
		{
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(
				players.stream().map(player -> playerPopulator.convertToDTO(player)).collect(Collectors.toList()), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Object> findById(@PathVariable("id") Long id)
	{
		Player player = playerService.findById(id);

		if (Objects.isNull(player))
		{
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}

		return new ResponseEntity<>(playerPopulator.convertToDTO(player), HttpStatus.OK);

	}
}
