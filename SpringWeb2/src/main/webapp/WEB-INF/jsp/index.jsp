<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>index.jsp</title>
</head>
<body>
<h1>index.jsp</h1>
<%
    String msg = "Hello World"; // 한글도 가능
%>
${greeting}
<c:set var="g" value="<%=msg%>"/>
<div>${g}</div>

</body>
</html>