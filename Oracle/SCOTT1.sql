SELECT *FROM emp;

SELECT * FROM emp;
-- CRUD (Create ,Read, Update, Delete)

CREATE TABLE emp2 AS SELECT * FROM emp; // 복사 
DESC emp2; // 자료형
/* 문자열: VARCHAR2  가변문자열 최대 10 바이트 
   DATE : 날짜 
   NUMBER(7,2) :최대 7자인데 소수점 이하 2까지 
*/
--READ
SELECT * FROM emp2;
--20 부서 사람 가져오기  조건을 준다는것은 행에 대한 조건 
SELECT * FROM emp2 WHERE deptno=20;
SELECT * FROM emp2 WHERE deptno=20 ORDER BY sal DESC;
--한행 추가 ,CREATE
//INSERT INTO emp2 () VALUES ();
INSERT INTO emp2 (empno,ename,job,mgr,hiredate,sal)
VALUES (7777,'재호','체육',7902,'2002-11-16',3030);
--UPDATE
UPDATE emp2 SET deptno=10 WHERE empno=7777;
--DELTE 
DELETE FROM emp2 WHERE ename='재호';

-- commit : 
commit; 
-- 11번 사원 정보를 삭제해보세요 
DELETE FROM emp2 WHERE  empno=7902;
ROLLBACK;

--sub-Query
--smith 와 같은 부서에 근무하는 사람들의 정보를 표시해보세요 
SELECT deptno FROM emp2 WHERE ename='SMITH';
--위 값을 하나의 표현식으로 
SELECT *FROM emp2 WHERE 
deptno=(SELECT deptno FROM emp2 WHERE ename='SMITH');

--WARD와 같은 상급자를 둔 직원들의 이름, mgr를 표시
SELECT *FROM emp2;
SELECT ename,mgr FROM emp2 WHERE  mgr =7698;
SELECT ename,mgr FROM emp2 WHERE  mgr=(SELECT mgr FROM emp2 WHERE ename='WARD');

-- 전체 사원의 이름과 급여를 표시하되 급여 내림차순으로 표시해보세요 
-- ORDER BY sla DESC
SELECT ename,sal FROM emp2  ORDER BY sal DESC;

SELECT * FROM emp2 WHERE sal >=1600; // 테이블을 나타내는 표현식 ,한행도 아닌 한컬럼도 아닌 
// 위에 중에서 직무가 매니저인 사람들 출력 위에거를 테이블로 보고  작성
SELECT * FROM (SELECT * FROM emp2 WHERE sal >=1600)WHERE JOB='MANAGER'; -- Inline View 

--Relational Database System(RDBMS) 관계형 데이타베이스 
--JOIN(cross ,Equi, Outer  Join)

--정규형 (1,2,3 정규형):Nomalization
--사원정보(emp,dept 어떤 사원이 어떤부서인지...부서 번호만 알뿐.)

--SMITH 사원의 이름과 부서번호, 부서명을 표시해보세요.
SELECT ename,e.deptno,dname --FROM emp,dept cross 조인 쓸모 없음.
FROM emp  e JOIN dept  d --INER JOIN :명령 조건 안에 들어오는 것을 출력해라
ON e.deptno=d.deptno; -- AS 생략 가능 

--특정 사원의 이름과 부서번호 부서명을 표시하세요 
SELECT ename,e.deptno,dname 
FROM emp  e JOIN dept  d
ON e.deptno=d.deptno
WHERE ename='BLAKE';

--SMITH의 호봉은? 이름 급여액 호봉수 
SELECT ename,sal,grade 
FROM emp e JOIN salgrade s 
ON e.sal BETWEEN s.losal AND s.hisal
WHERE ename='SMITH';

--이름과 부서번호 급여액 부서명 호봉수를 표시햅보세요 
SELECT ename,e.deptno,sal,dname,grade 
FROM emp e JOIN dept d  ON e.deptno =d.deptno
JOIN salgrade s ON e.sal BETWEEN s.losal AND s.hisal;
--ON: 연결 조건  위에 코드는 INER JOIN

--SELF JOIN:한개의 테이블을 마치 2개의 독립적인 테이블 처럼 JOIN 에 사용하는 것 
--전체 사원의 번호 ,이름 ,상급자 번호 상급자 이름 을 출력하고 싶다
SELECT e.empno ,e.ename,e.mgr ,m.ename
FROM emp e JOIN emp m 
on e.mgr=m.empno;

