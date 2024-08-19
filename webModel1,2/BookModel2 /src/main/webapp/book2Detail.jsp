<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.*"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 

<script src="https://code.jquery.com/jquery-3.7.1.min.js" integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo=" crossorigin="anonymous"></script>
<script type="text/javascript">
    function goBack() {
        window.location.href ='../list';
    }
    
    
    function addCart()
    {
    	
    	     if(!confirm('장바구니에 담을거야?')) return; 
	      
    	     var qty = $('#qty').val();
    	
    	     //'../addCart/${book.no}/'+qty,
	     
	      $.ajax
	      ({
	    	  url:'../addCart/'+${book.no}+'/'+qty,
	    	  method:'get',
	      	  cache:false,
	      	  dataType:'json',
	      	  success:function(res)
	      	  {
	      		  console.log(res)
	      		  alert(res.success ? '담기 성공':'담기 성공');
	      		 
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
<title>책 상세 페이지</title>
<style type="text/css">
    body {
        font-family: Arial, sans-serif;
        background-color: #f4f4f4;
        margin: 0;
        padding: 0;
    }

    main {
        width: 80%;
        margin: 2em auto;
        padding: 2em;
        background-color: #ffffff;
        border-radius: 8px;
        box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
    }

    h3 {
        margin: 0 0 1em 0;
        padding-bottom: 0.5em;
        border-bottom: 2px solid #ddd;
        font-size: 1.8em;
        color: #333;
    }

    table {
        width: 100%;
        border-spacing: 0;
        border-collapse: collapse;
        margin-top: 1em;
    }

    table img {
        max-width: 200px;
        height: auto;
        display: block;
        margin: 0 auto 1em auto;
    }

    th, td {
        padding: 0.75em;
        border: 1px solid #ddd;
        text-align: left;
        font-size: 1em;
        color: #333;
    }

    th {
        background-color: #f9f9f9;
        font-weight: bold;
    }

    tr:nth-child(even) {
        background-color: #f1f1f1;
    }

    tr:hover {
        background-color: #e9e9e9;
    }

    button {
        display: inline-block;
        padding: 0.75em 1.5em;
        font-size: 1em;
        color: #fff;
        background-color: #007bff;
        border: none;
        border-radius: 4px;
        cursor: pointer;
        margin-top: 1em;
        text-align: center;
        text-decoration: none;
    }

    button:hover {
        background-color: #0056b3;
    }

    button:focus {
        outline: none;
    }
</style>
</head>
<body>
<main>
	
    
    	<div>
        <h3>책 상세 페이지</h3> 
        <button type="button" onclick="goBack()">뒤로가기</button>
        </div>
        <table>
            <tr>
                <td colspan="2">
                    <img src="../../bookImgs/${book.cover}" alt="${book.title} Cover"/>
                </td>
            </tr>
            <tr><th>도서 번호</th><td>${book.no}</td></tr>
            <tr><th>제목</th><td>${book.title}</td></tr>
            <tr><th>작성자</th><td>${book.author}</td></tr>
            <tr><th>발행자</th><td>${book.publisher}</td></tr>
            <tr><th>발행일</th><td>${book.pubdate}</td></tr>
            <tr><th>페이지 수</th><td>${book.page}</td></tr>
            <tr><th>가격</th><td>${book.price}</td></tr>
           
        </table>
      <p>
      <div><label>구매수량</label> <input type="number" name="qty" id="qty">개</div>
      <p>
        <a href="javascript:addCart();"><button>장바구니에 담기</button></a>
		<a href="../showCart"><button>장바구니 보기 </button></a>
    
</main>
</body>
</html>
