package com.bookstore.entity;

import java.sql.Date;
import java.util.LinkedHashSet;
import java.util.Set;

public class Trade {

	private int tradeId;
	private Date tradeTime;
	private Set<TradeItem> items = new LinkedHashSet<TradeItem>();
	private int userId; //userId that associates with this trade
	
	public void setItems(Set<TradeItem> items) {
		this.items = items;
	}
	
	public Set<TradeItem> getItems() {
		return items;
	}
	
	public Integer getTradeId() {
		return tradeId;
	}

	public void setTradeId(int tradeId) {
		this.tradeId = tradeId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public Date getTradeTime() {
		return tradeTime;
	}

	public void setTradeTime(Date tradeTime) {
		this.tradeTime = tradeTime;
	}
	
	@Override
	public String toString() {
		return "Trade [tradeId=" + tradeId + ", tradeTime=" + tradeTime
				+ ", userId=" + userId + "]";
	}
	
}
