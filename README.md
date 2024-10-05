
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
  - [2차 평가](#2차-평가) 👉 웹 2차 평가 
  -
  -[오류] (#오류)
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

타임 리프는 표준태그에  반복분을 적용 가능 ->태그가 반복됨  C foreach 해도 블록이  생성되지 않음 반복됨에 따라 tr이 다수개 생성되지 않음 
iBatis -> MyBatis 특징:  복잡한 쿼리에 특화? 유연성
- 자동으로 DAO 클래스 생성(boilerplate code 제거) 
- interfacce(메소드 선언)
- XML(sql 문장을 적는다) Mapper xml  Map이란 key와 value와 같이 연결의 의미
- 실제 DB코드를 개발자가 작성하지 않고 MyBatis가 인터페이스를 구현하여,sql을 포함한 코드를 생성  메소드안에 들어갈 sql 코드를 만들어줌 
- 메서드와 sql을 분리
- 분리 장점 : sql분리되어 선언- sql 관리가 쉬워진다 단점: 2개를 연결하면서 사용해야한다는 점 즉, 연결 설정을 할 필요가 있다
- ex) public interface EmpMapper {
- 		public List<Emp> getList();
- }
- EmpMapper.xml
  class test implements EmpMapper (
  			public List<Emp> getList()
			//MyBatis가 코드를 구현함
  			//Xml에 퍼함된 SQL을 자바코드에 포함하여 DB에 질의함 
  )

JPA(java Persistent API ) 
-ORM(object Relational Mapping)
-JPARepository 인터페이스에 메소드 선언 
-findAll(): select*from board; 
-save(): insert into board values(?,?,?) 저장,업데이트 
-deleteById(id)
delete(Entity)
deleteAll()
delete(ids)

-부서 번호로 검색해서 목록을 가져오고자 한다면... 
List<Emp> findByDeptno(20);      =select *From epm where emp =20 ;
findByDeptnoAndJob(20,'Clerk') 이렇게 하면 sql문장 만들어짐 
-간단한 CRUD 기능을 포함하고 JPA가 DAO 클래스를 생성 
CRUD 만 편리함 간단한것만 
- 생산성
  QueryDSL: 복잡한것도 괜찮다.
@SessionAttributes
@SessionAttribute
SessionStatus 
  JPA가 포함하고 있는 메소드
  Java Persistence API
  CRUD 와 같은 간한한 Query 를 쉽게 자동으로 생성하여 사용가능

Spring Security  
-인증(Authentication),인가(Authorization) 
JWT:서버로드/발란싱 상태로 여러 대일 경우,쿠키에 이용자 정보 저장(클라이언트)  
쿠키는 http 프로토콜을 지키는    

## 2차평가


게시판 프로젝트 평가 요구사항
 - 글쓰기 화면에서 다중 첨부파일 가능
 - 특정 첨부파일 다운로드 기능
 - 글쓰기, 수정, 삭제, 검색, 목록보기
 - 목록보기 화면에는 첨부파일의 갯수만 표기
 - 목록보기 화면에는 페이지 Navigation 가능
 - 게시글 상세보기 화면에서 첨부파일 다운로드 가능, 첨부삭제 및 새 업로드 기능
 - 글쓰기 후에 성공/실패 메시지 표시 후 상세보기로 바로 이어지게 함
 - 글수정은 원래 작성자만 수정가능하게 함
 - 글 삭제는 관리자, 작성자가 삭제할 수 있게 함
 - 서버측에서 데이터가 변경되는 기능에는 반드시 성공/실패 메시지가 표시되어야 함

기능 목록
 - 글쓰기, 성공/실패 메시지, 상세보기로 이동
 - 첨부파일 선택, 업로드, 파일정보 저장
 - 글쓰기는 첨부파일 저장도 포함되므로 Transaction 처리 필수
 - 상세보기, 수정버튼, 삭제버튼, 목록보기버튼
 - 수정기능, 편집모드, 수정저장, 성공/실패 메시지, 상세보기
 - 삭제기능, 이용자 의도 확인, 삭제, 성공/실패 메시지, 목록보기
 - 검색기능(작성자로 검색, 제목으로 검색), 검색 목록 표시
 - 목록보기, 한 화면에 5개 아이템, 하단에 페이지 네비게이션,
 - 페이지 네비게이션은 5개, 클릭시 해당 페이지로 이동, 현재 페이지 링크는 적색으로 표시
 - 검색기능, 카테고리 선택(작성자, 제목)/키워드 입력, 검색, 검색된 목록 표시

시스템
 - Spring boot 3, MVC 패턴(Controller, Service, Mapper, VO)
 - Thymeleaf 
 - MyBatis, Oracle 12c
 - DB Tables : Board, Attach
 - Board(bid, title, author, contents, rdate, hit)
 - Attach(fid, bid, fname, fsize, downloads)
 - Attach 에는 Foreign Key 설정

프런트엔드
 - 목록에는 table 태그 사용, 화면 중앙에 위치
 - 목록의 각 행은 배경을 번갈아 다르게 설정
 - jQuery, AJAX 등을 적절히 활용




## 오류


트랜잭션 사용해 봤나요?
어떤 식으로 사용했나요?
문제가 있는 경우 어떻게 해결했나요?
dirty read 상태가 뭔지 설명해 보세요.
