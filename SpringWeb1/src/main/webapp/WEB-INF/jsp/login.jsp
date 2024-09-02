<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<script src="https://code.jquery.com/jquery-3.7.1.min.js" integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo=" crossorigin="anonymous"></script>
<script type="text/javascript">
    function login()
    {
    	obj={};
    	 obj.username = $('#username').val();
    	 obj.password = $('#password').val();
    	
    	
	      $.ajax
	      ({
	    	  url:'user/login',
	    	  data:obj,
	    	  method:'post',
	      	  cache:false,
	      	  dataType:'json',
	      	  success:function(res)
	      	  {
	      		alert(res.ok ? '로그인 성공' : '로그인 실패');
	      		if(res.ok) location.href="./";
	      	  },
	      	  error:function(xhr,status,err)
	      	  {
	      		  alert('에러:'+err);
	      	  }
	      	  
	      });
	}
    
 
</script>
<html>
<head>
    <title>Login Page</title>
</head>
<body>
    <h2>Login</h2>
        <div>
            <label for="username">Username:</label>
            <input name="username" id="username"/>
        </div>
        <div>
            <label for="password">Password:</label>
            <input type="password" name="password" id="password"/>
        </div>
        <div>
           <button type="reset">취소</button>
           <button type="button" onClick="login();" value="로그인"/>로그인</button>
           
        </div>

</body>
</html>

<!--  로그인 폼    -요청 /user/login -->