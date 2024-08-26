
<div align="center">
<h2> 수업 내용 정리 </h2>
 소개글 수정 예정 
</div>

## 폴더명
  - [1차 테스트](#1차-테스트)👉 자바 기초를 배우고 간단한 테스트 프로그램 작성 
  - [웹 모델](#웹-모델)👉 모델 1,2 적용 
  -
  -
  -
  -
  -
  -
  -
## 1차 테스트  

### 설명
  - 내용 작성 예정 
<table>
  <tr>
    <td><img src="https://github.com/user-attachments/assets/4de2a676-0288-41ea-8e38-14987e0985c5" alt="image" width="800" height="200" ></td>
    <td><img src="https://github.com/user-attachments/assets/ae18bbc7-4311-480c-bc72-0bddba4af816" alt="image"width="800"height="200" ></td>
    <td><img src="https://github.com/user-attachments/assets/bf02ef37-5ebc-493a-96d4-6ba2a8732506" alt="image"width="800"height="200" ></td>
  </tr>
  <tr>
    <td>파일 업로드 목록 검색</td>
    <td>수정 삭제</td>
    <td>직렬화 파일</td>
  </tr>
</table>

## 웹 모델

### 설명 
REST방식으로 개발해보았는가.
  - 내용 작성 예정
  - HTTP 요청 연습 ex)
  - 모델 2
```java
String url= request.getRequestURI();   // /Myproject/book/detail/3 
String token[] =url.split("/")         // [,Myproject,bool,detail,3];
int len =token.length;
path= Arrays.copyOfRange(token,3,len); // [detail,3]
```

`RESTful 요청  : +URL,( GET, POST,PUT, DELETE )`  

`GET: 서버 -> 클라이언트  가져오기 위한 요청 (서버로 전달하는 데이터량은 상대적으로 적다 ) READ`

`POST: 클라이언트 -> 서버로 데이터를 보내기 위한 요청 (서버로 양이 많은 데이터가 전달) CREATE`

`PUT: UPDATE`
`DELETE : DELETE `
->웹에 대한 CRUD

### 도서구매 웹 모델1
<table>
  <tr>
    <td><img src="https://github.com/user-attachments/assets/c81fa749-e273-4ceb-9250-0f20bd2e132d" alt="image" width="800" height="200" ></td>
    <td><img src="https://github.com/user-attachments/assets/0a272f58-020d-4ffa-a155-3b3aa118e2fb" alt="image"width="800"height="200" ></td>
    <td><img src="https://github.com/user-attachments/assets/b0d70713-f02f-417b-a908-857586f56665" alt="image"width="800"height="200" ></td>
  </tr>
  <tr>
    <td>도서 목록 페이지</td>
    <td>상세 페이지</td>
    <td>장바구니 페이지</td>
  </tr>
</table>

### 게시물 작성 웹 모델1

<table>
  <tr>
    <td><img src="https://github.com/user-attachments/assets/9e73c236-58da-4a4e-ba9c-1cc177c5f08b" alt="image" width="800" height="200" ></td>
    <td><img src="https://github.com/user-attachments/assets/85fd0844-31b6-4273-bbcf-e70855fe814c" alt="image"width="800"height="200" ></td>
    <td><img src="https://github.com/user-attachments/assets/6960f265-34de-466e-95db-9c3a03c53d5b" alt="image"width="800"height="200" ></td>
  </tr>
  <tr>
    <td>로그인</td>
    <td>게시글 목록</td>
    <td>게시물 입력</td>
  </tr>
</table>

<table>
  <tr>
    <td><img src="https://github.com/user-attachments/assets/934e952f-2651-4b2e-8800-32dd1e48ca42" alt="image" width="800" height="200" ></td>
    <td><img src="https://github.com/user-attachments/assets/91fae720-e30f-45ce-9893-7d6738ff54a4" alt="image"width="800"height="200" ></td>
  </tr>
  <tr>
    <td>게시물 상세</td>
    <td>게시물 수정</td>
  </tr>
</table>
### 스프링
`@SessionAttributes("uid") : 클래스 위에 선언  세션속성 uid라는 변수가 있으면  자동으로 세션에 저장함  `
		`@SessionAttribute(name="uid" ,requried=false) : 메소드 파라미터   -sessionGetAttribute  값이 없을때 null 에러 -> requried=false 에러 안남   로그인 검사 `
	`	@SessionStatus : 메소드 파라미터 -sessionSetAttribute  로그아웃할때  세션에 저장되어 있는 상태 정보 `


 `@Autowired` 
 `@Repository`
 `@Controller`
`@RequestMapping`
`@GetMapping("")`
`@PostMapping("/login")`
`@ResponseBody 	`	
`@ModelAttribute`
`@RequestParam `
`dependency`



<details>
  <summary>첫번째토글</summary>
  <div markdown="1">
	  <details>
  <summary>첫번째토글</summary>
  <!-- 내용 -->
</details>
    <ul>
      <li>1</li>
      <li>2</li>
    </ul>
  </div>
</details>
이름 없는 함수 ,람다 표현식 -메소드 한개일때만     함수형 인터페이스만 람다로   함수형 인터페이스: 메소드 한개 ,




--계정 생성
CREATE USER fitizen identified BY admin;

--데이터베이스 접속 권한 
GRANT CREATE SESSION TO fitizen;

--테이블 생성 권한 
GRANT CREATE TABLE TO fitizen ;

--테이블 스페이스 사용 권한 
GRANT UNLIMITED TABLESPACE TO fitizen ;

--인덱스 생성권한 
GRANT CREATE ANY INDEX TO fitizen ;
GRANT DROP ANY INDEX TO fitizen ;

--DBA 권한 생성 
GRANT CONNECT ,resource ,dba TO fitizen ;

