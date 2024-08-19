<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script src="https://code.jquery.com/jquery-3.7.1.min.js" integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo=" crossorigin="anonymous"></script>
<script type="text/javascript">
  
    function changeQty(no){
    	
    			
    	
    	     if(!confirm('수량을 변경할거야?')) return; 
	      
    	      
    	      	var qty= $('#qty'+no).val();
    	     
	     
	      $.ajax({
	    	  url:'./updateQty/' + no + '/' + qty,
	    	  method:'get',
	      	  cache:false,
	      	 
	      	  dataType:'json',
	      	  success:function(res){
	   
	      		  
	      		  alert(res.addCart ? '변경 성공':'변경 성공');
	      		 	if(res.addCart) location.href='/showCart.jsp';
	      	  },
	      	  error:function(xhr,status,err){
	      		  alert('에러:'+err);
	      	  }
	      	  
	      });
		}
    
    function deleteCheck() {
    	   var arr = $('#cb:checked').map(function() {
    	      return $(this).val();
    	   });

    	   var strValue='';
    	   for(var i=0;i<arr.length;i++){
    	      strValue += (arr[i]+',');
    	   }
    	   
    	   
    	   
    	   var obj = {};
    	   obj.delitems = strValue;

    	   $.ajax({
    	      url:'./updateQty/' + no + '/' + qty,
    	      method:'get',
    	      cache:false,
    	      data:obj,
    	      dataType:'json',
    	      success:function(res){
    	         alert(res.deleted ? '삭제 성공':'실패');
    	         if(res.deleted) location.href="../../showCart.jsp";
    	      },
    	      error:function(xhr,status,err){
    	         alert('에러:' + err)
    	      }
    	   });
    	}

 
</script>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>장바구니 보기</title>
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
        margin-bottom: 2em;
    }

    thead th {
        background-color: #f9f9f9;
        text-align: left;
        border-bottom: 2px solid #ddd;
        padding: 0.75em;
        font-size: 1em;
        color: #333;
        width: 33.33%; /* 고정 너비 */
    }

    tbody td {
        padding: 0.75em;
        border-bottom: 1px solid #ddd;
        font-size: 0.9em;
        color: #666;
        width: 33.33%; /* 고정 너비 */
    }

    tbody tr:hover {
        background-color: #f1f1f1;
    }

    tbody td a {
        color: #007bff;
        text-decoration: none;
    }

    tbody td a:hover {
        text-decoration: underline;
    }

    /* Responsive table */
    @media (max-width: 768px) {
        table, thead, tbody, th, td, tr {
            display: block;
        }

        thead tr {
            position: absolute;
            top: -9999px;
            left: -9999px;
        }

        tr {
            border: 1px solid #ddd;
            margin-bottom: 0.5em;
        }

        td {
            border: none;
            position: relative;
            padding-left: 50%;
            text-align: left;
        }

        td::before {
            content: attr(data-label);
            position: absolute;
            left: 0;
            width: 45%;
            padding-left: 10px;
            font-weight: bold;
            white-space: nowrap;
        }
    }

    /* Button Styles */
    button {
        display: inline-block;
        padding: 0.75em 1.5em;
        font-size: 1em;
        color: #fff;
        background-color: #007bff;
        border: none;
        border-radius: 4px;
        cursor: pointer;
        text-align: center;
        text-decoration: none;
    }

    button:hover {
        background-color: #0056b3;
    }

    button:focus {
        outline: none;
    }

    /* Container for input and button */
    .input-button-container {
        display: flex;
        align-items: center;
    }

    .input-button-container input[type="number"] {
        width: 80px;
        margin-right: 10px;
    }
</style>
</head>
<body>
<main>
   <h3>장바구니 보기</h3><a href="bookList.jsp"><button>도서 목록으로 돌아가기</button> </a>
   <p>
   <table>
      <thead>
         <tr>
            <th>도서번호</th>
            <th>제목</th>
            <th>저자</th>
            <th>출판사</th>
            <th>출판일</th>
            <th>가격</th>
            <th>구매수량</th>
            <th><button onClick="deleteCheck();">삭제</button></th>
         </tr>
      </thead>
      <tbody>
         <c:forEach var="b" items="${cart.getItems()}">
            <tr>
               <td data-label="도서번호">${b.no}</td>
               <td data-label="제목">${b.title}</td>
               <td data-label="저자">${b.author}</td>
               <td data-label="출판사">${b.publisher}</td>
               <td data-label="출판일">${b.pubdate}</td>
               <td data-label="가격">${b.price}</td>
               <td data-label="구매수량">
                  ${b.qty}개
                   <div class="input-button-container">
                       <input type="number" name="qty"id="qty${b.no}" value="${b.qty}" />개
                       <button onClick="changeQty(${b.no});">변경</button>
                   
                   </div>
               </td>
               <td><input type="checkbox" name="cb" id="cb" value="${b.no}"></td>

            </tr>
         </c:forEach>
      </tbody>
   </table>
   <div><strong>합계:</strong> ${cart.getTotalPrice()} 원</div>
   <p>
   <button onClick="">전체 삭제</button>
   
</main>
</body>
</html>

