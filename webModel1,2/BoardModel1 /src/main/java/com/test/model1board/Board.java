package com.test.model1board;

public class Board {

	
	private int bnum;
	private String title;
	private String author;
	private String contents;
	private java.sql.Date rdate;
	private int hit;
	
	
	public Board() {};
	
	public Board(int bnum, String title, String author, String contents, java.sql.Date rdate, int hit) {
	    this.bnum = bnum;
	    this.title = title;
	    this.author = author;
	    this.contents = contents;
	    this.rdate = rdate;
	    this.hit = hit;
	}
	
	public int getBnum() {
		return bnum;
	}
	public void setBnum(int bnum) {
		this.bnum = bnum;
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
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	public java.sql.Date getRdate() {
		return rdate;
	}
	public void setRdate(java.sql.Date rdate) {
		this.rdate = rdate;
	}
	public int getHit() {
		return hit;
	}
	public void setHit(int hit) {
		this.hit = hit;
	}
	
	
	
	
	
}
