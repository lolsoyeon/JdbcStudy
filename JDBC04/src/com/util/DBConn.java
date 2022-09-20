/*=====================
   DBConn.java
   - try ~ catch
 =====================*/

package com.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConn
{
	// 주요 변수 선언
	public static Connection dbConn;
	
	// 메소드 정의 → 연결
	public static Connection getConnection()
	{
		if (dbConn == null)
		{
			try
			{
				String url = "jdbc:oracle:thin:@211.238.142.48:1521:xe";
				String user = "scott";
				String pwd = "tiger";
				
				Class.forName("oracle.jdbc.driver.OracleDriver");
				
				dbConn = DriverManager.getConnection(url, user, pwd);
	
				
			} catch (Exception e)
			{
				// 에러 메세지 출력
				System.out.println(e.toString());
			}
			
		}
		// 구성된 연결 객체 반환
		return dbConn;
	}
	
	public static Connection getConnection(String url, String user, String pwd)
	{
		
		if (dbConn == null)
		{
			try
			{
				Class.forName("oracle.jdbc.driver.OracleDriver");
				
				dbConn = DriverManager.getConnection(url, user, pwd);
				
			} catch (Exception e)
			{
				System.out.println(e.toString());
			}
			
		}

		return dbConn;
		
	}
	public static void close()
	{
		if (dbConn != null)
		{
			try
			{
				if (!dbConn.isClosed())
				{
					dbConn.close();
					
				}
				
			} catch (Exception e)
			{
				System.out.println(e.toString());
			}
			
		}
		dbConn = null;
		
	}

}
