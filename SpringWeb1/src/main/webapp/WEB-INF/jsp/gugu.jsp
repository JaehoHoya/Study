<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>구구단 보기 </title>
</head>
<body>
<h3> 구구단 보기 </h3>

<c:forEach var="list" items="${list}">
<div>${list}</div>

</c:forEach>
</body>
</html>