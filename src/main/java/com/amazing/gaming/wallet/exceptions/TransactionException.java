package com.amazing.gaming.wallet.exceptions;

public class TransactionException extends Exception
{
	public TransactionException()
	{
		super();
	}

	public TransactionException(final String message)
	{
		super(message);
	}

	public TransactionException(final Throwable ex)
	{
		super(ex);
	}
}
