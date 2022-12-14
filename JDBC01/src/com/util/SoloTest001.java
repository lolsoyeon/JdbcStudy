/*==============
 DBConn.java
 ===============*/

// 싱글톤(singleton)  디자인 패턴을 이용한 Database 연결 객체 생성 전용 클래스
//				→ DB 연결 과정이 가장 부하가 크기 때문에
//					연결이 필요할 때마다 객테를 생성하는 것이 아니라
//					한 번 연결된 객체를 계속 사용할 수 있도록 처리.

package com.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class SoloTest001
{
	//  변수 선언
	// -- static 으로 선언해서 필요할 때마다 만드는것이 아니라 미리 만들어둔다.
	// -- Connection 은 한 번만 만들고 필요 할 때 꺼내쓴다 (열쇠)
	// 디자인 패턴 이라고 표현하는데 설계하고 구조화에 있어서 공식이라고 생각하면 된다.
	public static Connection dbConn;
	//-- private → 정보 은닉
	// 	 static → 정적 변수
	//	 자동으로 초기화 지원 → null 객체 형식은 초기화가 null이다. 
	
	// 메소드 정의 → 연결
	public static Connection getConnection()
	{
		// 한 번 연결된 객체를 계속 사용...
		//-- 연결이 안되어 있는 상태에서는 연결하게 해줘라 
		// 즉, 연결되지 않은 경우에만 연결을 시도하겠다는 의미
		// → singleton(디자인 패턴)
		if (dbConn == null)
		{
			try
			{
				String url = "jdbc:oracle:thin:@211.238.142.48:1521:xe";
				// 211.238.142.48 은 오라클 서버 IP 주소를 기재하는 부분
				// 1521 은 오라클 port number
				// xe 는 오라클 sid(express edition 은 xe)
				String user = "scott";		// 오라클 사용자 계정 이름
				String pwd = "tiger";		// 오라클 사용자 계정 암호
				
				Class.forName("oracle.jdbc.driver.OracleDriver");
				//-- OracleDriver 클래스에 대한 객체 생성
				//	 드라이버 로딩 → JVM에 전달
				
				dbConn = DriverManager.getConnection(url, user, pwd);
				//-- 오라클 서버 실제 연결
				// 위 (line 37 ~ 48)는... 연결을 위한 환경을 설정하는 과정
				// 갖고있는 인자값(매개변수)은 오라클 주소, 계정명, 패스워드
				
			} catch (Exception e)
			{
				System.out.println(e.toString());
				//-- 오라클 서버 연결 실패 시.... 오류 메세지 출력하는 부분
			}
			
		}
		// 구성된 연결 객체 반환
		return dbConn;
	}// end getConnection()
	
	
	
	// getConnection() 메소드 오버로딩 → 연결
	public static Connection getConnection(String url,String user, String pwd)
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
		// 구성된 연결 객체 반환
		return dbConn;
	}// end getConnection()
	
	
	// 메소드 정의 → 연결 종료 메소드
	public static void close()
	{
		// dbConn 변수(멤버 변수)는
		// Database 가 연결관 상태일 경우 Connection을 갖는다.
		// 연결되지 않은 상태라면 null 인 상황이 된다.
		if (dbConn != null) // 연결된 상황
		{
			try
			{
				// 연결 객체의 isclose() 메소드를 통해 연결상태 확인
				//-- 연결이 닫혀있는경우 true 반환
				//   연결이 닫혀있지 않은경우 false 반환 즉, 연결 연림
				// !true = false
				if (!dbConn.isClosed())
				{
					dbConn.close();
					//-- 연결 객체의 close() 메소드 호출을 통해 연결 종료
					
				}
				
			} catch (Exception e)
			{
				System.out.println(e.toString());
			}
			
		}
		// 연결 객체 초기화
		dbConn = null;
	}// end colse()
	
}
