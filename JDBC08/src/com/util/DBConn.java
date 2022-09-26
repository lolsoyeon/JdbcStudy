/*====================
 	DBConn
 	- trt ~ chach
==================== */
package com.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConn
{
	public static Connection dbConn;
	
	public static Connection getConnection()
	{
		try
		{
			if (dbConn == null)
			{
				String url = "jdbc:oracle:thin:@211.238.142.48:1521:xe";
				String user = "scott";
				String pwd = "tiger";
				
				Class.forName("oracle.jdbc.driver.OracleDriver");
				
				dbConn = DriverManager.getConnection(url, user, pwd);
				
				
			}
			
		} catch (Exception e)
		{
			System.out.println(e.toString());
		}
		return dbConn;
		
	}
	public static Connection getConnection(String url, String user, String pwd)
	{
		try
		{
			if (dbConn == null)
			{
				Class.forName("oracle.jdbc.driver.OracleDriver");
				
				dbConn = DriverManager.getConnection(url, user, pwd);
				
			}
			
		} catch (Exception e)
		{
			System.out.println(e.toString());
		}
		return dbConn;
		
	}
	public static void close()
	{
		try
		{
			if (dbConn != null)
			{
				if (!dbConn.isClosed())
				{
					dbConn.close();
				}
				
			}
			
		} catch (Exception e)
		{
			System.out.println(e.toString());
		}
		
	}
}
