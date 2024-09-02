<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<script src="https://code.jquery.com/jquery-3.7.1.min.js" integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo=" crossorigin="anonymous"></script>
<script type="text/javascript">
     function addForm()
     {
    	 locatin.herf="user/addForm";
     }
      
      
    function logout()
    {
    	
    	
	      $.ajax
	      ({
	    	  url:'user/logout',
	    
	    	  method:'get',
	      	  cache:false,
	      	  dataType:'json',
	      	  success:function(res)
	      	  {
	      		alert(res.ok ? '로그아웃 성공' : '로그아웃 실패');
	      		if(res.ok) location.href ="/user";
	 
	      	  },
	      	  error:function(xhr,status,err)
	      	  {
	      		  alert('에러:'+err);
	      	  }
	      	  
	      });
	}
    
    function listForm()
    {
    	location.herf="user/listForm";
    }
</script> 
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
<%= msg %>
<button type="button" onClick="addForm();" value="add"/>사용자 정보추가</button>
     <a href="user/listForm" >
        <button type="button">사용자 목록보기</button>
    </a>
<button type="button" onClick="logout();" value="로그아웃"/>로그아웃</button>
</body>
</html>

