package com.test.sku.servlet;

public class User {
	
	private String uid ;
	private String pwd;
	
	
	User(){};
	
	public User(String uid) 
	{
		setUid(uid);
	}
	
   

	@Override
    public String toString() {
        return "아이디: "+uid+"\t패스워드: "+pwd;
    }

	
	User(String uid, String pwd)
	{
		this.uid=uid;
		this.pwd=pwd;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	
	
	
}
