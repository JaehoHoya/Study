<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.*"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>도서 목록</title>
<style type="text/css"> 
    body {
        font-family: Arial, sans-serif;
        background-color: #f4f4f4;
        margin: 0;
        padding: 0;
    }

    main {
        width: 80%;
        margin: 2em auto;
        padding: 1em;
        background-color: #ffffff;
        border-radius: 8px;
        box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
    }

    main > h3 {
        margin: 0 0 1em 0;
        padding-bottom: 0.5em;
        border-bottom: 2px solid #ddd;
        font-size: 1.5em;
        color: #333;
    }

    table {
        width: 100%;
        border-spacing: 0;
        border-collapse: collapse;
        margin-bottom: 2em;
    }

    thead th {
        background-color: #f9f9f9;
        text-align: left;
        border-bottom: 2px solid #ddd;
        padding: 0.75em;
        font-size: 1em;
        color: #333;
        width: 33.33%; /* 고정 너비 */
    }

    tbody td {
        padding: 0.75em;
        border-bottom: 1px solid #ddd;
        font-size: 0.9em;
        color: #666;
        width: 33.33%; /* 고정 너비 */
    }

    tbody tr:hover {
        background-color: #f1f1f1;
    }

    tbody td a {
        color: #007bff;
        text-decoration: none;
    }

    tbody td a:hover {
        text-decoration: underline;
    }

    /* Responsive table */
    @media (max-width: 768px) {
        table, thead, tbody, th, td, tr {
            display: block;
        }

        thead tr {
            position: absolute;
            top: -9999px;
            left: -9999px;
        }

        tr {
            border: 1px solid #ddd;
            margin-bottom: 0.5em;
        }

        td {
            border: none;
            position: relative;
            padding-left: 50%;
            text-align: left;
        }

        td::before {
            content: attr(data-label);
            position: absolute;
            left: 0;
            width: 45%;
            padding-left: 10px;
            font-weight: bold;
            white-space: nowrap;
        }
    }
</style>
</head>
<body>
<main>
   <h3>도서 목록</h3>
   <table>
      <thead>
         <tr>
            <th>도서 번호</th>
            <th>제목</th>
            <th>작성자</th>
         </tr>
      </thead>
      <tbody>
         <c:forEach var="book" items="${list}">
            <tr>
               <td data-label="번호">${book.no}</td>
               <td data-label="제목"><a href="detail/${book.no}">${book.title}</a></td>
               <td data-label="작성자">${book.author}</td>
            </tr>
         </c:forEach>
      </tbody>
   </table>
</main>
</body>
</html>
