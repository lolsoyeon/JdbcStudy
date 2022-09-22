//  실행 예)

// 번호 입력(-1 종료) : 9
// 이름 입력 : 홍길동
// 전화번호 입력 : 010-8888-8888
// >> 데이터베이스 연결 성공~!!!
// >> 회원 정보 입력 완료

// 번호 입력(-1 종료) : 10
// 이름 입력 : 고길동
// 전화번호 입력 : 010-9999-9999
// >> 데이터베이스 연결 성공~!!!
// >> 회원 정보 입력 완료

// 번호 입력(-1 종료) : -1
// >> 데이터베이스 연결닫힘~!!!
// >> 프로그램 종료됨~!!!!!


package com.test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Scanner;

import com.util.DBConn;

public class Test002
{
	public static void main(String[] args)
	{
		try
		{
			Scanner sc = new Scanner(System.in);
			Connection conn = DBConn.getConnection();
			
			do
			{
				System.out.print("\n번호 입력(-1 종료) : ");
				String sid = sc.next();
				
				if (sid.equals("-1"))
				{
					break;
					
				}
				System.out.println("이름 입력 : ");
				String name = sc.next();
				
				System.out.println("전화번호 입력 : ");
				String tel = sc.next();
				
				if (conn != null)
				{
					System.out.println(">> 데이터베이스 연결 성공!~!!!!");
					
					try
					{
						// ※ 작업 객체 생성 전에 쿼리문 준비~!!!!!
						String sql = "INSERT INTO TBL_MEMBER(SID, NAME, TEL)"
								+ " VALUES( ?, ?, ?)";
						// 보안성이 좋다><
						// 작업 객체 생성(PreparedStatment)
						PreparedStatement pstmt = conn.prepareStatement(sql);
						//-- 작업 객체 생성 과정에서 쿼리문 전달~~~!!!
						// check~!!
						
						// 『?』 매개변수 전달
						// Data Type Check~!!!!
						// 이해했지만 다시 복습해야할 부분 따라쓰기~!!!!!!
						pstmt.setInt(1, Integer.parseInt(sid));
						pstmt.setString(2, name);
						pstmt.setString(3, tel);
						
						// 작업 객체 실행 과정(excute)에서
						// 쿼리문 전달되지 않음~!!!!
						int result = pstmt.executeUpdate();
						
						if (result > 0)
						{
							System.out.println(">> 회원 정보 입력 완료~!!!!");
						}
						
						
					} catch (Exception e)
					{
						System.out.println(e.toString());
					}
				}
			} while (true);
			
			DBConn.close();
			System.out.println(">> 데이터베이스 연결 닫힘~!!");
			System.out.println(">> 연결 닫힘~!!!");
			
		} catch (Exception e)
		{
			System.out.println(e.toString());
		}
	}

}

/*
번호 입력(-1 종료) : 9
이름 입력 : 
홍길동
전화번호 입력 : 
010-8888-8888
>> 데이터베이스 연결 성공!~!!!!
>> 회원 정보 입력 완료~!!!!

번호 입력(-1 종료) : 10
이름 입력 : 
고길동
전화번호 입력 : 
010-9999-9999
>> 데이터베이스 연결 성공!~!!!!
>> 회원 정보 입력 완료~!!!!

번호 입력(-1 종료) : -1
>> 데이터베이스 연결 닫힘~!!
>> 연결 닫힘~!!!


번호 입력(-1 종료) : 
 */