--각 부서의 평균 급여보다 더 많은
--급여를 받는 사원의 이름 ,부서번호, 급여
SELECT ROUND(AVG(sal),2)"전사원의 평균 급여" FROM emp;
SELECT ROUND(AVG(sal),2)"전사원의 평균 급여",MAX(sal),MIN(sal)
FROM emp;

--20번 부서의 평균 급여액을 표시 
SELECT  ROUND(AVG(sal)) FROM emp2 WHERE deptno=20;

--하위 그룹을 지어서 각 그룹에 대한 통계를 구해보자 
SELECT deptno, COUNT(*)"사원수" FROM emp2 GROUP BY deptno ORDER BY DEPTNO;
-- 부서별 행수  사원수 계산  '' : 문자열 ,"" : 데이터가 아님 ,컬럼명

--SELF JOIN, INNER JOIN에 속함  =연결조건 안에 들어오는것만 
SELECT e.empno ,e.ename,e.mgr ,m.ename
FROM emp e JOIN emp m 
on e.mgr=m.empno; --사원수가 11명? 12명인데?  이유 연결조건에 벗어나서 =MGR이 NULL이여서

--OUTER JOIN도 필요 
SELECT e.empno ,e.ename,e.mgr ,m.ename
FROM emp e LEFT OUTER JOIN emp m on e.mgr=m.empno;
// 벗어나 있는것도 가져와라 
//OUTER :벗어나 있어도 LEFT 왼쪽 


--상관관계 서브 쿼리(Correlated Sub-Query)
--일반 서브쿼리는 가장 깊은 괄호안에 있는 쿼리가
--한번만 실행되어 값을 가지고 있지만
--상관 관계 서브커리는 바깥 커리가 실행될 때마다 한번씩 실행된다


DESC dept ;
SELECT empno,ename,e.deptno, dname ,grade "호봉"
FROM emp e JOIN dept d ON e.deptno=d.deptno
JOIN salgrade s ON e.sal BETWEEN s.losal AND s.hisal
WHERE e.deptno =10;


--AND OR 
SELECT * FROM emp2 WHERE job='CRERK' AND job='MANAGER';
SELECT * FROM emp2 WHERE job='CRERK' OR job='MANAGER';


-- CROSS JOIN 
SELECT *FROM emp, dept; --12 *4 48개 
--목록을 가져올때 데이터의 총 행수를 동시에 가져와야 한다 
SELECT count(*) FROM emp;
SELECT * FROM emp;
--위에 2개 합치기 

SELECT *
FROM emp2 CROSS JOIN (SELECT COUNT(*)total FROM emp2);
-- 이용자한테 총페이지가 몇개인데 현재 이용자는 무슨 무슨 페이지에 있다라는 것을 알려줄수있음 


--게시글을 작성할 떄 첨부 파일을 다수개 첨부하는 경우 
--board(게시글) attach(첨부) 글번호 참조해야함 
--테이블 생성할 때  제약조건 pk :     FK:
--Pagination 
--상관관계 서브 쿼리 

CREATE TABLE bbs ( --부모 
    bid NUMBER NOT NULL PRIMARY KEY,
    title VARCHAR2(50) NOT NULL, -- 가변 문자열 최대 50바이트 
    contents VARCHAR2(2000) NOT NULL
);
-- 컬럼명 -지료형 , 제약 조건 
CREATE TABLE attach( -- 자식 
    aid NUMBER NOT NULL PRIMARY KEY,
    bid NUMBER,
    fname VARCHAR2(30),
    CONSTRAINT fk_bid FOREIGN KEY(bid) REFERENCES bbs (bid)  -- 외래키 지정
    --뜻 제약조건 
    -- forign 외부에 있는 값을 참조                     bbs 테이블의 bid를 
);

--Pagination 
--유사컬럼: 테이블에 없지만 컬럼처럼 사용할 수 있는 컬럼 
--RoWNUM

SELECT empno ,ename, ROWNUM "행번호" FROM emp2;
-- 1페이지에 3개씩이면 이용자가 4    끝 4*3 12 end  첫 12- (3-1) 10 
SELECT *,ROWNUM AS rn FROM emp2;  -- 오류 서브쿼리로 
SELECT t.*, ROWNUM RN FROM 
(

SELECT * FROM emp2
)t;



SELECT  * FROM -- 10,11,12 
(
SELECT t.*, ROWNUM RN FROM 
   (

   SELECT * FROM emp2
   )t



) WHERE RN BETWEEN 10 AND 12;


SELECT SYSDATE "오늘 날짜";
SELECT SYSDATE "오늘 날짜" FROM emp2; --?  아무 테이블 넣어도 된다? 근데 샐랙트문은 반복이라는 것을 명심하자 

