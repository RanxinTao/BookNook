package com.bookstore.dao;

import java.util.Set;

import com.bookstore.entity.Trade;

public interface TradeDAO {
	
	public abstract void insert(Trade trade); //insert a trade into database
	
	public abstract Set<Trade> getTradesByUserId(int userId); //return a set of trades associate with the userId
	
}
