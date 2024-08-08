<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.test.sku.servlet.User" %>

<%
    List<User> list = (List<User>)request.getAttribute("list");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

    <div id="main">
    <h3>사용자 목록</h3>
<table>
<tr><th>아이디</th><th>암호</th></tr>  <!-- 테이블의 한행 table row ,th: 테이블 헤더-->
<% 
   for(int i=0;i<list.size();i++){
		User u=list.get(i); %>
		<tr>
		<td><a href="user?cmd=detail&uid=<%=u.getUid()%>"><%=u.getUid()%></a></td>
		<td><%=u.getPwd()%></td>
		</tr>
		<%}
%>

</table>
<p>
<a href="user?cmd=addForm"><button>이용자 정보 추가</button></a>
</div>
	
</body>
</html>




 <style type="text/css">
 h3 {text-align:center;    
          text-decoration: underline; }
 #main{
          width:fit-content;    
          margin:1em auto;  
         } 
table {border:1px solid black; padding:10px; border-collapse: collapse; border-spacing:0;}
tr:first-child>th {background-color:#ddd; border-bottom:3px double black;}
th,td{border:1px solid black; padding:0.2em 1em;}
td>a {text-decoration:name; color:blue;}
tr:nth-child(odd) {background-color:#cde;}
</style>

