<%@page import="com.test.model1board.Board"%>
<%@page import="com.test.model1board.BoardDAO"%>
<%@page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:useBean id="dao" class="com.test.model1board.BoardDAO"/>
<%
   // 게시글 리스트를 가져온다
   List<Board> boardList = dao.getListBoard();
%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="boardList" value="<%=boardList%>"/>
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
   <h3>게시글 리스트(JSTL ,EL)</h3>
   
   
   
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
          <c:forEach var="a" items="${boardList}" varStatus="status">
         <tr>
         
            <td><a href="boardDetail.jsp?bnum=${a.bnum}">${a.bnum}</a></td>
            <td>${a.title}</td>
            <td>${a.author}</td>
            <td>${a.rdate}</td>
            <td>${a.hit}</td>
            <td>${a.contents}</td>
         </tr>
   
      </tbody>
      
   </table>
   	    
   	    
   	    
   	  </c:forEach>
   
   <h3>게시글 리스트(기본)</h3>
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
         <% 
         // 리스트를 반복하여 각 게시글을 출력
         for (Board board : boardList) {
         %>
         <tr>
         
            <td><a href="boardDetail.jsp?bnum=<%= board.getBnum() %>"><%= board.getBnum() %></a></td>
            <td><%= board.getTitle() %></td>
            <td><%= board.getAuthor() %></td>
            <td><%= board.getRdate() %></td>
            <td><%= board.getHit() %></td>
            <td><%= board.getContents() %></td>
         </tr>
         <% 
         } 
         %>
      </tbody>
      
   </table>
</main>
</body>
</html>
