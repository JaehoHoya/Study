package com.sku.web.updown;

import java.sql.Date;
import java.util.List;



public class UploadVO {
	
 	private int unum ; 
	private String title;
	private String writer; 
	private Date rdate;
	private String content;
	private int fileCount;
	
	private List<AttachVO> attList ;
	
	public List<AttachVO> getAttList() {
		return attList;
	}

	public void setAttList(List<AttachVO> attachList) {
		this.attList = attachList;
	}

	
	public int getUnum() {
		return unum;
	}
	public void setUnum(int unum) {
		this.unum = unum;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public Date getRdate() {
		return rdate;
	}
	public void setRdate(Date rdate) {
		this.rdate = rdate;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
		
	}

	public int getFileCount() {
		return fileCount;
	}

	public void setFileCount(int fileCount) {
		this.fileCount = fileCount;
	}
	
	
	

	
	
	
}
