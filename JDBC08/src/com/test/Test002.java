/*=========================================================
	Test002.java PRC_MEMBERSELECT
	- CallableStatment 를 활용한 SQL 구문 전송 실습 2
 ==========================================================*/
package com.test;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Scanner;

import com.util.DBConn;

import oracle.jdbc.internal.OracleTypes;

public class Test002
{
	public static void main(String[] args)
	{
		try
		{
			Connection conn = DBConn.getConnection();

			if (conn != null)
			{
				System.out.println(">> 데이터 베이스 연결 성공~!!!!!");

			}
			try
			{
				String sql = "{call PRC_MEMBERSELECT(?)}";
				CallableStatement cstmt = conn.prepareCall(sql);

				// check~!!!
				// 프로시저 내부에서 SYS_REFCUSOR 를 사용하고 있기 때문에
				// OracleType.COURSOR 를 사용하기 위한 등록 과정이 필요한 상황
				// -- 인텔리샌ㄴ스가 없다
				// 1. 프로젝트명 마우스 우클릭 > Build path > configure.... >
				// → 대화창 호출
				// 2. Libraries 탭 클릭 > ADD External JAR 버튼 클릭
				// >> ojdbc6.jar 파일 추가 등록
				// 3. import oracle.jdbc.internal.OracleTypes; 구문 추가 됨
				cstmt.registerOutParameter(1, OracleTypes.CURSOR); // check~!!! 사항

				cstmt.execute(); // check~!!! 사항
				// 인덱스
				ResultSet rs = (ResultSet) cstmt.getObject(1); // 다운캐스팅
				// 실제로는 rs타입을 반환하지만 Object타입을 넘겨주기 때문에

				while (rs.next())
				{
					String sid = rs.getString("SID");
					String name = rs.getString("NAME");
					String tel = rs.getString("TEL");

					String str = String.format("%3s %7s %12s", sid, name, tel);

					System.out.println(str);

				}
				rs.close();
				cstmt.close();

			} catch (Exception e)
			{
				System.out.println(e.toString());
			}
			DBConn.close();
			System.out.println("\n>> 데이터베이스 연결 닫힘~!~!~!");
			System.out.println(">> 프로그램 종료됨~!!!!");

		} catch (Exception e)
		{
			System.out.println(e.toString());
		}
	}// end main
}// end class Test002

/*
>> 데이터 베이스 연결 성공~!!!!!
  1     장현성 010-1111-1111
  2     정미경 010-2222-2222
  3     엄소연 010-3333-3333
  4     박원석 010-4444-4444
  5     김유림 010-5555-5555
  6     정영준 010-6666-6666
  7     고연수 010-7777-7777
  8     임시연 010-7777-7777
  9     홍길동 010-8888-8888
 10     고길동 010-9999-9999
 11     김보경 010-0022-0022
 12     정영준 0101-0023-0012

>> 데이터베이스 연결 닫힘~!~!~!
>> 프로그램 종료됨~!!!!
  */
