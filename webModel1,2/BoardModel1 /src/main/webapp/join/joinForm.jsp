<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>join up Form</title>
<script src="https://code.jquery.com/jquery-3.7.1.min.js" integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo=" crossorigin="anonymous"></script>
<script type="text/javascript">
$(function(){  // jequery의 ready 이벤트 헨들러 함수 
	alert('DOM 오브젝트 준비');
	$('#pwd').blur(function(){
		
		checkPwd();
	});

});

function checkDuplicate()
    {
	
	
	
      alert('아이디 중복 검사');
      console.log('아이디 중복 검사');
      
      var ser=$('#joinForm').serialize();
      $.ajax({
    	  url:'checkDuplicate.jsp',
    	  method:'post',
      	  cache:false,
      	  data:ser,
      	  dataType:'json',
      	  success:function(res){
      		  alert(res.ok? '유요한 아이디':'사용할 수 없는 아이디');
      		  if(!res.ok) $('#uid').val('');
      	  },
      	  error:function(xhr,status,err){
      		  alert('에러:'+err);
      	  }
      	  
      });
	}
	
function checkPwd()
{
  
  var pwd = $('#pwd').val();
  
  var specialCharCnt =(pwd.match(/[^a-zA-Z0-9]/g) || []).length; // ^소문자 대문자 0~9 제외하고  
  console.log('특수문자자의 수: '+specialCharCnt);
  
 
  /*
  if(res==null)console.log(0);
  else console.log(res.length);
  */
  var lowCharCnt = (pwd.match(/[a-z]/g) || []).length;
  console.log('소문자의 수 : '+lowCharCnt);
  
  var upperCharCnt = (pwd.match(/[A-Z]/g) || []).length;
  console.log('대문자의 수 : '+upperCharCnt);
  
  if(pwd.len>=8 && specialChaCnt>=2 && lowChaCnt>=2 && upperChaCnt){
	  console.log('유효한 암호');
  }else
  {
	  alert("무효한 암호");
  }
  /*
  //대문자 
  var res=pwd.match(/[A-Z]/g); 
  console.log('매치'+res);
  if(res==null)console.log(0);
  else console.log(res.length);
  */
}

function onHistoryInput(){
	var hist=$('#history').val();
	//console.log("취미경력: "+hist);
	$('#histOut').val(hist);
}
</script>
</head>
<body>
<main>
<h3>회원가입</h3>
<form id="joinForm" action="formProc.jsp" method="get">
		<div>
		    <label for="uid">아이디</label>
		    <input type="text" name="uid" id="uid" >
		    <button type="button" onclick="checkDuplicate();">중복검사</button>
		</div>
		<div>
		    <label for="pwd">암호</label>
		    <input type="password" name="pwd" id="pwd" >
		    <button type="button" onclick="checkPwd();">유효성 검사</button>
		</div>
		<div>
		<label for="gender" > 성별</label>
		남<input type="radio" name= "gender" id="gender" value="m">
		여<input type="radio" name= "gender" id="gender" value="f">
		</div>
		<label for="gender" > 취미</label>
		게임<input type="checkbox" name= "hobby" value="game">
		독서<input type="checkbox" name= "hobby" value="readding">
		여행<input type="checkbox" name= "hobby" value="travle">
		낚시<input type="checkbox" name= "hobby" value="fishing">
		그림<input type="checkbox" name= "hobby" value="drawing">
		<div>
			<label for="history">취미 경력</label>
			<input type="range" name="history" min="0" max="100" id="history" oninput="onHistoryInput();">
			<output id="histOut"></output>
		</div>
		<div>
		 <label for="age">나이</label>
		 <input type="number" name="agegam" id="age">
		</div>
		<div>
		 	<label for="birth">생년월일</label>
		 	<input type="date" name="birth" id="birth">
		</div>
		<div>
		   <label for="introl">간단한 개인소개</label>
		   <textarea rows="5" cols="10" placeholder="여기에 입력...." name="intro" id="intro"></textarea>
		</div>
	 	<div>
	 		<button type="reset">취소</button>
	 		<button type="submit">저장</button>
	 	</div>
</form> 
</main>
</body>
</html>
<!-- 이렇게 넘어감  -->
<!--http://localhost:8080/BoardModel1/join/formProc.jsp?uid=&pwd=&hobby=game&hobby=readding&hobby=travle&hobby=fishing&hobby=drawing  -->  