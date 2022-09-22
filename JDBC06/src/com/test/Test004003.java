/*
 	Test004.java Test003과 동일! 2022-09-22
 	쿼리문 전송 실습
 */
package com.test;

import java.io.ObjectInputStream.GetField;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.util.DBConn;

public class Test004003
{
	public static void main(String[] args)
	{
		try
		{
			Connection conn = DBConn.getConnection();
			if (conn != null)
			{
				System.out.println("데이터베이스 연결 성공~!~!~!");
			}
			try
			{
				// 쿼리문 준비 
				String sql = "SELECT SID, NAME, TEL" + " FROM TBL_MEMBER ORDER BY SID";
				
				// PreparedStatment 작업 객체 생성
				PreparedStatement pstmt = conn.prepareStatement(sql);
				// 작업 객체 실행
				ResultSet rs = pstmt.executeQuery();
				// excuteQuery 문은 적용된 행의 갯수 ReusltSet rs 반환

				// ResultSet 처리 → 반복문
				while (rs.next())
				{
					int sid = rs.getInt("SID");
					String name = rs.getString("NAME");
					String tel = rs.getString("TEL");
					
					String str = String.format("%3d %5s %10s", sid, name, tel);
					System.out.println(str);
				}
				rs.close();
				pstmt.close();
				
				
			} catch (Exception e)
			{
				System.out.println(e.toString());
			}
			DBConn.close();
			System.out.println("데이터베이스 연결이 끊겼습니다~!~!~");
			System.out.println("프로그램 종료");
			
			
			
		} catch (Exception e)
		{
			System.out.println(e.toString());
		}
	}
	
}