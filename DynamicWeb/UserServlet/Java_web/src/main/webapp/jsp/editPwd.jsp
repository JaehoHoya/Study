<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page import="java.util.List" %>
<%@ page import="com.test.sku.servlet.User" %>

<%
   User user =(User)request.getAttribute("user");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.7.1.min.js" integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo=" crossorigin="anonymous"></script>
<script type="text/javascript">

	// 함수 이름이 없네  함수 자체를 함수한테 주는거라 이름이 없다? 
	// 이름 없는 함수 
	// 아래 코드는 jQuery함수
	$(function() {
		// 커리를 실헹할 준비가 되면 돌아감 
		// 함수 호출 
		  console.log("jQuery ready");
	     //alert('jQuery ready');
	   });		
	/*
	function formCheck(){
	
		var pwd=document.querySelector("#pwd").value;
		
		if(pwd=="")
		{
			alert("패스워드를 입력해주세요");
			return false;
		}
		
		return true;
		
	}
	*/
	function updatePwd()
	{
		let cmd= $('#cmd').val(); // 표준코드: document.querySelector('#cmd').value
		let uid= $('#uid').val();
		let pwd= $('#pwd').val();
		
		let obj={}; // JSON 표현식
	    obj.cmd=cmd;
		obj.uid=uid;
		obj.pwd=pwd;
		
		// 비동기 자바스크립트 요청 -> 요청에 대한 응답을 자바스크립트 변수에 저장할 수 있다.(비동기전송하는 이유)
		$.ajax({ // 어떤 서버에 어떤 데이터를 전송하고 응답은 어떤 변수에 받겠다 
			url: 'user', 
			method:'post',
			cache: false, // 메모리에 담지마라 , 요청할때 새롭게  기억하지마라 
			data:obj,
			dataType:'json', //'text', 
			success:function(res)
			{
				//alert(res);
				alert(res.updated?'변경성공':'변경실패');
				if((!res.updated)&& res.cause!="")
				{
					alert(res.cause);
					location.href="user?cmd=loginForm";
				}
				
			},
			error:function(xhr,status,err)
			{
				alert('에러:'+err);	
			}
			
		}); 
		
		
		return false;
	}
	
	
	
	
	
</script>
</head>
<body>
<div id="main">
  <h3><%=user.getUid()%>의 비밀번호 변경</h3>
  
<form action="user" method="post" onsubmit="return updatePwd();">

<input type="hidden" name="cmd" id="cmd"value="update"/>

<table>

<tr><th>아이디</th><th><%=user.getUid()%></th></tr>

<tr><th>암호</th><th>
               <input type="hidden" name="uid" id="uid" value="<%=user.getUid()%>"/>
	 		   <input type="password" name="pwd" id="pwd"> <!--모든 태그에는 id 라는 속성  -->
	 		
	 		</th></tr>

</table>
<p>
<button type="submit">저장</button>
</form>
<a href="user?cmd=userList"><button>취소</button></a>


</div>
</body>
</html>








<style type="text/css">
   table {border:1px solid black; padding:10px; border-collapse:collapse; 
      border-spacing: 0; margin:0.2em auto;
   }
   th:first-child { background-color:#ddd; border-right:3px double black;}
   th,td {border:1px solid black; padding: 0.2em 1em;}
   td>a { text-decoration:none; color:blue; }
   #main {width:fit-content; margin:1em auto;}
   
    h3{text-align:center;}
   

</style>
