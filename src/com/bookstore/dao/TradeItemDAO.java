package com.bookstore.dao;

import java.util.Collection;
import java.util.Set;

import com.bookstore.entity.TradeItem;

public interface TradeItemDAO {

	public abstract void batchSave(Collection<TradeItem> items);
	
	public abstract Set<TradeItem> getTradeItemsByTradeId(int tradeId);
	
}