SELECT SYSDATE "오늘 날짜" FROM dept;

SELECT SYSDATE "오늘 날짜" FROM DUAL; -- 한개의 컬럼밖에 없음
SELECT * FROM DUAL;  --?x


SELECT 'A'col1,'B' col2,'c'col3 FROM DUAL; 
SELECT 5 page , SYSDATE "날짜"FROM DUAL;  
SELECT (2/3) "결과" FROM DUAL;
SELECT (5/3) "결과" FROM DUAL; --1.66666666666666666666666666666666666667
SELECT TRUNC(5/3) "결과" FROM DUAL; --1
SELECT CEIL(5/3) "결과" FROM DUAL;  --2
SELECT FLOOR(5/3) "결과" FROM DUAL;  --1



SELECT * FROM
(
  SELECT t2.*,TRUNC((RN-1)/3+1) AS page FROM 
   (

   SELECT t.*,ROWNUM RN FROM 
   (
   SELECT * FROM emp2,(SELECT CEIL(COUNT(*)/3)total FROM emp2)
   )t


)t2
)WHERE page =4 ;



 
-- 전체 페이지 수 확인 하기 위해 돌림 


-- 계층구조 커리 (Hierarchical Query)

--Hello 
 --  ㄴReply:Hello 
  --    ㄴAAA 

--오라클에서 제공  계층구조란? 계층구조 차수란? 
 
--START WITH ~CONNECT BY PRIOR ~연결조건 
 


SELECT empno, ename, deptno, sal,mgr FROM emp2
START WITH mgr IS NULL 
CONNECT BY PRIOR empno=mgr;

-- 계구조 커리에서만 사용가능한 유사컬럼(LEVEL차수)을 사용하여 시각적인 계충구조 출력 

--이름 왼쪽에 차수에 비례해서 넣어주자 
SELECT '   '|| ename ename FROM emp2;
SELECT LPAD('A',5,'a') FROM DUAL; -- A의 외쪽에 'a'를 붙여서 최대길이가 5가 되게한다 
SELECT empno,'    '|| ename "이름", deptno FROM emp2; 

SELECT empno,LPAD('   ',2*3,'   ')|| ename "이름", deptno FROM emp2; 



SELECT empno, LPAD('ㅤ',(LEVEL-1)*3,'ㅤ')||ename ename, deptno, sal,mgr FROM emp2
START WITH mgr IS NULL 
CONNECT BY PRIOR empno=mgr -- 자료형이 같아야함 
ORDER SIBLINGS BY empno;
--웹브라우저에서는 공백을 1나만 인정  맥북 공백 특수문자가 없네 


-- 동일 차수 내에서 정렬 방법은 ? 형제내에서 정렬 
--ORDER BY 는 전체

--- 연습코드 

SELECT * FROM
(
     SELECT t2.*,TRUNC((RN-1)/5+1) AS page FROM 
    (
        SELECT t.*,ROWNUM RN FROM 
        (
          SELECT empno, 
          LPAD('ㅤ',(LEVEL-1)*3,'ㅤ')||ename ename, 
          deptno, sal,job, hiredate,total 
          FROM emp2,
          (
             SELECT CEIL(COUNT(*)/3)total FROM emp2
          )
   
          START WITH mgr IS NULL 
          CONNECT BY PRIOR empno=mgr -- 자료형이 같아야함 
          ORDER SIBLINGS BY empno
        )t
    )t2
)WHERE page =1 ;


-- Oracle Sequence : 시퀸스 오브젝트 
-- 반드시 쓰이는 오브젝트 
CREATE SEQUENCE my_seq
       INCREMENT BY 1 -- 1:증가량 2일때는 2씩
       START WITH 1 -- 시작 수 1부터
       MINVALUE 1   --최저의 수
       --MAXVALUE 9999
       NOMAXVALUE --끝까지 올라감 ?
       NOCYCLE   --번호 순환 123123123
       NOCACHE   
       NOORDER;  -- 요청된 순서되로 번호를 매길것인가 NO  외부에서 받으면 ...에바 .. 필요 없음  중복되지않은 숫자를 받기만하자 
     
     
SELECT  board_seq.NEXTVAL FROM DUAL;   --NEXTVAL 다음값으로 올려 
SELECT  board_seq.CURRVAL FROM DUAL;
-- 게스글 번호를 시퀀스로 하는 경우 
INSERT INTO board (num,title,contents) VALUES
(board_seq.NEXTVAL,'sample','contents...');

--board(bid,title,contents) 부모
--attch(bid,fnum,fname) 자식 

