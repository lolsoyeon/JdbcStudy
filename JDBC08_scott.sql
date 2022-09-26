SELECT USER
FROM DUAL;
--==> SCOTT

SELECT *
FROM TBL_MEMBER
ORDER BY SID;
/*
1	장현성	010-1111-1111
2	정미경	010-2222-2222
3	엄소연	010-3333-3333
4	박원석	010-4444-4444
5	김유림	010-5555-5555
6	정영준	010-6666-6666
7	고연수	010-7777-7777
8	임시연	010-7777-7777
9	홍길동	010-8888-8888
10	고길동	010-9999-9999
*/



DESC TBL_MEMBER;
--==>>
/*
이름   널?       유형           
---- -------- ------------ 
SID  NOT NULL NUMBER       
NAME          VARCHAR2(30) 
TEL           VARCHAR2(60) 

*/

--○ CallableStatement 실습을 위한 프로시저 생겅(작성)
-- 프로시저명 : PRC_MEMBERINSERT
-- 프로시저 기능 :  TBL_MEMBER 테이블에 데이터를 입력하는 INSERT 프로시저


CREATE OR REPLACE PROCEDURE PRC_MEMBERINSERT
( VSID  IN TBL_MEMBER.SID%TYPE
, VNAME IN TBL_MEMBER.NAME%TYPE
, VTEL  IN TBL_MEMBER.TEL%TYPE
)
IS
BEGIN
    -- 실행부
    -- 데이터 입력 쿼리문 구성
    INSERT INTO TBL_MEMBER(SID, NAME, TEL)
    VALUES(VSID, VNAME, VTEL);
    
    -- 커밋
    COMMIT;
END;
--==>> Procedure PRC_MEMBERINSERT이(가) 컴파일되었습니다.

--○ Test001.java 실행 후 확인
SELECT *
FROM TBL_MEMBER
ORDER BY SID;
/*
1	장현성	010-1111-1111
2	정미경	010-2222-2222
3	엄소연	010-3333-3333
4	박원석	010-4444-4444
5	김유림	010-5555-5555
6	정영준	010-6666-6666
7	고연수	010-7777-7777
8	임시연	010-7777-7777
9	홍길동	010-8888-8888
10	고길동	010-9999-9999
11	김보경	010-0022-0022   <<<
12	정영준	0101-0023-0012  <<<
*/

--○ 프로시저 재 작성 출력 매개변수를 통해 값을 얻어 내도록
--   CallableStatment 실습을 위한 프로시저 생성(작성)
--   프로시저 명 : PRC_MEMBERSELECT
--   프로시저 기능: TBL_MEMBER 테이블의 데이터를 읽어오는 SELECT 프로시저
--   ※ 『SYS_REFCURSOR』 자료형을 이용하여 커서 다루기
-- REF 참조를 하는것은 보통 안 닫는다. 닫을때는 동기화터리 해줘야함

CREATE OR REPLACE PROCEDURE PRC_MEMBERSELECT
( VRESULT   OUT SYS_REFCURSOR
)
IS
BEGIN
    OPEN VRESULT FOR
    SELECT SID, NAME, TEL
    FROM TBL_MEMBER
    ORDER BY SID;
    
    --CLOSE VRESULT;
END;
--==>> Procedure PRC_MEMBERSELECT이(가) 컴파일되었습니다.


















