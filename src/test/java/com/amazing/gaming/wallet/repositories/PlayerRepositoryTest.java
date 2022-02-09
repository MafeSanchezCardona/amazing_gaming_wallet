package com.amazing.gaming.wallet.repositories;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.amazing.gaming.wallet.entities.Player;


@DataJpaTest
public class PlayerRepositoryTest
{
	@Autowired
	private PlayerRepository playerRepository;

	@Test
	public void whenFindById_then_Return()
	{
		Player playerSearch = playerRepository.findById(1L).orElse(null);

		Assertions.assertThat(playerSearch).isNotNull();
		Assertions.assertThat(playerSearch.getName()).isEqualTo("Maria");
		Assertions.assertThat(playerSearch.getLastName()).isEqualTo("Sanchez");
	}

	@Test
	public void whenFindById_then_ReturnThreeResults()
	{
		Player player = Player.builder().id(3L).name("Agustin").lastName("Perez").build();

		playerRepository.save(player);

		List<Player> playerList = playerRepository.findAll();
		Assertions.assertThat(playerList.size()).isEqualTo(3);
	}
}
