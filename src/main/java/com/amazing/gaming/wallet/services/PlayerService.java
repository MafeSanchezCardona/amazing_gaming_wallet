package com.amazing.gaming.wallet.services;

import java.util.List;

import com.amazing.gaming.wallet.entities.Player;


/**
 * Player Service
 */
public interface PlayerService
{
	/**
	 * Method to save the player entity
	 * @param player
	 */
	void save(final Player player);

	/**
	 * Method to list of players
	 * @return
	 */
	List<Player> findAll();

	/**
	 * Method to get player by id
	 * @param id
	 * @return Player
	 */
	Player findById(Long id);
}
