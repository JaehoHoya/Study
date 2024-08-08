<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.7.1.min.js" integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo=" crossorigin="anonymous"></script>
<script type="text/javascript">

	function addUser()
	{
		
      
		
		
		
		let cmd= $('#cmd').val(); // 표준코드: document.querySelector('#cmd').value
		let uid= $('#uid').val();
		let pwd= $('#pwd').val();
		
		if(uid==""||pwd=="")
		{
			alert("아이디 패스워드를 입력해주세요");
			return false;
		}
		
		
		
		
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
				alert(res.added?'등록 성공':'등록 실패');
				if((!res.added)&& res.cause!="")
				{
					alert(res.cause);
					location.href="user?cmd=loginForm";
				}
				if(res.added) location.href="user?cmd=detail&uid="+uid;
				
			},
			error:function(xhr,status,err)
			{
				alert('에러:'+err);	
			}
			
		}); 
		
			return false;  // 있고 없고 차이 모르겟ㅇ,
	}
	
	
</script>
</head>
<body>
	
	<div id ="main">
	 <h3>이용자 정보 추가 </h3>
	 <form action="user" method="post" onsubmit="return addUser();">
	 		
	 		<input type="hidden" name="cmd" id="cmd" value="add"/>
	 		
	 		<div>
	 		   <label for="uid">아이디</label>
	 		   <input type="text" name="uid" id="uid">
	 		</div>
	 		
	 		<div>
	 		   <label for="pwd">암호</label>
	 		   <input type="password" name="pwd" id="pwd">
	 		</div>
	 		
	 		<div>
	 		    <button type="submit">등록</button>
	 
	 			<button type="reset">취소</button>
	 			
	 		</div>
	 
	 </form>

</div>
</body>
</html>

<!-- <button type="button" on Click="addUser();">등록</button> 

.....<form id addForm.......한번에 전부 가져오기위해 form 에 id로 직렬화로 가져옴 


	 function addUser()
	 {
	 	var ser=$('#addForm').serialize();  // 
	 	$.ajax({}
	 
	 
	 }






-->
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