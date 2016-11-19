package com.bookstore.servlet;

import java.io.IOException;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookstore.entity.Book;
import com.bookstore.entity.ShoppingCart;
import com.bookstore.service.BookService;
import com.bookstore.web.BookStoreWebUtils;
import com.bookstore.web.CriteriaBook;
import com.bookstore.web.Page;

@WebServlet("/bookServlet")
public class BookServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private BookService bookService = new BookService();

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String methodName = req.getParameter("method");
		try {
			Method method = getClass().getDeclaredMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);
			method.setAccessible(true);
			method.invoke(this, req, resp);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	protected void clear(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ShoppingCart sc = BookStoreWebUtils.getShoppingCart(req);
		bookService.clearShoppingCart(sc);
		
		req.getRequestDispatcher("/WEB-INF/pages/emptycart.jsp").forward(req, resp);
	}
	
	protected void remove(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String idStr = req.getParameter("id");
		int id = -1;
		
		try {
			id = Integer.parseInt(idStr);
		} catch (Exception e) {}
		
		ShoppingCart sc = BookStoreWebUtils.getShoppingCart(req);
		bookService.removeItemFromShoppingCart(sc, id);
		
		if(sc.isEmpty()) {
			req.getRequestDispatcher("/WEB-INF/pages/emptycart.jsp").forward(req, resp);
			return;
		}
		
		req.getRequestDispatcher("WEB-INF/pages/cart.jsp").forward(req, resp);
	}
	
	protected void toCartPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("WEB-INF/pages/cart.jsp").forward(req, resp);
	}
	
	protected void addToCart(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//1. get book id
		String idStr = req.getParameter("id");
		int id = -1;
		boolean flag = false;
		
		try {
			id = Integer.parseInt(idStr);
		} catch (Exception e) {}
		
		if(id > 0) {
			//2. get shopping cart object
			ShoppingCart sc = BookStoreWebUtils.getShoppingCart(req);
			//3. call BookService addToCart() method, add book into shopping cart. 
			flag = bookService.addToCart(id, sc); //if the book associated with this id is found, flag is set to true
		}
		
		if(flag) {
			//4. call getBooks() method
			getBooks(req, resp);
			return;
		}
		
		resp.sendRedirect(req.getContentType() + "/error-1.jsp");	
	}
	
	protected void getBook(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String idStr = req.getParameter("id");
		int id = -1;
		Book book = null;
		
		try {
			id = Integer.parseInt(idStr);
		} catch (NumberFormatException e) {}
		
		if(id > 0)
			book = bookService.getBook(id);
		if(book == null) {
			resp.sendRedirect(req.getContextPath() + "/error-1.jsp");
			return;
		}
			
		req.setAttribute("book", book);
		req.getRequestDispatcher("/WEB-INF/pages/book.jsp").forward(req, resp);
	}
	
	protected void getBooks(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String pageNoStr = req.getParameter("pageNo");
		String minPriceStr = req.getParameter("minPrice");
		String maxPriceStr = req.getParameter("maxPrice");
		
		int pageNo = 1;
		int minPrice = 0;
		int maxPrice = Integer.MAX_VALUE;
		
		try {
			pageNo = Integer.parseInt(pageNoStr);
		} catch (NumberFormatException e) {}
		try {
			minPrice = Integer.parseInt(minPriceStr);
		} catch (NumberFormatException e) {}
		try {
			maxPrice = Integer.parseInt(maxPriceStr);
		} catch (NumberFormatException e) {}
		
		CriteriaBook cb = new CriteriaBook(minPrice, maxPrice, pageNo);
		Page<Book> page = bookService.getPage(cb);
		
		req.setAttribute("bookpage", page);
		req.getRequestDispatcher("/WEB-INF/pages/books.jsp").forward(req, resp);
	}

}
