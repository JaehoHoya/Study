<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>파일 리스트</title>
<script src="https://code.jquery.com/jquery-3.7.1.min.js" integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo=" crossorigin="anonymous"></script>
<script type="text/javascript">
</script>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<style>
    body {
        font-family: Arial, sans-serif;
        background-color: #f4f4f4;
        margin: 20px;
    }

    h3 {
        text-align: center;
        color: #333;
    }

    table {
        width: 100%;
        border-collapse: collapse;
        margin: 20px 0;
    }

    thead {
        background-color: #4CAF50;
        color: white;
    }

    th, td {
        border: 1px solid #ddd;
        padding: 8px;
        text-align: left;
    }

    th {
        background-color: #4CAF50;
        color: white;
    }

    tbody tr:nth-child(even) {
        background-color: #f2f2f2;
    }

    tbody tr:hover {
        background-color: #ddd;
    }

    a {
        text-decoration: none;
        color: #4CAF50;
    }

    a:hover {
        text-decoration: underline;
    }
</style>
</head>
<body>
<h3> 업로드한 파일 리스트</h3>

<table>
    <thead>
        <tr>
            <th>번호</th>
            <th>제목</th>
            <th>작성자</th>
            <th>작성일</th>
            <th>파일 개수</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach var="a" items="${list}">
            <tr>
                <td><a href="./detail/${a.getUnum()}">${a.getUnum()}</a></td>
                <td>${a.title}</td>
                <td>${a.writer}</td>
                <td>${a.rdate}</td>
                <td>${a.fileCount}개</td>
            </tr>
        </c:forEach>
    </tbody>
</table>

</body>
</html>
