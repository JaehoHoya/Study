<%@ page language="java" contentType="application/json; charset=UTF-8" 
   pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="jsObj" class="org.json.simple.JSONObject"/>

<jsp:useBean id="book" class="com.test.sku.Book" scope="page"/>
<jsp:setProperty name="book" property="*"/>

<jsp:useBean id="dao" class="com.test.sku.BookDAO"/>
<c:set var="b" value="${dao.getBook(book)}"/>

<jsp:useBean id="cart" class="com.test.sku.BookCart" scope="session"/>
<c:set var="updated" value="${cart.updateQty(b)}"/>
<c:set var="total" value="${cart.getTotalPrice()}"/>
${jsObj.put("updated",updated)}
${jsObj.toJSONString()}