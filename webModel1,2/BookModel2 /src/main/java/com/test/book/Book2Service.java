package com.test.book;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import org.json.simple.JSONObject;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class Book2Service {
	
	private HttpServletRequest request;
    private HttpServletResponse response;
    private String[] path;
    private String uri;
    private String servletPath;
    private String cmd;
    private String method;
    
    public Book2Service(HttpServletRequest request, HttpServletResponse response) 
    {
		this.request = request;
		this.response = response;
		
		uri = request.getRequestURI();
		path = uri.split("\\/");
		servletPath = request.getServletPath();
		
		if(path.length>3) cmd=path[3];	
		method= request.getMethod();
		
		HttpSession ss= request.getSession();
		if(ss.getAttribute("cart")==null) {
			ss.setAttribute("cart",new Book2Cart());
		}
		
	}

	public String process()
	{
		String viewPath =null; 
		if(cmd ==null) viewPath= "/book2Index.jsp";
		else if(cmd.equals("list") && method.equalsIgnoreCase("GET"))	
		{
			// 목록을 주세용
			Book2DAO dao= new Book2DAO();
			List<Book2> list= dao.getListBook();
			
			//영역 오브젝트에 넣기 
			request.setAttribute("list",list);
			return "/book2List.jsp";
			
		}
		else if(cmd.equals("detail") &&method.equalsIgnoreCase("GET"))
		{
			
			Book2DAO dao= new Book2DAO();
			int no = Integer.parseInt(path[4]);
			Book2 book =dao.getBook(no);
			
			request.setAttribute("book",book);
			return "/book2Detail.jsp";
			
		}
		else if(cmd.equals("addCart") && method.equalsIgnoreCase("GET"))
		{
			
			Book2DAO dao= new Book2DAO();
			int no = Integer.parseInt(path[4]);
			int qty=Integer.parseInt(path[5]);
			System.out.println("no:"+no+"qty"+qty);
			
			Book2 b =dao.getBook(new Book2(no,qty));
			
			Book2Cart cart = getUserCart();		
			
			
			
			boolean success=cart.add(b);

		   sendJSON("success",String.valueOf(success));
			
			
			
			
		}
		else if(cmd.equals("showCart") &&  method.equalsIgnoreCase("GET"))
			
		{
			
			return "/showCart.jsp";
		}
		else if(cmd.equals("updateQty") && method.equalsIgnoreCase("GET"))
		{
			
			Book2DAO dao= new Book2DAO();
			int no = Integer.parseInt(path[4]);
			int qty=Integer.parseInt(path[5]);
			System.out.println("수량 변경-no:"+no+"qty"+qty);
			
			Book2 b =dao.getBook(new Book2(no,qty));
			
			Book2Cart cart = getUserCart();		
			
			
			
			boolean updated=cart.updateQty(b);

		   sendJSON("updated",String.valueOf((updated)));
			
			
		}
	
		
		
	
		return viewPath;
	
	
	}
	
	
	
	
	
	
	
	
	
	
	
	
	private Book2Cart getUserCart() {
		HttpSession ss =request.getSession();
		Book2Cart cart=(Book2Cart)ss.getAttribute("cart");
		return cart;
	}
	
	
	
	 private void sendJSON(String key, String value) {
	        String json = String.format("{\"%s\":%s}", key, value);
	        try (PrintWriter out = response.getWriter()) {
	            out.print(json);
	            out.flush();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }

	    
	    private void sendJSON(JSONObject jsObj) {
	       
	        try (PrintWriter out = response.getWriter()) {
	            out.print(jsObj.toJSONString());
	            out.flush();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }


}
