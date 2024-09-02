<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>사용자 목록</title>
</head>
<body>
<h3>사용자 목록</h3>

 
   
   
   	  <table>
      <thead>
         <tr>
            <th>아이디</th>
            <th>비번</th>
            
         </tr>
      </thead>
       <c:forEach var="u" items="${users}" varStatus="status">
      <tbody>
        
         <tr>
            <td>${u.username}</td>
            <td>${u.password}</td>
            
         </tr>
   
      </tbody>
      
   </table>
   
   	    
   	    
   	  </c:forEach>
</body>
</html>