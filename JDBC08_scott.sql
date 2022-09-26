SELECT USER
FROM DUAL;
--==> SCOTT

SELECT *
FROM TBL_MEMBER
ORDER BY SID;
/*
1	������	010-1111-1111
2	���̰�	010-2222-2222
3	���ҿ�	010-3333-3333
4	�ڿ���	010-4444-4444
5	������	010-5555-5555
6	������	010-6666-6666
7	����	010-7777-7777
8	�ӽÿ�	010-7777-7777
9	ȫ�浿	010-8888-8888
10	��浿	010-9999-9999
*/



DESC TBL_MEMBER;
--==>>
/*
�̸�   ��?       ����           
---- -------- ------------ 
SID  NOT NULL NUMBER       
NAME          VARCHAR2(30) 
TEL           VARCHAR2(60) 

*/

--�� CallableStatement �ǽ��� ���� ���ν��� ����(�ۼ�)
-- ���ν����� : PRC_MEMBERINSERT
-- ���ν��� ��� :  TBL_MEMBER ���̺� �����͸� �Է��ϴ� INSERT ���ν���


CREATE OR REPLACE PROCEDURE PRC_MEMBERINSERT
( VSID  IN TBL_MEMBER.SID%TYPE
, VNAME IN TBL_MEMBER.NAME%TYPE
, VTEL  IN TBL_MEMBER.TEL%TYPE
)
IS
BEGIN
    -- �����
    -- ������ �Է� ������ ����
    INSERT INTO TBL_MEMBER(SID, NAME, TEL)
    VALUES(VSID, VNAME, VTEL);
    
    -- Ŀ��
    COMMIT;
END;
--==>> Procedure PRC_MEMBERINSERT��(��) �����ϵǾ����ϴ�.

--�� Test001.java ���� �� Ȯ��
SELECT *
FROM TBL_MEMBER
ORDER BY SID;
/*
1	������	010-1111-1111
2	���̰�	010-2222-2222
3	���ҿ�	010-3333-3333
4	�ڿ���	010-4444-4444
5	������	010-5555-5555
6	������	010-6666-6666
7	����	010-7777-7777
8	�ӽÿ�	010-7777-7777
9	ȫ�浿	010-8888-8888
10	��浿	010-9999-9999
11	�躸��	010-0022-0022   <<<
12	������	0101-0023-0012  <<<
*/

--�� ���ν��� �� �ۼ� ��� �Ű������� ���� ���� ��� ������
--   CallableStatment �ǽ��� ���� ���ν��� ����(�ۼ�)
--   ���ν��� �� : PRC_MEMBERSELECT
--   ���ν��� ���: TBL_MEMBER ���̺��� �����͸� �о���� SELECT ���ν���
--   �� ��SYS_REFCURSOR�� �ڷ����� �̿��Ͽ� Ŀ�� �ٷ��
-- REF ������ �ϴ°��� ���� �� �ݴ´�. �������� ����ȭ�͸� �������

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
--==>> Procedure PRC_MEMBERSELECT��(��) �����ϵǾ����ϴ�.


















