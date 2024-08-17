package com.test.sku;

import java.sql.Date;

public class Book {
	
	private int no; 
	private String title;
	private String author;
	private String publisher; //발행자 
	private Date pubdate;
	private int page ;
	private int price ;
	private String cover ; //책표지? 
	private int qty;
	
	private int totalPrice;
	
	public int getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}
	public Book() {};
	public Book(int no, String title, String author, String publisher, Date pubdate, int page, int price,
			String cover) {
		
		setNo(no);
		setTitle(title);
		setAuthor(author);
		setPublisher(publisher);
		setPubdate(pubdate);
		setPage(page);
		setPrice(price);
		setCover(cover);
		
	}
	
	
	
	 public int getQty() {
		return qty;
	}
	public void setQty(int qty) {
		this.qty = qty;
	}
	public Book(int no) {
	      this.no = no;
	   }
	   
	   public boolean equals(Object obj) {
	      Book other = (Book) obj;
	      return this.no == other.no;
	   }
	   
	   
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getCover() {
		return cover;
	}
	public void setCover(String cover) {
		this.cover = cover;
	}
	public Date getPubdate() {
		return pubdate;
	}
	public void setPubdate(Date pubdate) {
		this.pubdate = pubdate;
	}
	
	
	
	
	

}
