package com.bookstore.test;

import org.junit.Test;

import com.bookstore.dao.AccountDAO;
import com.bookstore.dao.impl.AccountDAOImpl;
import com.bookstore.entity.Account;

public class AccountDAOTest {

	AccountDAO accountDAO = new AccountDAOImpl();
	
	@Test
	public void testGetAccount() {
		Account account = accountDAO.getAccount(1);
		System.out.println(account.getBalance());
	}

	@Test
	public void testUpdateBalance() {
		accountDAO.updateBalance(1, 50);
	}

}
