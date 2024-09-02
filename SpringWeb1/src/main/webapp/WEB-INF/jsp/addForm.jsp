<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script src="https://code.jquery.com/jquery-3.7.1.min.js" integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo=" crossorigin="anonymous"></script>
<script type="text/javascript">
     
      
    function add()
    {
    	
     obj={};
   	 obj.username = $('#username').val();
   	 obj.password = $('#password').val();
   	
    	
	      $.ajax
	      ({
	    	  url:'./add',
	    
	    	  method:'post',
	      	  cache:false,
	      	  data:obj,
	      	  dataType:'json',
	      	  success:function(res)
	      	  {
	      		alert(res.ok ? '등록 성공' : '등록 실패');
	      		if(res.ok) location.href ="../";
	 
	      	  },
	      	  error:function(xhr,status,err)
	      	  {
	      		  alert('에러:'+err);
	      	  }
	      	  
	      });
	}
    
</script> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>사용자 정보 추가</title>
</head>
<body>
<h3>사용자 정보추가</h3>
	<form>
        <div>
            <label for="username">추가 아이디:</label>
            <input name="username" id="username"/>
        </div>
        <div>
            <label for="password">추가 비밀번호:</label>
            <input type="password" name="password" id="password"/>
        </div>
        <div>
           <button type="reset">취소</button>
   </form>
           <button type="button" onClick="add();" value="추가"/>추가</button>
           
       </div>
</body>
</html>