-- 첨부파일 번호로 사용될 시컨스 선언 (attach_seq)
-- 보드 테이블에  한행의 데이터를 추가한다
INSERT INTO board (bid,title,contents) VALUES
(board_seq.NEXTVAL,'sample1','contents...');

SELECT * FROM board;

--attach 테이블에 현재 게시글의 첨부파일을 2개  저장해보자
--글 번호는 동일하고 파일번호는 달라야함  
INSERT INTO attach VALUES(board_seq.CURRVAL,attach_seq.NEXTVAL,'test1.txt');
INSERT INTO attach VALUES(board_seq.CURRVAL,attach_seq.NEXTVAL,'test2.txt');

SELECT* FROM ATTACH;


SELECT b.bid, contents, fname FROM board b INNER JOIN attach a ON b.bid=a.bid;
-- 1 

SELECT b.bid, contents, fname FROM board b LEFT OUTER JOIN attach a ON b.bid=a.bid ORDER BY b.bid;
-- 2    파일이 없는 게시물도 가져옴 

SELECT LISTAGG(ename,',') WITHIN GROUP (ORDER BY deptno) FROM emp2 ;  
-- 부서번호를 기준으로 합친다 부서 번호가 같은 행끼리 합치는데 ename을 가져올때 ,로 합쳐서 가져와라 
SELECT LISTAGG(ename,',') WITHIN GROUP (ORDER BY deptno) FROM emp2 
WHERE deptno=20;
--1
SELECT deptno, LISTAGG(ename,',') WITHIN GROUP (ORDER BY deptno) FROM emp2 
GROUP BY deptno;
--2
SELECT deptno, LISTAGG(empno||'_'||ename,',') WITHIN GROUP (ORDER BY deptno) 
FROM emp2 
GROUP BY deptno;
--3


-- board attach 테이블로부터 화면에 목록을 표시할때 한개의 글에 딸린 첨부파일 이름을
-- 한개의 컬럼에 모두 합쳐서 화면에 표시해보세요 . 

-- attach 테이블만 사용하는 경우
SELECT bid, LISTAGG(fname,',') WITHIN GROUP(ORDER BY bid) files 
FROM ATTACH GROUP BY bid;

-- JOIN을 통해서 가져오는 경우 
SELECT b.bid,b.title, LISTAGG(fname,',') WITHIN GROUP(ORDER BY a.bid)files 
FROM board b LEFT OUTER JOIN attach a
on b.bid=a.bid 
GROUP BY b.bid, b.title
ORDER BY b.bid;

--............상관관계 서브쿼리............
SELECT bid,title,contents,
(
    SELECT LISTAGG(fnum||','||fname,'/') WITHIN GROUP(ORDER BY fnum) files
    FROM ATTACH WHERE bid=b.bid

)att -- 하나의 컬럼 
FROM board b; 
--...........................


-- Oracle 그룹함수, 단일행 함수, OVER() 
-- 그룹함수    : COUNT(*) 다수개의 행을 대상으로 조사하여 한개의 값을 산출함 
-- 단일행함수  : 한개의 행에서 값을 산출함 

SELECT ename, COUNT(*) FROM emp2; -- 오류 단일 그룹의 그룹 함수가 아닙니다
SELECT ename, COUNT(*)OVER() FROM emp2; -- 반복해서 가져와

-- JOIN 결과 다수개의 행으로 표현된 내용을 한개의 행으로 합쳐서 보여주는 경우 

-- 부서별 사원의 으름이 한개의 컬럼으로 리턴되는 부서인원 정보 출력기능 

-- EMPDAO에 getEmpsByDeptno()  부서별 사원 이름을 리턴해줘는 기능 작성할것 ,리턴타입 Map<String ,String> 

SELECT deptno,dname,
(
    SELECT LISTAGG(ename,'/') WITHIN GROUP(ORDER BY deptno) 
    FROM EMP2 WHERE deptno=e.deptno
)names
FROM DEPT e;


SELECT deptno,COUNT(empno)"사원수",
        
    LISTAGG(ename,'/') WITHIN GROUP(ORDER BY deptno) names
    FROM EMP2

GROUP BY deptno;
---------------------------------------------------------------
-- 글번호 ,제목 , 첨부파일 수를 화면에 표시해보세요. 
SELECT b.bid,b.title,COUNT(fname)"파일 수 ",LISTAGG(fname,',') WITHIN GROUP(ORDER BY a.bid)files 
FROM board b LEFT OUTER JOIN attach a
on b.bid=a.bid 
GROUP BY b.bid, b.title
ORDER BY b.bid;