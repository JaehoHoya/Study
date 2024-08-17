<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<script src="https://code.jquery.com/jquery-3.7.1.min.js" integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo=" crossorigin="anonymous"></script>
<script type="text/javascript">
function logout() {
    $.ajax({
        url: '/BoardModel2/board',
        method: 'post',
        cache: false,
        data: {cmd: 'logout'},
        dataType: 'json',
        success: function(res) {
            alert(res.logout ? '로그아웃 성공' : '로그아웃 실패');
            if(logout)location.href="/BoardModel2/board";
        },
        error: function(xhr, status, err) {
            alert('에러:' + err);
        }
    });
}


</script>
<meta charset="UTF-8">
<title>모델 2 게시판</title>
</head>
<body>
<main>
<h3>JSP개발방법론 model 2을 사용한 게시판 프로젝트</h3>
<ul>
	<li><a href="board?cmd=loginForm">로그인</a>
	 <button type="button" onclick="logout();">로그아웃</button>
</ul>
</main>
</body>
</html>

