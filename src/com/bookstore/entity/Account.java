package com.bookstore.entity;

public class Account {
	
	private int accountNo;
	private int balance;
	
	public Account() {}
	
	public int getAccountNo() {
		return accountNo;
	}
	
	public void setAccountNo(int accountNo) {
		this.accountNo = accountNo;
	}
	
	public int getBalance() {
		return balance;
	}
	
	public void setBalance(int balance) {
		this.balance = balance;
	}
	
	public Account(int accountNo, int balance) {
		this.accountNo = accountNo;
		this.balance = balance;
	}
	
	@Override
	public String toString() {
		return "Account [accountNo=" + accountNo + ", balance=" + balance + "]";
	}
	
}
