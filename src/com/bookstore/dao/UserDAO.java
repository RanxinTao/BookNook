package com.bookstore.dao;

import com.bookstore.entity.User;

public interface UserDAO {

	public abstract User getUser(String username);
	
}
