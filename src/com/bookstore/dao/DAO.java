package com.bookstore.dao;

import java.util.List;

public interface DAO<T> {
	
	/**
	 * perform INSERT operation, return the id of the record
	 * @param sql
	 * @param args
	 * @return the id of the record
	 */
	long insert(String sql, Object... args);
	
	/**
	 * perform INSERT, UPDATE, DELETE operations without return value
	 * @param sql
	 * @param args
	 */
	void update(String sql, Object... args);
	
	/**
	 * query a single record, return an instance of the record
	 * @param sql
	 * @param args
	 * @return an instance of the record
	 */
	T query(String sql, Object... args);
	
	/**
	 * query mutiple records, return a list of instances of the records
	 * @param sql
	 * @param args
	 * @return a list of instances of the records
	 */
	List<T> queryList(String sql, Object... args);
	
	/**
	 * query a field of a T instance or aggregate data, return the value of the field
	 * @param sql
	 * @param args
	 * @return the value of the field
	 */
	<V> V getSingleVal(String sql, Object... args);
	
	/**
	 * batch update methods
	 * @param sql
	 * @param params
	 */
	void batch(String sql, Object[]... params);
	
}
