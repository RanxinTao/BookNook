package com.bookstore.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;

import com.bookstore.dao.DAO;
import com.bookstore.db.JDBCUtils;

public class BaseDAO<T> implements DAO<T> {
	
	private QueryRunner queryRunner = new QueryRunner();

	@Override
	public long insert(String sql, Object... args) {
		long id = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = JDBCUtils.getConnetion();
			pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			if(args != null) {
				for(int i=0; i<args.length; i++) {
					pstmt.setObject(i+1, args[i]);
				}
			}
			pstmt.executeUpdate();
			//get the generated primary key
			rs = pstmt.getGeneratedKeys();
			if(rs.next()) {
				id = rs.getLong(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.close(rs, pstmt);
			JDBCUtils.close(conn);
		}
		return id;
	}

	@Override
	public void update(String sql, Object... args) {
		Connection conn = null;
		try {
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.close(conn);
		}
		
	}

	@Override
	public T query(String sql, Object... args) {
		Connection conn = null;
		try {
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.close(conn);
		}
		return null;
	}

	@Override
	public List<T> queryList(String sql, Object... args) {
		Connection conn = null;
		try {
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.close(conn);
		}
		return null;
	}

	@Override
	public <V> V getSingleVal(String sql, Object... args) {
		Connection conn = null;
		try {
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.close(conn);
		}
		return null;
	}

	@Override
	public void batch(String sql, Object[]... params) {
		Connection conn = null;
		try {
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.close(conn);
		}
		
	}

}
