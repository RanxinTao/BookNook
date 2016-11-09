package com.bookstore.entity;

public class Account {
	
	private int accountId;
	private int balance;
	
	public Account() {}
	
	public int getAccountId() {
		return accountId;
	}
	
	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}
	
	public int getBalance() {
		return balance;
	}
	
	public void setBalance(int balance) {
		this.balance = balance;
	}
	
	public Account(int accountId, int balance) {
		this.accountId = accountId;
		this.balance = balance;
	}
	
	@Override
	public String toString() {
		return "Account [accountId=" + accountId + ", balance=" + balance + "]";
	}
	
}
