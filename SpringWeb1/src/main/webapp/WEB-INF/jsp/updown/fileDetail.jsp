<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>파일 상세보기</title>
<script src="https://code.jquery.com/jquery-3.7.1.min.js" integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo=" crossorigin="anonymous"></script>
<script type="text/javascript">
function download(fname2){
	
	    
    $.ajax({
        url: '/files/download/'+${fnum},
        method: 'GET',
        success: function(data, status, xhr) {
            console.log('다운로드 성공');
        },
        error: function(jqXHR, textStatus, errorThrown) {
            alert('다운로드에 실패했습니다.');
        }
    });
}

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
        background-color: #ffffff;
        border-radius: 8px;
        overflow: hidden;
        box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
    }

    thead {
        background-color: #4CAF50;
        color: white;
    }

    th, td {
        border: 1px solid #ddd;
        padding: 10px;
        text-align: left;
        font-size: 14px; /* 글씨 크기 조정 */
    }

    th {
        background-color: #4CAF50;
        color: white;
    }

    tbody tr:nth-child(even) {
        background-color: #f9f9f9;
    }

    tbody tr:hover {
        background-color: #f1f1f1;
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
<h3>파일 상세보기</h3>

<table>
    <thead>
        <tr>
            <th>번호</th>
            <th>제목</th>
            <th>작성자</th>
            <th>작성일</th>
            <th>내용</th>
            <th>파일 목록</th>
        </tr>
    </thead>
    <tbody>
        <tr>
            <td>${d.getUnum()}</td>
            <td>${d.getTitle()}</td>
            <td>${d.getWriter()}</td>
            <td>${d.getRdate()}</td>
            <td>${d.getContent()}</td>
            <td>
                <c:forEach items="${d.attList}" var="file">
                    ${file.fname1}<button onClick="download('${file.fnum}');">다운</button> <br/>
                    <a href="./download/${file.fname2}">${a.getUnum()}</a>
                </c:forEach>
            </td>
        </tr>
    </tbody>
</table>
</body>
</html>
