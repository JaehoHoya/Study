<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script src="https://code.jquery.com/jquery-3.7.1.min.js" integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo=" crossorigin="anonymous"></script>
<script type="text/javascript">
    function calculate()
    {
    	var a = $('#a').val();
    	var b = $('#b').val();
	      $.ajax
	      ({
	    	  url:'calculate/'+a+'/'+b,
	    	  method:'get',
	      	  cache:false,
	      	  dataType:'json',
	      	  success:function(res)
	      	  {
	      			console.log(result);
	      			$('#result').val(res.result);
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
<title>덧셈 계산기</title>
</head>
<body>

<h3>덧셈 계산기</h3>
<div>
<input type="text" id="a" name="a">
+
<input type="text" id="b" name="b">
=<input type="text" id="result" readonly/>
</div>
<button onClick ="calculate();">계산</button>
</body>
</html>


