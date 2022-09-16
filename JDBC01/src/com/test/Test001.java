/*================
  Test001.java
  - Database 연결 테스트
 ==================*/

package com.test;

import java.sql.Connection;

import com.util.DBConn;

public class Test001
{
	public static void main(String[] args)
	{
		Connection conn = DBConn.getConnection();
		//커넥션객체 반환한다
		
		if (conn != null)
		{
			System.out.println("데이터 베이스 연결 성공!~!!");
			
		}
		DBConn.close();
	}

}
