package com.bookstore.dao.impl;

import com.bookstore.dao.UserDAO;
import com.bookstore.entity.User;

public class UserDAOImpl extends BaseDAO<User> implements UserDAO {

	@Override
	public User getUser(String username) {
		String sql = "SELECT userId, username, accountNo FROM userinfo WHERE username = ?";
		return query(sql, username);
	}

}
