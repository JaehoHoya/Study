CI / CD 
- Continuous integration ( 지속적인 통합 )
- Continuous Delivery( 지속적 전달 )
- Continuous Deployment( 지속적인 배포)

- 코드작성 > 깃허브에 통합 > 배포 > 실행
- java project > jar(패키징) > github > Webhook > AWS > jenkins 스크립트 실행 > github 복제 > 실행
- ,,,,,,,,, 가발자,,,,,,,,, github  ,,,,,,,,,,,,,, jenkins

<br>
통합 : GitHub 
운용 : AWS(Linux)

코드 통합과 운용 시스템에 배포하는 것을 자동화 
- Docker
- Jenkins

AWS 환경에 적응 하기 
- AWS에서 Nginx 웹서버 운용 

- AWS ES2 라는 인스턴스 생성, 설정 : 한대의 서버 컴퓨터
- OS : AMAZON Linux 2 , Ubuntu
  ----------------------------------------아래는 명령어로 위에는 클릭만으로
- 웹서버 설치 : Nginx
- 웹서버 실행 ,웹브라우저로 접속( public-ip)
- (전세계 공인 아이피 )
- EC2 인스턴스에 원격 접속하여 리눅스 명령어로 설정 -
- Telnet > SSH( PuTTy)
 



# aws 인스턴스 생성 과정 

- ec2 검색 : elastic Computing Cloud
- 사이드바 인스턴스 아래 인스턴스
- 인스턴스 시작
- os : 이미지 선택
- 사용자 이름 확인할것 . 컴표터에 접속할때 사용자이름을 써야함  우분투랑 리눅스 aws 이름이 다름 확인할것
- 인스턴스 유형( 하드웨어 ) 사양이 무엇인가
- 단순히 웹사이트 실행쇼 에는 t2.micro
- 키 페어   : 퍼블릭 ,프라이비 키

- 새 키페어 생성
-  이름(jh-aws-first-instance) ,유형(RSA), 프라이빗 키 파일 형식 ( .ppk)  컴퓨터 접속시 할때  즉 관리자
-  프라이비 키는 aws 퍼블릭 키가 다운됨 확장자 .ppk

