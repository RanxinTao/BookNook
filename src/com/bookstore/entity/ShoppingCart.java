package com.bookstore.entity;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class ShoppingCart {
	
	private Map<Integer, ShoppingCartItem> books = new HashMap<>();
	
	public void updateItemQuantity(int id, int quantity) {
		ShoppingCartItem sci = books.get(id);
		if(sci != null) 
			sci.setQuantity(quantity);
	}
	
	public void removeItem(int id) {
		books.remove(id);
	}
	
	public void clear() {
		books.clear();
	}
	
	public boolean isEmpty() {
		return books.isEmpty();
	}
	
	public float getTotalCost() {
		float total = 0;
		for(ShoppingCartItem sci : getItems()) {
			total += sci.getItemTotalCost();
		}	
		return total;
	}
	
	public Collection<ShoppingCartItem> getItems() {
		return books.values();
	}
	
	public int getTotalBookNo() {
		int total = 0;
		for(ShoppingCartItem sci : books.values()) {
			total += sci.getQuantity();
		}
		return total;
	}
	
	public Map<Integer, ShoppingCartItem> getBooks() {
		return books;
	}
	
	public boolean hasBook(int id) {
		return books.containsKey(id);
	}
	
	public void addBook(Book book) {
		//check if the shopping cart has that book, if yes, increment its quantity by 1; if no, create one.
		ShoppingCartItem sci = books.get(book.getId());
		if(sci == null) {
			sci = new ShoppingCartItem(book);
			books.put(book.getId(), sci);
		} else {
			sci.increment();
		}
	}
	
}
