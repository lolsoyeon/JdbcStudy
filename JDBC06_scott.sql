SELECT USER
FROM DUAL;
--==>> SCOTT

--○ 실습 테이블 생성

-- 확인
SELECT *
FROM TBL_MEMBER;


-- 입력 쿼리문 구성

INSERT INTO TBL_MEMBER(SID, NAME, TEL)
VALUES(MEMBERSEQ.NEXTVAL, '정영준','010-6666-6666');
--==>> 1 행 이(가) 삽입되었습니다.

INSERT INTO TBL_MEMBER(SID, NAME, TEL)
VALUES(MEMBERSEQ.NEXTVAL, '정영준','010-6666-6666');


INSERT INTO TBL_MEMBER(SID, NAME, TEL) VALUES(MEMBERSEQ.NEXTVAL, '정영준','010-6666-6666')
;

-- Test001.java 실행 후 확인
/*
6	정영준	010-6666-6666
1	장현성	010-1111-1111
2	정미경	010-2222-2222
3	엄소연	010-3333-3333
4	박원석	010-4444-4444
5	김유림	010-5555-5555
7	고연수	010-7777-7777
8	임시연	010-7777-7777
*/




desc tbl_member;


-- 전체 조회 쿼리문 구성
SELECT SID, NAME, TEL
FROM TBL_MEMBER
ORDER BY SID;

SELECT SID, NAME, TEL FROM TBL_MEMBER ORDER BY SID
;


