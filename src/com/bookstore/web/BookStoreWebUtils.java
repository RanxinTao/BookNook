package com.bookstore.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.bookstore.entity.ShoppingCart;

public class BookStoreWebUtils {
	
	public static ShoppingCart getShoppingCart(HttpServletRequest req) {
		HttpSession session = req.getSession();	
		ShoppingCart sc = (ShoppingCart) session.getAttribute("ShoppingCart");
		
		if(sc == null) {
			sc = new ShoppingCart();
			session.setAttribute("ShoppingCart", sc);
		}
		return sc;
	}
}
