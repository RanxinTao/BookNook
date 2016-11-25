package com.bookstore.dao.impl;

import java.util.List;

import com.bookstore.dao.BookDAO;
import com.bookstore.entity.Book;
import com.bookstore.web.CriteriaBook;
import com.bookstore.web.Page;

public class BookDAOImpl extends BaseDAO<Book> implements BookDAO {

	@Override
	public Book getBook(int id) {
		String sql = "SELECT id, author, title, price, publishingDate, salesAmount, stockNumber, remark "
				+ "FROM mybooks WHERE id = ?";
		return query(sql, id);
	}

	@Override
	public Page<Book> getPage(CriteriaBook cb) {
		Page page = new Page<>(cb.getPageNo());
		page.setTotalItemNo(getTotalBookNo(cb));
		
		//make sure pageNo is legal
		cb.setPageNo(page.getPageNo());
		page.setList(getPageList(cb, 3));
		return page;
	}

	@Override
	public long getTotalBookNo(CriteriaBook cb) {
		String sql = "SELECT count(id) FROM mybooks WHERE price >= ? AND price <= ?";
		return getSingleVal(sql, cb.getMinPrice(), cb.getMaxPrice());
	}

	@Override
	public List<Book> getPageList(CriteriaBook cb, int pageSize) {
		String sql = "SELECT id, author, title, price, publishingDate, salesAmount, stockNumber, remark "
				+ "FROM mybooks WHERE price >= ? AND price <= ? LIMIT ?, ?";
		return queryList(sql, cb.getMinPrice(), cb.getMaxPrice(), (cb.getPageNo()-1)*pageSize, pageSize);
	}

	@Override
	public int getStockNo(int id) {
		String sql = "SELECT stockNumber FROM mybooks WHERE id = ?";
		return getSingleVal(sql, id);
	}
	
}
