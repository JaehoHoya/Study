package com.sku.web.updown;

public class AttachVO {

	private int fnum;
    private String fname1;
    private String fname2;
    private int unum ;
    private long fsize;
    
    public AttachVO() {};
    
    public AttachVO(String fname1, String fname2, long fsize) {
    	setFname1(fname1);
    	setFname2(fname2);
    	setFsize(fsize);
    };
    
	public int getFnum() {
		return fnum;
	}
	public void setFnum(int fnum) {
		this.fnum = fnum;
	}
	public String getFname1() {
		return fname1;
	}
	public void setFname1(String fname1) {
		this.fname1 = fname1;
	}
	public String getFname2() {
		return fname2;
	}
	public void setFname2(String fname2) {
		this.fname2 = fname2;
	}
	public int getUnum() {
		return unum;
	}
	public void setUnum(int unum) {
		this.unum = unum;
	}
	public long getFsize() {
		return fsize;
	}
	public void setFsize(long fsize) {
		this.fsize = fsize;
	} 
    
    
	
}
