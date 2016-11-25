package com.bookstore.service;

import com.bookstore.dao.AccountDAO;
import com.bookstore.dao.impl.AccountDAOImpl;
import com.bookstore.entity.Account;

public class AccountService {
	
	private AccountDAO accountDAO = new AccountDAOImpl();
	
	public Account getAccount(int accountNo) {
		return accountDAO.getAccount(accountNo);
	}
}