- putty 다운로드(https://www.putty.org/)
<img width="790" alt="스크린샷 2024-12-06 오전 10 50 43" src="https://github.com/user-attachments/assets/c31343ce-72d2-4b4c-af71-6ee419c0d5d0">
- 푸티 arm 다운함 일단은 맥이라
- brew install putty ?
- 네트워크 설정 : 사설 내트워크 ,보안그룹 생성 ( 방화벽 ) ,SSH(관리용 포트 개방),위치 무관 다른거 할시에는 그 컴퓨터에서만 접근 됨
- https http포트 열기
- DB 하려면 편집 (네트워크 선택란에 있어) -보안그룹 규치 추가
- 잰킨스 8080 오라클 1521 -위치 무관
- 네트워크 : vpc (가상의 사설망 컴퓨터 만들어진거 선택됨: big-vpc-01)
- 서브넷: 이것도  ( 가상 사설망 안에 하위 네트워크)
- vpc 오류 -> vpc 검색 생성  주요기능 -vpc,서브넷, 라이팅 테이블 , 인터넷 게이트웨이
- Virtual Private Cloud 사이드바 클릭 ->vpc
- vpc 생성 -> vpc만 -> 이름 ->IPv4 CIDR 블록 :수동?->IPv4 CIDR: ABC클래스 사설망10.0.0.0/16 16으로하면 사설망안에 많은 컴퓨터를 듈수 있다?
- 서브넷 마스크가 16 
- vpc생성
  
```
VPC 생성
 - Virtual Private Cloud(가상 사설 네트워크)
 - 각자 Region(지역)을 정해서 생성

Internet Gateway 생성 
 - VPC 가 외부로 트랙픽을 전송하기 위한 관문
 - VPC에 연결

Subnet 생성
 - VPC 안에 내부 네트워크 생성

Routing Table 생성
 - 트래픽의 방향 설정
 - 내부 트래픽 : VPC 내부로 전달되는 트래픽
 - 외부 트래픽 : VPC를 벗어나 인터넷으로 전달되는 트래픽
 - 서브넷과 연결
- 라우티에서 대상 대상 컬럼이 있는데  트래픽의목적지가 10.0.0.0/16 내부?, 
- 라우팅 편집 ,

인스턴스 생성(웹서버)
 - VPC, Subnet 연결
 - Nginx 설치, 실행
 - 웹브라우저로 접속 테스트
 - 웹페이지 변경 및 적용/웹브라우저로 테스트

```

- 비젼 : 버지니아 북부 이게 뭔지 알것 

<img width="684" alt="스크린샷 2024-12-06 오후 1 20 15" src="https://github.com/user-attachments/assets/c6d24e40-7743-45b0-8017-8d207c4a42ff">

<img width="822" alt="스크린샷 2024-12-06 오후 1 23 08" src="https://github.com/user-attachments/assets/20a1dcb0-54b9-48e1-afc9-1a9ce1751c0c">
<img width="1430" alt="스크린샷 2024-12-06 오후 1 34 27" src="https://github.com/user-attachments/assets/109a2091-3148-4d6a-93dc-f9b3dd2888b1">
<img width="1656" alt="스크린샷 2024-12-06 오후 1 34 36" src="https://github.com/user-attachments/assets/a8918f22-0c7e-4dc9-9d14-ed5d0c973a37">
- 라우팅 편집 끝나면 서브넷 연결 편집 연결
- ec2 인스턴스 검색 인스턴스
<img width="840" alt="스크린샷 2024-12-06 오후 1 45 57" src="https://github.com/user-attachments/assets/fd589160-c37f-47ba-b0b3-7d96dcad45cf">


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

## Python 

1. 회원분류
  - 회원의 정보를 이용하여 유사도를 기준으로 몇개의 그룹으로 분류
  - 분류된 회원은 한 그룹 안에서 공통점을 찾고 아직 갖추지 못한 회원을 탐색
  - 탐색된 회원에게 판촉 메일을 보낸다(여행상품, 소설권유, 운동소개 등...)
  - 회원정보 부족시 ChatGPT를 통해 실제와 유사하게 생성하여 사용
  - Gower 유사도, DBSCAN(밀도기반 Clustering), RDBMS(SQL)

2. 행동분류
  - 행동은 시계열(Time Series) 데이터이므로 시계열 데이터에 특화된 LSTM 활용
  - Blender 애니메이션을 통해 유사한 비디오 생성
  - mp4 비디오로부터 Google Mediapipe를 통해 Landmark(관절좌표) 추출
  - Google Tensorflow, Google Mediapipe, Blender 3D

3. NLP(Natural Language Processing)
  - 텍스트 생성, 분류, 평점, 문법검사
  - LLM(Large Language Model)
  - Hugging Face에서 다운로드 > 로컬에서 Ollama를 이용하여 실행
  - Langchain 을 사용하여 LLM 사용시 chain, prompt engineering 지원됨

ANN ( Artificial Neural Network ) : 인공 신경망 
-  에러가 최소인점 을 찾는다 기울기는 0 
f(x) =x^2 -4x +3  x: 는 우리가 준 데이터  학습용 데이터( ex : 10만개 데이이터)
 -4 : 계수, 비중, 가중치 (rate)
  3 : 높이 , bias 

  y= 2x +3 
  실제값: x=3 ,y=5  (3월인데 주가가 5 ) 
  예축값: x=3 일때 y= 9    손실 발생? 
  y= 5가 되기 위해   2 ,3 (파라미터 : 컴퓨터가 바꿀 수 있는 값)을 바꾼다  가중치와 bias 


##  Spring Web   
    -AI server에 접속 
    -httpClient  서버이면서 클라이언트 역할을 한다 

AI server(Fast API ) 


RAG (Retrieval Augmented Generation )
-Retriecal : 관련 도메인 문서에서 검색 
- Generation : 검색된 내용을 생성 모데레 전달하여 강화된 답변 생성

Vector Store  :Embedding 방법으로 벡터 생성 저장/검색 
Vector Store  :파일이나 캐시 서버에 저장하고 필요시 호출 
로컬 언어모델을 사용해본경험 랭체인 올라마 그 파일에 llm , gpt reg 서비스  redis 

챗봇은 서버 불필요 
FastAPI 불필요 REST FULL API
java, javascript 에서 접속 가능 

FastAPI 
- Web Server(AI)
- Recommend System
- ChatGPT RAG 
- GAN/ LSTM Model
- 추천 , LSTM 같은경우는 서버가 필요 

-실습 
-- local , Anaconda 
-- activate pytorch_env 환경 
-- Tensorflow Matplotlib 설치 
-- Sine 파 곡선을 추정할 수 있는 회귀 모델을 Tensorflow으로 작성 
-- 학습 / 학습 성능 차트로 확인  
-- sampleReg.keras 파일에 저장  
-- loaded_model을 사용하여 predict() 테스트 
-- FastAPI 웹에 배포 
-- FAstAPI , app.py 에  이 모델을 서비스할 수 있는 함수 선언 ( async def getReg(xvalue :Regreq(클래스)): 
-- java 프로그램을 실행하여  getReg 를 호출하여  그 결과를 화면에 표시해보기 
-- getReg()가  호출 되면 .....
-- sampleReg.keras 모델 로드 
-- pred = loadded_model.predict( 이용자가 전할한 값) 
-- return pred 
-- 웹브라우저에서 http://localhost:8000/reg/3.141592 으로 전달했을때  0 에 근사한 값이 표시되면 성공 
우리가 사용했던 가상환경 설치된 모듈 을 다 기억해야 다른 컴퓨터에서도 돌릴수 있다는 점을 참고할 것. 즉 환경을 똑같이 맞춰야 한다는 점 
우리는 레그 서비스를 만들기 위해  fastapi를 쓰는거임 ㅇㅋ? ㅇㅖ지가 없으니깐 나중에 면접질문 
cnn  : 이미지분류, 회귀 
lstm : 시계열 분석  분류 회귀 
gan  : 적대적 생성     
## GAN 
discriminator 판별자 
generative(생성) ,Adversarity(적대적) 
Network(신경망) 
-시계열 데이터를 기준으로 판별할 수 있어야 하므로 	LSTM으로 구성하는 것이 바람직 
-생성자는 시계열 데이터를 생성할 수 있어야 한다 

Time Series(시계열) 
-Lstm (long short Term Memmory) 
-분류: Sequence ( 특정 시점 구간 내에서 발생하는 데이터의 집합) 
-회귀: Sequence 내의 데이터의 집합을 숫자로 산출한다 

챗봇은 지피티 vs 올라마(라마3 ,mistral 7B ,Gemma 7B)
너무 느리다 


## 오류
Thymeleaf 데이터를 JavaScript에서 JSON 형식으로 인식하려면 ${} 안에서 th:inline="javascript"를 사용해야 합니다

트랜잭션 사용해 봤나요?
어떤 식으로 사용했나요?
문제가 있는 경우 어떻게 해결했나요?
dirty read 상태가 뭔지 설명해 보세요.
