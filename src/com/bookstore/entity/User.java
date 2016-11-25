package com.bookstore.entity;

import java.util.LinkedHashSet;
import java.util.Set;

public class User {
	
	private int userId;
	private String username;
	private int accountNo;
	private Set<Trade> trades = new LinkedHashSet<Trade>();
	
	public User() {}
	
	public User(int userId, String username, int accountNo) {
		this.userId = userId;
		this.username = username;
		this.accountNo = accountNo;
	}
	
	public void setTrades(Set<Trade> trades) {
		this.trades = trades;
	}
	
	public Set<Trade> getTrades() {
		return trades;
	}
	
	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getAccountNo() {
		return accountNo;
	}

	public void setAccountNo (int accountNo) {
		this.accountNo = accountNo;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", username=" + username
				+ ", accountNo=" + accountNo + "]";
	}

}
