package com.bookstore.dao;

import java.util.Collection;
import java.util.List;

import com.bookstore.entity.Book;
import com.bookstore.entity.ShoppingCartItem;
import com.bookstore.web.CriteriaBook;
import com.bookstore.web.Page;

public interface BookDAO {

	public abstract Book getBook(int id);
	
	public abstract Page<Book> getPage(CriteriaBook cb); //return a page object based on a CriteriaBook object
	
	public abstract long getTotalBookNo(CriteriaBook cb);
	
	public abstract List<Book> getPageList(CriteriaBook cb, int pageSize);
	
	public abstract int getStockNo(int id);
	
	//public abstract void batchUpdateStockNoAndSalesAmount(Collection<ShoppingCartItem> items);
	
}
