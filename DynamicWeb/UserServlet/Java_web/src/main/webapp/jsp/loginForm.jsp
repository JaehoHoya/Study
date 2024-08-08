<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title> 로그인 폼 </title>

<style type="text/css">
    #main{
          width:fit-content;    
          margin:0.5em auto;  
          padding:1em;
         } 
    form{border:1px solid black;padding:0.5em;}
    h3{text-align:center;}
    div:last-child{margin-top:0.3em; text-align:center;}
    label {display:inline-block; width:3em; } <!--폭 조절인 안되는 요소를 인라인 요소-->
</style>
<script src="https://code.jquery.com/jquery-3.7.1.min.js" integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo=" crossorigin="anonymous"></script>
<script type="text/javascript">

		function loginCheck()
		{
			//var uid=document.querySelector("#uid").value;   // Document Object Model (DOM 국제표준) 
			//var pwd=document.querySelector("#pwd").value;
			
			let cmd= $('#cmd').val(); // 표준코드: document.querySelector('#cmd').value
			let uid= $('#uid').val();
			let pwd= $('#pwd').val();

			if(uid==""||pwd=="")
			{
				alert("아이디 패스워드를 입력해주세요");
				return false;
			}
			
			
		
			let obj={}; // JSON 표현식
		    obj.cmd='login';
			obj.uid=uid;
			obj.pwd=pwd;
			
			// 비동기 자바스크립트 요청 -> 요청에 대한 응답을 자바스크립트 변수에 저장할 수 있다.(비동기전송하는 이유)
			$.ajax({ // 어떤 서버에 어떤 데이터를 전송하고 응답은 어떤 변수에 받겠다 
				url:'user', 
				method:'post',
				cache:false,
				data:obj,
				dataType:'json', //'text', 
				success:function(res)
				{
					//alert(res);
					
					alert(res.ok ? '로그인 성공' : '로그인 실패');
					if(res.ok) location.href="user?cmd=userList";
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

<div id ="main">
	 <h3>로그인</h3>
	 <form action="user" method="post" onsubmit="return loginCheck();">  <!-- 이용자 눈에 안보인다? -->
	 		
	 		<input type="hidden" name="cmd" id="cmd" value="login"/>
	 		
	 		<div>
	 		   <label for="uid">아이디</label>
	 		   <input type="text" name="uid" id="uid">
	 		</div>
	 		
	 		<div>
	 		   <label for="pwd">암호</label>
	 		   <input type="password" name="pwd" id="pwd"> <!--모든 태그에는 id 라는 속성  -->
	 		</div>
	 		
	 		<div>
	 			<button type="reset">취소</button>
	 			<button type="submit">로그인</button>  <!--post 방식으로 전송  -->
	 		</div>
	 
	 </form>

</div>

</body>
</html>