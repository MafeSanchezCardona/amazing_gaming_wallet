package com.amazing.gaming.wallet.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.PositiveOrZero;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@Entity
@Table(name="wallet")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Wallet
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id", updatable = false,nullable = false,unique = true)
	private Long id;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="player_id")
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	private Player player;

	@PositiveOrZero
	@Column(name="cash_balance")
	private Double cashBalance;

	@PositiveOrZero
	@Column(name="bonus_balance")
	private Double bonusBalance;
}
