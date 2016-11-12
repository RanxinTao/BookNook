package com.bookstore.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import com.bookstore.exception.DBException;
import com.mchange.v2.c3p0.ComboPooledDataSource;

public class JDBCUtils {
	
	private static DataSource dataSource = null;
	
	static {
		dataSource = new ComboPooledDataSource("javawebapp");
	}
	
	public static Connection getConnetion() {
		try {
			return dataSource.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DBException("Database connection error!");
		}
	}
	
	public static void close(Connection conn) {
		try {
			if(conn != null)
				conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DBException("Database connection error!");
		}
	}
	
	public static void close(ResultSet rs, Statement stmt) {
		try {
			if(rs != null)
				rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DBException("Database connection error!");
		}
		
		try {
			if(stmt != null)
				stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DBException("Database connection error!");
		}
	}

}
