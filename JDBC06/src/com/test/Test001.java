/*
 		Test001
 		- 쿼리문 전송 실습 1
 */
package com.test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;

import com.util.DBConn;

public class Test001
{
	public static void main(String[] args)
	{
		try
		{
			Connection conn = DBConn.getConnection();
			
			if (conn != null)
			{
				System.out.println("데이터베이스 연결 성공~!!!");
				
				try
				{
					/*
					Statement stmt  = conn.createStatement();
					String sql = "INSERT INTO TBL_MEMBER(SID, NAME, TEL)"
							+ " VALUES(MEMBERSEQ.NEXTVAL, '고연수','010-7777-7777')";
					int result = stmt.executeUpdate(sql);
					if (result > 0)
					{
						System.out.println("데이터 입력 성공~!!!");
						stmt.close();
					}
					*/
					
					/*
					String sql = "INSERT INTO TBL_MEMBER(SID, NAME, TEL)"
							+ " VALUES(MEMBERSEQ.NEXTVAL, '고연수','010-7777-7777')";
					Statement stmt  = conn.createStatement();
					int result = stmt.executeUpdate(sql);
					if (result > 0)
						System.out.println("데이터 입력 성공~!!!");
					stmt.close();
					*/
					/*
					String sql = "INSERT INTO TBL_MEMBER(SID, NAME, TEL)"
							+ " VALUES(MEMBERSEQ.NEXTVAL, '고연수','010-7777-7777')";
					PreparedStatement pstmt = conn.prepareStatement(sql);
					int result = pstmt.executeUpdate();

					if (result > 0)
						System.out.println("데이터 입력 성공~!!!!");

					pstmt.close();
					*/
					String sql = "INSERT INTO TBL_MEMBER(SID, NAME, TEL)"
//						 + " VALUES(MEMBERSEQ.NEXTVAL, %d, %s,'%d','%s')";
//							+ " VALUES(MEMBERSEQ.NEXTVAL, ?, ?)";
							+ " VALUES(?, ?, ?)";
					PreparedStatement pstmt = conn.prepareStatement(sql);
					pstmt.setInt(1, 8);
					pstmt.setString(2,"임시연");
					pstmt.setString(3, "010-7777-7777");
					
					int result = pstmt.executeUpdate();

					if (result > 0)
						System.out.println("데이터 입력 성공~!!!!");

					pstmt.close();
					
					
				} catch (Exception e)
				{
					System.out.println(e.toString());
				}
				
				
			}
			
		} catch (Exception e)
		{
			System.out.println(e.toString());
		}
	}

}
