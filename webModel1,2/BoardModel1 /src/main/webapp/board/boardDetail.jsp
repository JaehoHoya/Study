<%@page import="java.util.Arrays"%>
<%@page import="com.test.model1board.Board"%>
<%@page import="com.test.join.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:useBean id="dao" class="com.test.model1board.BoardDAO"/>
<%
   int bnum = Integer.parseInt(request.getParameter("bnum"));
   Board b = dao.getBoard(bnum);
%>
<%@ page import="java.util.*"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
	
	request.setAttribute("board",b);
	pageContext.setAttribute("today",new Date()); // 이상해. jstl 쓰자 
	Date today= new Date();
	int price =123455321;
	
	List<String> list=(List<String>)Arrays.asList("가","나","다");
	List<User> users=new ArrayList<>();
	users.add(new User("hi","jaeho"));
	
	Map<String,User> map =new HashMap<>();
	map.put("smith",new User("smith","smithpwd") );
	map.put("jaeho",new User("jaeho","jaehpwd") );
	
%>
<c:set var="price" value="<%=price%>" />
<c:set var="date" value="<%=today%>" scope="page"/> <!-- page 영역에 들어감  -->
<c:set var="date" value="<%=today%>" scope="page"/>
<c:set var="str" value="<%=list%>"/>
<c:set var="users" value="<%=users%>"/>
<c:set var="umap" value="<%=map%>"/>  <!-- jstl map루프가 가능하다?  -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 상세보기</title>
<script src="https://code.jquery.com/jquery-3.7.1.min.js" integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo=" crossorigin="anonymous"></script>
<script type="text/javascript">
function updateBoard(){
	{
		
	      if(!confirm('작성된 글을 저장하시겠습니까?'));
	      var ser =$('#addForm').serialize();
	     
	      $.ajax({
	    	  url:'addFormProc.jsp',
	    	  method:'post',
	      	  cache:false,
	      	  data:ser,
	      	  dataType:'json',
	      	  success:function(res){
	      		  alert(res.saved ? '작성 성공':'작성 실패');
	      		if(res.saved){
	      			location.href="boardDetail.jsp?bnum="+res.bnum;
	      		}
	      		 
	      	  },
	      	  error:function(xhr,status,err){
	      		  alert('에러:'+err);
	      	  }
	      	  
	      });
		}
	function delteBoard(){
		{
			
		      if(!confirm('작성된 글을 삭제하시겠습니까?'));
		      var ser =$('#Form').serialize();
		     
		      $.ajax({
		    	  url:'addFormProc.jsp',
		    	  method:'post',
		      	  cache:false,
		      	  data:ser,
		      	  dataType:'json',
		      	  success:function(res){
		      		  alert(res.saved ? '작성 성공':'작성 실패');
		      		if(res.saved){
		      			location.href="boardDetail.jsp?bnum="+res.bnum;
		      		}
		      		 
		      	  },
		      	  error:function(xhr,status,err){
		      		  alert('에러:'+err);
		      	  }
		      	  
		      });
			}
}
</script>
<style type="text/css">
main { width:fit-content; margin:0.5em auto; }
main>h3 { width:fit-content; margin:0.2em auto; border-bottom:3px double black;}
table { border-spacing: 0; border-collapse: collapse; border:1px solid black;}
th {background-color:#eef; text-align:right; border-right:3px double black;}
th,td { padding:0.2em 0.5em; border-bottom:1px dashed black; }
tr:last-child>td { width:20em; height:5em; overflow: auto;}
</style>
</head>
<body>
<c:forEach var="item" items="${umap}">
<div>아이디: ${item.key},암호:${item.value.pwd} </div>
</c:forEach>


<main>
	<form id= form>
   <h3>게시글 상세보기</h3>
   <table>		
   	  오늘 날짜:${today}
   	  <p>
   	  오늘 날짜 포맷:<fmt:formatDate  value="${date}" pattern="yyyy-MM-dd"/>
   	  <fmt:formatDate var="date"  value="${board.rdate}" pattern="yyyy-MM-dd"/>
   	  <p>
   	  가격:${price}
   	  <p>
   	  가격 포맷:<fmt:formatNumber value="${price}" type="currency" currencySymbol="₩"/>
   	  <p>
   	  <%  for(String s:list){%>
   		  <%=s %>
   	  <%} 
   	  
   	  %>
   	  <p>
   	  <c:forEach var="s" items="${str}" varStatus="status">
   	    <div>${status.index+1}.${s}</div>
   	  </c:forEach>
      <tr><th>번호</th><td>${board.bnum}</td></tr>
      <tr><th>제목</th><td>${board.title}</td></tr>
      <tr><th>작성자</th><td>${board.author}</td></tr>
      <tr><th>작성일</th><td>${date}</td></tr>
      <tr><th>조회수</th><td>${board.hit}</td></tr>
      <tr><th>내용</th><td>${board.contents}</td></tr>
   </table>
   	  <button onClick="updateBoard();">수정</button>
   	  <button onClick="deleteBoard();">삭제</button>
</main>
	</form>
</body>
</html>