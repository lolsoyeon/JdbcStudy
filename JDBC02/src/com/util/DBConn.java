/*===============
 	DBConn.java
 ================ */
// 예외 처리 방법 변경
// try ~ catch → throws
package com.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConn
{
	// 변수 선언
	public static Connection dbConn;
	
	// 메소드 정의 
	public static Connection getConnection() throws ClassNotFoundException, SQLException
	{
		if (dbConn == null)
		{
			String url = "jdbc:oracle:thin:@211.238.142.48:1521:xe";
			String user = "scott";
			String pwd = "tiger";
			
			
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// 대소문자 주의
			
			dbConn = DriverManager.getConnection(url, user, pwd);
		
		}

		return dbConn;
		// ★ 리턴위치 정말 중요 ★
		
		//System.out.println("데이터 베이스 연결 성공~!!");
		
	}//end getConnection()
	
	
	
	
	// 메소드 정의 (오버로딩) 
	public static Connection getConnection(String url, String user, String pwd) throws ClassNotFoundException, SQLException
	{
		if (dbConn == null)
		{		
			Class.forName("oracle.jdbc.driver.OracleDriver");
			dbConn = DriverManager.getConnection(url, user, pwd);
		}
		return dbConn;
	}//end getConnection()
	
	
	
	
	// 메소드 정의 → 연결 종료 close() 메소드 호출
	public static void close() throws SQLException
	{
		if (dbConn != null)
		{
			if (!dbConn.isClosed())  // 이걸 확인한 다음 닫아 주는 작업
			{
				dbConn.close();
			}
			
		}
		
		dbConn = null;
		
	}//end close()

	
}//end DBConn
