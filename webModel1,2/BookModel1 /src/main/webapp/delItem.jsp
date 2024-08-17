<%@ page language="java" contentType="application/json; charset=UTF-8" 
   pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="cart" class="com.test.sku.BookCart" scope="session"/>
<c:set var="deleted" value="${cart.deleteItems(param.delitems)}"/>
<jsp:useBean id="jsObj" class="org.json.simple.JSONObject"/>
${jsObj.put("deleted", deleted)}
${jsObj.toJSONString()}