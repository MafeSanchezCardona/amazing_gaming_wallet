package com.amazing.gaming.wallet.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TransactionDTO
{
	private Long id;

	@Positive(message = "The transactionId must not be empty")
	private Long transactionId;

	@Positive(message = "The playerId must not be empty")
	private Long playerId;

	@Positive(message = "The amount must be greater than zero")
	private Double amount;

	private Double proportionalBet;
}
