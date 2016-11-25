package com.bookstore.service;

import com.bookstore.dao.UserDAO;
import com.bookstore.dao.impl.UserDAOImpl;
import com.bookstore.entity.User;

public class UserService {
	
	private UserDAO userDAO = new UserDAOImpl();
	
	public User getUserByUsername(String username) {
		return userDAO.getUser(username);
	}
	
}
