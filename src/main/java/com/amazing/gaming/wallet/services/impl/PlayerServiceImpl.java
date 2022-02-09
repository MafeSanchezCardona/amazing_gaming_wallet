package com.amazing.gaming.wallet.services.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.amazing.gaming.wallet.entities.Player;
import com.amazing.gaming.wallet.repositories.PlayerRepository;
import com.amazing.gaming.wallet.services.PlayerService;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class PlayerServiceImpl implements PlayerService
{
	private final PlayerRepository playerRepository;

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void save(final Player player)
	{
		playerRepository.save(player);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Player> findAll()
	{
		return playerRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Player findById(final Long id)
	{
		return playerRepository.findById(id).orElse(null);
	}
}
