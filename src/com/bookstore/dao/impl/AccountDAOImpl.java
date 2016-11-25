package com.bookstore.dao.impl;

import com.bookstore.dao.AccountDAO;
import com.bookstore.entity.Account;

public class AccountDAOImpl extends BaseDAO<Account> implements AccountDAO {

	@Override
	public Account getAccount(int accountNo) {
		String sql = "SELECT accountNo, balance FROM account WHERE accountNo = ?";
		return query(sql, accountNo);
	}

	@Override
	public void updateBalance(int accountNo, float amount) {
		String sql = "UPDATE account SET balance = balance - ? WHERE accountNo = ?";
		update(sql, amount, accountNo);
	}

}
