package com.amazing.gaming.wallet.services;

import java.util.List;

import com.amazing.gaming.wallet.entities.Player;


public interface PlayerService
{
	void save(final Player player);

	List<Player> findAll();

	Player findById(Long id);
}
