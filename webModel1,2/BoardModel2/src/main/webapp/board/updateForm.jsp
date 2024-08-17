<%@page import="java.util.*"%>
<%@page import="com.test.sku.Board"%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 수정하기</title>
<script src="https://code.jquery.com/jquery-3.7.1.min.js" integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo=" crossorigin="anonymous"></script>
<script type="text/javascript">


function update(){
	
	     
	var ser =$('#add').serialize();
	 
	      $.ajax({
	    	  url:'/BoardModel2/board',
	    	  method:'post',
	      	  cache:false,
	      	  data:ser,
	      	  dataType:'json',
	      	  success:function(res){
	      		  console.log(res)
	      		  alert(res.ok ? '성공':'실패');
	      		if(res.ok){
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
	
   <h3>게시글 수정하기</h3>
   
  <form id="add" method="post">
  <input type="hidden" id="cmd" name="cmd" value="update">
  <input type="hidden" id="bnum" name="bnum" value="${detail.bnum}">
   <table>		
      <tr><th>번호</th><td>${detail.bnum}</td></tr>
      <tr><th>제목</th><td>
	 <input type="text" name="title" id="title"></td></tr>
      <tr><th>작성자</th><td>${detail.author}</td></tr>
      <tr><th>작성일</th><td>${detail.rdate}</td></tr>
      <tr><th>조회수</th><td>${detail.hit}</td></tr>
      <tr><th>내용</th><td>
           <input type="text" name="contents" id="contents" row="5"></td></tr>
   </table>
   	 
   	   <button type="button" onclick="update();">저장</button>
   	    <a href="?cmd=list"> 취소</a>
   	</form>   
   	 
  
</main>
	</form>
</body>
</html>