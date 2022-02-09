package com.amazing.gaming.wallet.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.amazing.gaming.wallet.entities.Player;
import com.amazing.gaming.wallet.entities.Wallet;


@Repository
public interface WalletRepository extends JpaRepository<Wallet,Long>
{
	Wallet findByPlayer(final Player player);
}
