SELECT USER
FROM DUAL;
--==>> SCOTT

--�� �ǽ� ���̺� ����

-- Ȯ��
SELECT *
FROM TBL_MEMBER;


-- �Է� ������ ����

INSERT INTO TBL_MEMBER(SID, NAME, TEL)
VALUES(MEMBERSEQ.NEXTVAL, '������','010-6666-6666');
--==>> 1 �� ��(��) ���ԵǾ����ϴ�.

INSERT INTO TBL_MEMBER(SID, NAME, TEL)
VALUES(MEMBERSEQ.NEXTVAL, '������','010-6666-6666');


INSERT INTO TBL_MEMBER(SID, NAME, TEL) VALUES(MEMBERSEQ.NEXTVAL, '������','010-6666-6666')
;

-- Test001.java ���� �� Ȯ��
/*
6	������	010-6666-6666
1	������	010-1111-1111
2	���̰�	010-2222-2222
3	���ҿ�	010-3333-3333
4	�ڿ���	010-4444-4444
5	������	010-5555-5555
7	������	010-7777-7777
8	�ӽÿ�	010-7777-7777
*/




desc tbl_member;


-- ��ü ��ȸ ������ ����
SELECT SID, NAME, TEL
FROM TBL_MEMBER
ORDER BY SID;

SELECT SID, NAME, TEL FROM TBL_MEMBER ORDER BY SID
;

