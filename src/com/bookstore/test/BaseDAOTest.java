package com.bookstore.test;

import static org.junit.Assert.*;

import java.sql.Date;

import org.junit.Test;

import com.bookstore.dao.impl.BaseDAO;

public class BaseDAOTest {

	private BaseDAO baseDAO = new BaseDAO();
	
	@Test
	public void testInsert() {
		String sql = "INSERT INTO trade (userid, tradetime) VALUES (?, ?)";
		long id = baseDAO.insert(sql, 1, new Date(new java.util.Date().getTime()));
		System.out.println(id);
	}

	@Test
	public void testUpdate() {
		fail("Not yet implemented");
	}

	@Test
	public void testQuery() {
		fail("Not yet implemented");
	}

	@Test
	public void testQueryList() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetSingleVal() {
		fail("Not yet implemented");
	}

	@Test
	public void testBatch() {
		fail("Not yet implemented");
	}

}
