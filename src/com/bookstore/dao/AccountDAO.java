package com.bookstore.dao;

import com.bookstore.entity.Account;

public interface AccountDAO {
	
	public abstract Account getAccount(int accountId);
	
	public abstract void updateBalance(int accountId, float amount);
	
}
