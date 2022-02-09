package com.amazing.gaming.wallet.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@Entity
@Table(name="transaction")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Transaction
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id", updatable = false,nullable = false,unique = true)
	private Long id;

	@Column(name="transaction_id")
	private Long transactionId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="player_id")
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	private Player player;

	@Column(name="type")
	private Integer type;

	@Column(name="amount")
	private Double amount;

	@Column(name="proportional_bet")
	private Double proportionalBet;
}
