package com.bookstore.dao;

import com.bookstore.entity.Account;

public interface AccountDAO {
	
	public abstract Account getAccount(int accountNo);
	
	public abstract void updateBalance(int accountNo, float amount);
	
}
