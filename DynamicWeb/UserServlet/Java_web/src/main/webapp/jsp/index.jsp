<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Hello~</title>
<script src="https://code.jquery.com/jquery-3.7.1.min.js" integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo=" crossorigin="anonymous"></script>
<script type="text/javascript">

		function logout()
		{
			
			
			$.ajax({ 
				url:'user', 
				method:'post',
				cache:false,
				data:{'cmd':'logout'},
				dataType:'json', //'text', 
				success:function(res)
				{
					//alert(res);
					
					alert(res.logout ? '로그아웃' : '로그아웃 실패');
					if(res.logout) location.href="user?cmd=loginForm";
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
<main>
   <nav>
      <ul>
         <li><a href="user">HOME</a>
         <li><a href="user?cmd=loginForm">LOGIN</a>
         <li><a href="user?cmd=userList">LIST</a>
         <li><a href="user?cmd=addForm">ADD</a>
         <li><a href="javascript:logout();">LOGOUT</a>
      </ul>
   </nav>
   <div><img src="./img/iu.jpg"></div>
   
</main>
</body>
</html>



<style type="text/css">
    img{width:25%}
    main div:last-child {display:flex; justify-content:center; margin-top:4em;}
    li{display: inline-block; width:5em;}
</style>
