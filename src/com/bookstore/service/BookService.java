package com.bookstore.service;

import com.bookstore.dao.BookDAO;
import com.bookstore.dao.impl.BookDAOImpl;
import com.bookstore.entity.Book;
import com.bookstore.entity.ShoppingCart;
import com.bookstore.web.CriteriaBook;
import com.bookstore.web.Page;

public class BookService {
	
	private BookDAO bookDAO = new BookDAOImpl();
	
	public Page<Book> getPage(CriteriaBook cb) {
		return bookDAO.getPage(cb);
	}

	public Book getBook(int id) {
		return bookDAO.getBook(id);
	}

	public boolean addToCart(int id, ShoppingCart sc) {
		Book book = bookDAO.getBook(id);
		if(book != null) {
			sc.addBook(book);
			return true;
		}
		return false;
	}

	public void removeItemFromShoppingCart(ShoppingCart sc, int id) {
		sc.removeItem(id);
	}

	public void clearShoppingCart(ShoppingCart sc) {
		sc.clear();
	}

	public void updateItemQuantity(ShoppingCart sc, int id, int quantity) {
		sc.updateItemQuantity(id, quantity);
	}
}
