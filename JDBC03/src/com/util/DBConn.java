/*===================
  DBConn.java
  - 예외처리 throws
 ===================*/
package com.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConn
{
	// 주요 변수 선언
	public static Connection dbConn;
	
	// 메소드 정의 → 연결
	public static Connection getConnection() throws ClassNotFoundException, SQLException
	{
		// 조건문 연결이 안되었을때 연결하겠다.
		if (dbConn == null)
		{
			String url = "jdbc:oracle:thin:@211.238.142.48:1521:xe";
		    String user = "scott";
			String pwd = "tiger";
			
			Class.forName("oracle.jdbc.driver.OracleDriver");
			dbConn = DriverManager.getConnection(url, user, pwd);
			
		}
		return dbConn;
		
	}//end getConnection()
	
	
	// 메소드 정의(오버로딩) → 오버로딩  
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
			if (!dbConn.isClosed())
			{
				dbConn.close();
				
			}
			
		}
		dbConn = null;
	}//end close()

}//end DBConn
