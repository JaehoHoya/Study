<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:useBean id="user" class="com.test.join.User" scope="session"/>
<jsp:setProperty name="user" property="*"/>
<jsp:useBean id="dao" class="com.test.join.UserDAO" scope="session"/>
<%
boolean ok = dao.login(user);
session.setAttribute("uid", user.getUid());
%>
{"ok":<%=ok%>}