package com.bookstore.service;

import com.bookstore.dao.BookDAO;
import com.bookstore.dao.impl.BookDAOImpl;
import com.bookstore.entity.Book;
import com.bookstore.web.CriteriaBook;
import com.bookstore.web.Page;

public class BookService {
	
	private BookDAO bookDAO = new BookDAOImpl();
	
	public Page<Book> getPage(CriteriaBook cb) {
		return bookDAO.getPage(cb);
	}
}
