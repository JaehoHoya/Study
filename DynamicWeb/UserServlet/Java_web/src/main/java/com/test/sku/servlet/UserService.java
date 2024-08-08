package com.test.sku.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONObject;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class UserService 
{
	private HttpServletRequest request;
	private HttpServletResponse response;
	
	
	public UserService(HttpServletRequest request, HttpServletResponse response) {
		this.request=request;
		this.response=response;
	}
	
	public String process()
	{
		// 요청이 오면 아이디와 패스워드가 존재 로그인 폼달라는 요청도 요청 

	      String cmd= request.getParameter("cmd");
	      String viewPath=null;
		  
		  
	      if(cmd==null) {
	          return "/jsp/index.jsp";
	       }

	      
	      if(cmd.equals("add") || cmd.equals("update") || cmd.equals("delete")) {
	          if(!loginCheck()) {
	             Map<String,Object> map = new HashMap<>();
	             map.put("added", false);
	             map.put("updated", false);
	             map.put("deleted", false);
	             map.put("cause", "로그인 이용자만 이용할 수 있는 기능입니다");
	             map.put("loginRequired", true);
	             sendJSON(map);
	             return null;
	          }
	       }

	      
		  if(cmd==null ||cmd.equals("loginForm")) 
		  {
			  viewPath="/jsp/loginForm.jsp";
		  }
		  
		  
		  
		  else if(cmd.equals("login"))
		  {
			  //로그인 절차를 수행 
			  String uid= request.getParameter("uid");
			  String pwd= request.getParameter("pwd");
			  
			  User user = new User(uid,pwd);
			  UserDAO dao =new UserDAO();
			  boolean ok= dao.login(user);
			  
			  // 로그인 통과한 이용자는 어떤페이지로 이용하든지 로그인 여부를 할 수 있도록 
			  // 영역 오브젝트에 로그인 성공 사실을 기억해 놓는다.
			  if(ok)
			  {
			  request.getSession().setAttribute("uid",uid);		  
			  }
			  sendJSON("ok",ok+"");
			 
		  }
		  else if(cmd.equals("detail"))
		  {
			  String uid= request.getParameter("uid");
			  UserDAO dao =new UserDAO();
			  User user=dao.detail(uid);
			  
				  request.setAttribute("detail",user);
				  viewPath="/jsp/userDetail.jsp";
			  
			 
			  
		  }
		  else if(cmd.equals("userList"))
		 {
			       UserDAO dao =new UserDAO();
			       List<User> list=dao.getList();
			       request.setAttribute("list",list);
			       viewPath="/jsp/userList.jsp";
		 }
		  else if(cmd.equals("editPwd"))
		  {
			   UserDAO dao =new UserDAO();
			   String uid= request.getParameter("uid");
			   User user=dao.detail(uid);
		       request.setAttribute("user",user);
			   viewPath="/jsp/editPwd.jsp";
		  }
		  else if(cmd.equals("update"))
		  {
			  String uid= request.getParameter("uid");
			  String pwd= request.getParameter("pwd");
			  UserDAO dao =new UserDAO();
			  boolean updated =dao.updatePwd(uid,pwd);
			
			  sendJSON("updated",updated+"");
		  }
		  else if(cmd.equals("delete"))
		 {
			  String uid= request.getParameter("uid");
			 
			  UserDAO dao =new UserDAO();
			  boolean deleted=dao.delete(uid);
			  sendJSON("deleted",deleted+"");
			 	 
		 }
		  else if(cmd.equals("addForm"))
		  {
			  
			  viewPath="/jsp/userInput.jsp";
		  }
		  else if(cmd.equals("add"))
		  {
			  
			  
			  String uid= request.getParameter("uid");
			  String pwd= request.getParameter("pwd");
			  
			  User newUser =new User(uid,pwd);
			  UserDAO dao = new UserDAO();
			  boolean added = dao.add(newUser);
			 
			  sendJSON("added",added+"");
			  
			  
		  }
		  else if(cmd.equals("logout"))
		  {
			  request.getSession().invalidate();
			  sendJSON("logout",true+"");
			  
		  }
		  
		  return viewPath;
	}
	
	
	
	
	private void sendJSON(String key,String value)
	{
		
		String json=String.format("{\"%s\":%s}",key,value);
		
		PrintWriter out =null;
		try {
			out = response.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}	
		  out.print(json);
		  out.flush();
	}
	
	
	private void sendJSON(Map<String,Object> map)
	{
		
		
		JSONObject jsObj= new JSONObject(map);
		 String js =jsObj.toJSONString();
		 try{
		 
			PrintWriter out=response.getWriter();
			out.print(js);
			out.flush();
		 }
		 catch(IOException e)
		 {
			 e.printStackTrace();
		 }
		
	}
	
	
	private boolean loginCheck() 
	{
		Object obj = request.getSession().getAttribute("uid");
		
		return obj!=null;
	}
	
	

}
