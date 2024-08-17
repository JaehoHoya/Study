<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 입력</title>
<script src="https://code.jquery.com/jquery-3.7.1.min.js" integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo=" crossorigin="anonymous"></script>
<script type="text/javascript">
function saveBoard(){
	{
		
	      if(!confirm('작성된 글을 저장하시겠습니까?'));
	      var ser =$('#addForm').serialize();
	     
	      $.ajax({
	    	  url:'addFormProc.jsp',
	    	  method:'post',
	      	  cache:false,
	      	  data:ser,
	      	  dataType:'json',
	      	  success:function(res){
	      		  alert(res.saved ? '작성 성공':'작성 실패');
	      		if(res.saved){
	      			location.href="boardDetail.jsp?bnum="+res.bnum;
	      		}
	      		 
	      	  },
	      	  error:function(xhr,status,err){
	      		  alert('에러:'+err);
	      	  }
	      	  
	      });
		}
}
</script>
</head>
<body>
<main>
<h3>게시물 입력</h3>
<form id="addForm" action="addFormProc.jsp" method="post">
<!-- 제목,내용, AJAX요청 -->
            <div>
	 		   <label for="title">제목</label>
	 		   <input type="text" name="title" id="title" value="Just Test">
	 		</div>
	 		
	 		<div>
	 		   <label for="contents">내용</label>
	 		   <input type="text" name="contents" id="contents" row="5"> <!--모든 태그에는 id 라는 속성  -->
	 		</div>
	 		
	 		<div>
	 			<button type="reset">취소</button>
	 			<button type="button" onClick="saveBoard();">저장</button>  <!--post 방식으로 전송  -->
	 		</div>
</form>
</main>
</body>
</html>


