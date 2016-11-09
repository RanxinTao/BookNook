package com.bookstore.entity;

public class TradeItem {
	
	private int tradeItemId;
	private Book book; //book that associates with this tradeItem
	private int quantity;
	private int bookId;
	private int tradeId;
	
	public TradeItem() {}
	
	public TradeItem(int tradeItemId, int bookId, int quantity, int tradeId) {
		this.tradeItemId = tradeItemId;
		this.bookId = bookId;
		this.quantity = quantity;
		this.tradeId = tradeId;
	}
	
	public void setBook(Book book) {
		this.book = book;
	}
	
	public Book getBook() {
		return book;
	}
	
	public Integer getTradeItemId() {
		return tradeItemId;
	}

	public void setTradeItemId(Integer tradeItemId) {
		this.tradeItemId = tradeItemId;
	}

	public Integer getBookId() {
		return bookId;
	}

	public void setBookId(Integer bookId) {
		this.bookId = bookId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Integer getTradeId() {
		return tradeId;
	}

	public void setTradeId(Integer tradeId) {
		this.tradeId = tradeId;
	}

	@Override
	public String toString() {
		return "TradeItem [tradeItemId=" + tradeItemId + ", quantity="
				+ quantity + ", bookId=" + bookId + "]";
	}
	
}
