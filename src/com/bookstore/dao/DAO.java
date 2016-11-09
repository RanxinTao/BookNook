package com.bookstore.dao;

import java.util.List;

public interface DAO<T> {
	
	long insert(String sql, Object ... args); //perform INSERT operation, then return the id of the record
	
	void update(String sql, Object ... args); //perform INSERT, UPDATE, DELETE operations without return value
	
	T query(String sql, Object ... args); //query a single record, return an instance of the record
	
	List<T> queryList(String sql, Object ... args); //query mutiple records, return a list of instances of the records
	
	<V> V getSingleVal(String sql, Object ... args); //query a field of a T instance or aggregate data
	
	void batch(String sql, Object[] ... params); //batch update methods
	
}
