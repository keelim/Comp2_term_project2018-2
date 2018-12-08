package BangBang.src.bank;

import java.io.Serializable;

public abstract class Account implements Serializable
{
	public abstract String getAcctType();
	public Account(String acctNo, double init_balance)
	{
		balance = init_balance;
		accountNumber = acctNo;
	}
	
	public boolean deposit(double amt)
	{
		balance = balance + amt;
		return true;
	}
	
	public boolean withdraw(double amt)
	{		
		balance -= amt;
		return true;
	}
	
	public double getBalance() { return balance; }
	public String getAcctNum() { return accountNumber; }
	
	protected double balance;
	private String accountNumber;
}
