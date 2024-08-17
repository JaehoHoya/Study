<%@page import="com.test.sku.Board"%>

<%@page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%
   // 게시글 리스트를 가져온다
   List<Board> list = (List<Board>) request.getAttribute("list");
%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="boardList" value="<%=list%>"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 리스트</title>
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
<main>
   <h3>게시글 리스트</h3>
   
   <c:forEach var="a" items="${boardList}" varStatus="status">
   
   
   	  <table>
      <thead>
         <tr>
            <th>번호</th>
            <th>제목</th>
            <th>작성자</th>
            <th>작성일</th>
            <th>조회수</th>
            <th>내용</th>
         </tr>
      </thead>
      <tbody>
         
         <tr>
            <td><a href="?cmd=detail&bnum=${a.bnum}">${a.bnum}</a></td>
            <td>${a.title}</td>
            <td>${a.author}</td>
            <td>${a.rdate}</td>
            <td>${a.hit}</td>
            <td>${a.contents}</td>
         </tr>
   
      </tbody>
      
   </table>
   
   	    
   	    
   	  </c:forEach>
   	  
   <a href="?cmd=addForm"> <button>글 작성</button></a>
   
</main>
</body>
</html>
