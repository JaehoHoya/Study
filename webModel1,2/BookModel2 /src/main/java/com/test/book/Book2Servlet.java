package com.test.book;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;



@WebServlet("/book2/*")  // book으로 시작하는 모든 경로는 서블릿이 받겠다 
public class Book2Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		String viewPath = new Book2Service(request, response).process();
		
		if(viewPath != null) {
			getServletContext().getRequestDispatcher(viewPath).forward(request, response);
		}
	}

}













/*
protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

 
	String param= request.getParameter("param");
	System.out.println("service()/"+request.getMethod()+"/"+param);  
	String uri =request.getRequestURI();
	System.out.println("uri:"+uri); 
	String[] token=uri.split("\\/");
	System.out.println("token:"+Arrays.toString(token));  
	
	String ctx= request.getContextPath();
	System.out.println("context:"+ctx); 
	
	 
	 service()/GET/null
     uri:/BookModel2/book2/
     token:[, BookModel2, book2]

     service()/GET/null
     uri:/BookModel2/book2
     token:[, BookModel2, book2]
	 
	  
	//서블릿 이름으로 끝나는 요청인 경우에는 book2Index.jsp가 표시되도록 한다.
	String servletPath=request.getServletPath();  // -> /book2
	if(servletPath.endsWith(token[token.length-1]))  // 서블릿 이름으로 끝난다면.
	{
		String viewPath= "/boo2Index.jsp";
		getServletContext().getRequestDispatcher(viewPath).forward(request,response);
		
	}
	
	
	




protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	System.out.println("doGet()");
	
	String param= request.getParameter("param");
	System.out.println("service()/"+request.getMethod()+"/"+param);  
	String uri =request.getRequestURI();
	System.out.println("uri:"+uri); 
	String[] token=uri.split("\\/");
	System.out.println("token:"+Arrays.toString(token));  
	
	String ctx= request.getContextPath();
	System.out.println("context:"+ctx); 
	
	
	String servletPath=request.getServletPath();  // -> /book2
	if(servletPath.endsWith(token[token.length-1]))  // 서블릿 이름으로 끝난다면.
	{
		String viewPath= "/book2Index.jsp";
		getServletContext().getRequestDispatcher(viewPath).forward(request,response);
		
	}
	response.getWriter().append("Served at: ").append(request.getContextPath());
}


protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	System.out.println("doPost()");
	doGet(request, response);
}


protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	System.out.println("doPut()");
}


protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	// TODO Auto-generated method stub
	System.out.println("doDelete()");
}

}

*/