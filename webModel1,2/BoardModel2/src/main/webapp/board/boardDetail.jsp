<%@page import="java.util.*"%>
<%@page import="com.test.sku.Board"%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 상세보기</title>
<script src="https://code.jquery.com/jquery-3.7.1.min.js" integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo=" crossorigin="anonymous"></script>
<script type="text/javascript">


function deleteBoard(){
	
	     
		 
	      if(!confirm('작성된 글을 삭제하시겠습니까?'));
	      var obj = {
	  	        cmd: 'delete',
	  	        bnum:'${board.bnum}'
	              }
	     
	      $.ajax({
	    	  url:'/BoardModel2/board',
	    	  method:'post',
	      	  cache:false,
	      	  data:obj,
	      	  dataType:'json',
	      	  success:function(res){
	      		  alert(res.deleted ? '삭제 성공':'삭제 실패');
	      		if(res.deleted){
	      			location.href="/BoardModel2/board?cmd=list";
	      		}
	      		 
	      	  },
	      	  error:function(xhr,status,err){
	      		  alert('에러:'+err);
	      	  }
	      	  
	      });
		
}
	
</script>
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
	<form id= form>
   <h3>게시글 상세보기</h3>
   <table>		
      <tr><th>번호</th><td>${detail.bnum}</td></tr>
      <tr><th>제목</th><td>${detail.title}</td></tr>
      <tr><th>작성자</th><td>${detail.author}</td></tr>
      <tr><th>작성일</th><td>${detail.rdate}</td></tr>
      <tr><th>조회수</th><td>${detail.hit}</td></tr>
      <tr><th>내용</th><td>${detail.contents}</td></tr>
   </table>
   	 
   	   <button type="button" onclick="deleteBoard();">삭제</button>
   	   <a href="board?cmd=updateForm&bnum=${detail.bnum}">수정</a>
   	   
</main>
	</form>
</body>
</html>