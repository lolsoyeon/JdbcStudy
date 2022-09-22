-- JDBC01_scott.sql

SELECT USER
FROM DUAL;
--==>> SCOTT

--○ 기본 테이블 제거
DROP TABLE TBL_MEMBER PURGE;
--==>> Table TBL_MEMBER이(가) 삭제되었습니다.

--○ 실습 테이블 생성(TBL_MEMBRER)
CREATE TABLE TBL_MEMBER
( SID  NUMBER
, NAME VARCHAR2(30)
, TEL  VARCHAR2(60)
, CONSTRAINT MEMBER_SID_PK PRIMARY KEY(SID)
);
--==>> Table TBL_MEMBER이(가) 생성되었습니다.


--○ 데이터 입력
INSERT INTO TBL_MEMBER(SID, NAME, TEL) VALUES(1,'김인교', '010-1111-1111')
;
--==>> 1 행 이(가) 삽입되었습니다.


--○ 확인
SELECT *
FROM TBL_MEMBER;
--==>> 1	김인교	010-1111-1111


--○ 커밋
COMMIT;
--==>> 커밋 완료.

--★ DML 구문을 실행하면 반드시 트랜잭션 커밋/ 롤백 처리 해줘야한다.★




--○ Test002.java 실행 후 확인
SELECT *
FROM TBL_MEMBER;
/*
2	민찬우	010-2222-2222
1	김인교	010-1111-1111
*/


--○ Test003.java 실행 후 확인
SELECT *
FROM TBL_MEMBER
ORDER BY SID;
/*
2	민찬우	010-2222-2222
3	최나윤	010-3333-3333
4	조현하	010-4444-4444
5	김유림	010-5555-5555
1	김인교	010-1111-1111
*/

--○ 조회 쿼리문 구성
SELECT SID, NAME, TEL
FROM TBL_MEMBER
ORDER BY 1;
--> 한 줄 구성
SELECT SID, NAME, TEL FROM TBL_MEMBER ORDER BY 1
;
--==>> 
/*
1	김인교	010-1111-1111
2	민찬우	010-2222-2222
3	최나윤	010-3333-3333
4	조현하	010-4444-4444
5	김유림	010-5555-5555
*/







