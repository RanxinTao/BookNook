package com.bookstore.test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.bookstore.dao.BookDAO;
import com.bookstore.dao.impl.BookDAOImpl;
import com.bookstore.entity.Book;
import com.bookstore.web.CriteriaBook;
import com.bookstore.web.Page;

public class BookDAOTest {

	private BookDAO bookDAO = new BookDAOImpl();
	
	@Test
	public void testGetBook() {
		Book book = bookDAO.getBook(3);
		System.out.println(book);
	}

	@Test
	public void testGetPage() {
		CriteriaBook cb = new CriteriaBook(50, 60, 3);
		Page<Book> page = bookDAO.getPage(cb);
		System.out.println("pageNo: " + page.getPageNo());
		System.out.println("totalPageNo: " + page.getTotalPageNo());
		System.out.println("list: " + page.getList());
		System.out.println("prevPage: " + page.getPrevPageNo());
		System.out.println("nextPage: " + page.getNextPageNo());
	}

	@Test
	public void testGetStoreNo() {
		int storeNumber = bookDAO.getStoreNo(5);
		System.out.println(storeNumber);
	}

}
