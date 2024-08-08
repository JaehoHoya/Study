package com.test.sku.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/user")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		
	  request.setCharacterEncoding("utf-8"); //화면에 안깨지게 
	  response.setContentType("text/html;charset=utf-8");
	  
	  String viewPath=new UserService(request,response).process();
	  
	  
	 if(viewPath!=null) 
	 {
	   getServletContext().getRequestDispatcher(viewPath).forward(request, response);
	 }
	  
	  
	  
	  
	}

}
// 서블릿 서버에서 돌아가는 작은 프로글램 http 요청에 의해 수행되는 자바 클래스 요청이 오면 무조건 돌아감 톰캣이 호출 톰캣은 웹서버 역할 
/*
 * 
 * Servlet, CRUD 
 * -Create : html 폼에서 데이터가 Servlet으로 -> DAO -> users 저장 
 * -Read   : Html에서 요청 -> Servlet ->DAO -> users 
 * -Update : html 폼 -> 새 데이터 입력 ->Servlet ->DAO -> 수정 
 *         수정:수정(요청)암호수정용 폼 >아이디는 보여주고  패스워드 비워둔다 >새 암호 입력 저장(요청)을 누르면 반영 
 *         1.수정버튼 누르면 수정폼 출력 
 *         2.저장 버튼 누르면 > 해당 이용자의 암호가 서버에서 변경됨 
 *   	   3.성공/실패 메세지 (수정폼에서)
 * 
 * 
 * 이용자 정보 입력기능 
 * -> [이용자 정보추가]-> 입력폼 ->[저장]->성공/실패 ->성공시 상세정보
 * -목록보기 화면에 버튼추가 -> userInputjsp -> ajax요청 -> 성공/실패 > userDetail.jsps
 * service레이어 도입 (User service 클래스)
 * 로그인 이용자만 데이터 변경 가능하게 
 * 
 * java Web Scope(영역 오브젝트)
 * 
 * -이용자가 속하게 되는 논리적인 영역  
 * ,page 영역: 한페이지(서블릿)안의 영역 
 * -request 영역 : 이용자가 다른 jsp, servlet으로 forward될 떄 원래 요청된 페이지와 forward된 페이지 
 * -session 영역 : 이용자가 지나가는 모든 jsp,servlet 영역 
 * -application 영역:  사이트에 있는 모든 jsp,servlet영역 
 * 
 * 영역 오브젝트의 공통 메소드 
 * obj.setAttribute("key",value); // 영역 오브젝트에 key,value를 저장 
 * obj.getAttribute("key")   // 영역 오브젝트에 저장된 key를 사용하여 value를 구한다 
 * 
 * 세션 개념알것 . 한사람당 한개씩  세션 한 사이트에 100명 100개의 세션 오브젝트가 만들어져 있음 
 * 
 * pet 관리 시스템 종합 실습 
 * 
 * -Delete : html에서 삭제 요청 -> servlet -> DAO->삭제 
 * 
 * <a href="user?cmd=detail&uid=smith">smith</a> 
 * 
 * <a href="user?cmd=detail&uid=<%=u.getUid()%>"><%=u.getUid()%></a>
 * */

/*
<div id="main">
<% 
   for(int i=0;i<list.size();i++){
		User u=list.get(i); %>
		<div><%=u.getUid()%></div>
		<%}
	   %>
</div>
 */


/*
 <h2>사용자 목록</h2>
    <% if (list != null) { 
        for (User user : list) { %>
            <p><%= user %></p>
    <% } } %>
	
 */

// !!!!!!!!!!!!!!!!!!!!!!
// AJAX  비동기 요청방식 응답도 자바스크립트 변수로 받을 수 있고
// 화면을 갱신을 할거냐 마냐는 개발자 영역 
//jQuery CDN 
// 3.x minified
/*
 * <script src="https://code.jquery.com/jquery-3.7.1.min.js" integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo=" crossorigin="anonymous"></script>
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * */



