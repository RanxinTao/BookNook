package com.bookstore.test;

import org.junit.Test;

import com.bookstore.dao.UserDAO;
import com.bookstore.dao.impl.UserDAOImpl;
import com.bookstore.entity.User;

public class UserDAOTest {

	private UserDAO userDAO = new UserDAOImpl();
	
	@Test
	public void testGetUser() {
		User user = userDAO.getUser("AA");
		System.out.println(user);
	}

}
