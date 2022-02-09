package com.amazing.gaming.wallet.services;

import java.util.Objects;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.amazing.gaming.wallet.entities.Player;
import com.amazing.gaming.wallet.repositories.PlayerRepository;
import com.amazing.gaming.wallet.services.impl.PlayerServiceImpl;


@SpringBootTest
public class PlayerServiceMockTest
{
	@Mock
	private PlayerRepository playerRepository;

	private PlayerService playerService;

	@BeforeEach
	public void begin()
	{
		MockitoAnnotations.initMocks(this);
		playerService = new PlayerServiceImpl(playerRepository);

		Mockito.when(playerRepository.findById(1L))
				.thenReturn(Optional.of(Player.builder().id(1L).name("Maria").lastName("Sanchez").build()));
	}

	@Test
	public void when_findById_return_player()
	{
		Player player = playerService.findById(1L);
		Assertions.assertThat(player.getName()).isEqualTo("Maria");
		Assertions.assertThat(player.getLastName()).isEqualTo("Sanchez");
	}

	@Test
	public void when_findById_return_not_exists()
	{
		Player player = playerService.findById(3L);
		Assertions.assertThat(Objects.isNull(player));
	}
}
