package com.test.book2;

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
