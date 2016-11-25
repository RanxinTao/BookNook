package com.bookstore.servlet;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookstore.entity.Account;
import com.bookstore.entity.Book;
import com.bookstore.entity.ShoppingCart;
import com.bookstore.entity.ShoppingCartItem;
import com.bookstore.entity.User;
import com.bookstore.service.AccountService;
import com.bookstore.service.BookService;
import com.bookstore.service.UserService;
import com.bookstore.web.BookStoreWebUtils;
import com.bookstore.web.CriteriaBook;
import com.bookstore.web.Page;
import com.google.gson.Gson;

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
	
	private AccountService accountService = new AccountService();
	
	//validate if the account has enough balance
	public StringBuffer validateBalance(HttpServletRequest req, String accountNo) {
		ShoppingCart cart = BookStoreWebUtils.getShoppingCart(req);
		StringBuffer errors = new StringBuffer();
		
		Account account = accountService.getAccount(Integer.parseInt(accountNo));
		if(cart.getTotalCost() > account.getBalance()) 
			errors.append("Your balance is not enough!");
		return errors;
	}
	
	//validate if there are adequate stocks of books
	public StringBuffer validateBookStock(HttpServletRequest req) {
		ShoppingCart cart = BookStoreWebUtils.getShoppingCart(req);
		StringBuffer errors = new StringBuffer();
		
		for(ShoppingCartItem sci : cart.getItems()) {
			int quantity = sci.getQuantity();
			int stock = bookService.getBook(sci.getBook().getId()).getStockNumber();
			
			if(quantity > stock) 
				errors.append(sci.getBook().getTitle() + " is currently out of stock.<br>");
		}
		
		return errors;
	}
	
	//validate if username matches account number
	public StringBuffer validateUser(String username, String accountNo) {
		boolean flag = false;
		User user = userService.getUserByUsername(username);
		if(user != null) {
			int _accountNo = user.getAccountNo();
			if(accountNo.trim().equals("" + _accountNo)) {
				flag = true;
			}
		}
		
		StringBuffer errors = new StringBuffer();
		if(!flag) 
			errors.append("The username does not match this account number according to our record.");
		
		return errors;
	}
	
	//validate basic rules of form fields: e.g. not null
	public StringBuffer validateFormField(String username, String accountNo) {
		StringBuffer errors = new StringBuffer();
		
		if(username == null || username.trim().equals("")) {
			errors.append("Username can not be empty. <br>");
		}
		if(accountNo == null || accountNo.trim().equals("")) {
			errors.append("Account number can not be empty.");
		}
		return errors;
	}
	
	private UserService userService = new UserService();
	
	protected void checkout(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String username = req.getParameter("username");
		String accountNo = req.getParameter("accountNo");
		
		//1. simple validation (i.e. no need to query database or call any business methods)
		//Ex., if it is null, if it can be cast to int, if it is an email address
		StringBuffer errors = validateFormField(username, accountNo);
		
		//form field verified
		if(errors.length() == 0) {
			errors = validateUser(username, accountNo);
			
			//2. complex validation
			//username and account number verified
			if(errors.length() == 0) {
				errors = validateBookStock(req);
				
				//stock verified
				if(errors.length() == 0) {
					errors = validateBalance(req, accountNo);	
				}
			}	
		}
		
		if(errors.length() != 0) {
			req.setAttribute("errors", errors);
			req.getRequestDispatcher("/WEB-INF/pages/checkout.jsp").forward(req, resp);
			return;
		}
	}
	
	protected void updateItemQuantity(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String idStr = req.getParameter("id");
		String quantityStr = req.getParameter("quantity");
		
		ShoppingCart sc = BookStoreWebUtils.getShoppingCart(req);
		
		int id = 1;
		int quantity = -1;
		
		try {
			id = Integer.parseInt(idStr);
			quantity = Integer.parseInt(quantityStr);
		} catch (Exception e) {}
		
		if(id > 0 && quantity > 0)
			bookService.updateItemQuantity(sc, id, quantity);
			
		Map<String, Object> res = new HashMap<String, Object>();
		res.put("totalBookNo", sc.getTotalBookNo());
		res.put("totalCost", sc.getTotalCost());
		
		//return JSON data: totalBookNo, totalCost
		Gson gson = new Gson();
		String jsonStr = gson.toJson(res);
		resp.setContentType("text/javascript");
		resp.getWriter().print(jsonStr);
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
	
	protected void forwardPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String page = req.getParameter("page");
		req.getRequestDispatcher("WEB-INF/pages/" + page + ".jsp").forward(req, resp);
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
