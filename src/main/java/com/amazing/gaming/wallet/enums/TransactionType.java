package com.amazing.gaming.wallet.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor
public enum TransactionType
{
	DEPOSIT(1),
	WITHDRAW(2),
	WIN(3),
	BET(4);

	private final int id;
}
