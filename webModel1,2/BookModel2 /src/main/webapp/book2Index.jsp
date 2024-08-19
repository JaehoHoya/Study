<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>모델 2 도서구매 사이트(장바구니)</title>

<script src="https://code.jquery.com/jquery-3.7.1.min.js" integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo=" crossorigin="anonymous"></script>
<script type="text/javascript">
function sendPost(){
	$.ajax({
		url:'book2',
		method:"POST",
		cache:false,
		
	})
}
function sendPut(){
	$.ajax({
		url:'book2',
		method:"PUT",
		cache:false,
		
	})
}
function sendDelete(){
	$.ajax({
		url:'book2',
		method:"DELETE",
		cache:false,
		
	})
}
</script>

</head>
<body>
	<h3>도서 구매 사이트</h3>
	<li><a href="book2/list">도서 목록</a>
	<p>
	<button type="button" onClick="sendPost();">POST</button>
	<button type="button" onClick="sendPut();">PUT</button>
	<button type="button" onClick="sendDelete();">DELETE</button>
</body>
</html>