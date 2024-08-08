<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.test.sku.servlet.User" %>
	
<%
      User user =(User)request.getAttribute("detail");
%> 
    

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>이용자 상세정보</title>
<script src="https://code.jquery.com/jquery-3.7.1.min.js" integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo=" crossorigin="anonymous"></script>
<script type="text/javascript">
	function goList(){
		location.href="user?cmd=userList";
	}
	function deleteUser()
	{
		if(!confirm('정말로 삭제할거야?')) return ;
		let uid= $('#uid').val();
		console.log(uid);
		let obj={};
	    obj.uid='<%=user.getUid()%>';
	   	obj.cmd='delete';
		
		
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
				alert(res.deleted?'삭제완료':'삭제실패');
				if((!res.deleted)&& res.cause!="")
				{
					alert(res.cause);
					location.href="user?cmd=loginForm";
				}
				
				
				if(res.deleted) location.href="user?cmd=userList";
				
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
  <h3><%=user.getUid()%>의 상세 정보</h3>
<table>
<tr><th>아이디</th><th><%=user.getUid()%></th></tr>
<tr><th>암호</th><th><%=user.getPwd()%></th></tr>
</table>
<p>

<a href="user?cmd=editPwd&uid=<%=user.getUid()%>"><button>수정</button></a>
  <!-- <a href="user?cmd=deleteUser&uid=<%=user.getUid()%>"><button>삭제</button></a> -->
  
<a href="javascript:deleteUser();"><button>삭제</button></a>
<a href="user?cmd=userList"><button>사용자 목록으로</button></a>
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
   h3 {text-align:center; text-decoration: underline;}
</style>
<script type="text/javascript">
   function goList() {
      location.href = "user?cmd=list";
   }
</script>
