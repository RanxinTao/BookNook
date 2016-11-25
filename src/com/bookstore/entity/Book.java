package com.bookstore.entity;

import java.sql.Date;

public class Book {
	
	private int id;
	private String author;
	private String title;
	private float price;
	private Date publishingDate;
	private int salesAmount;
	private int stockNumber;
	private String remark;
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getAuthor() {
		return author;
	}
	
	public void setAuthor(String author) {
		this.author = author;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public float getPrice() {
		return price;
	}
	
	public void setPrice(float price) {
		this.price = price;
	}
	
	public Date getPublishingDate() {
		return publishingDate;
	}
	
	public void setPublishingDate(Date publishingDate) {
		this.publishingDate = publishingDate;
	}
	
	public int getSalesAmount() {
		return salesAmount;
	}
	
	public void setSalesAmount(int salesAmount) {
		this.salesAmount = salesAmount;
	}
	
	public int getStockNumber() {
		return stockNumber;
	}
	
	public void setStockNumber(int stockNumber) {
		this.stockNumber = stockNumber;
	}
	
	public String getRemark() {
		return remark;
	}
	
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	@Override
	public String toString() {
		return "Book [id=" + id + ", author=" + author + ", title=" + title
				+ ", price=" + price + ", publishingDate=" + publishingDate
				+ ", salesAmount=" + salesAmount + ", stockNumber="
				+ stockNumber + ", remark=" + remark + "]\n\n";
	}
	
}
