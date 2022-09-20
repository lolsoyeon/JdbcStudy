/*======================
   Test003.java
   - 데이터 입력 실습
 ======================*/

/*
 ○ JDBC 프로그램의 작성
 
 	1. 드라이버 인스턴스 생성
 	   사용할 파일들(데이터베이스 와의 연결 과정에서 사용될 파일들)이
 	   정상적으로 존재하는지 확인한다.
 	   이 과정에서는 굳이 인스턴스를 생성하지 않고,
 	   드라이버가 있는지만 확인하더라도 프로그램을 실행하는 데에는 지장이 없다.
 	   
 	    ※ 드라이버 클래스를 찾는방법
 	    『Class』 라는 클래스의 『forName()』 이라는 정적 메소드 사용
 	    이 메소드는 매개변수로 넘겨받은 이름의 클래스를 찾아주는 역할을 수행하며
 	    해당 클래스를 찾지 못할 경우 『ClassNotFoundException』 예외를
 	    발생 시키게 된다.
 	    
 	2. 연결 객체 생성
 	    (Class.forName() 메소드를 활용하여...) 찾은 드라이버 클래스를 가지고
 	    설치된 데이터베이스 서버와 연결하는 Connection 객체를 생성한다.
 	    
 	    ※ Connection 객체는 DriverManager 클래스의
 	    『getConnection()』 이라는 정적 메소드로 생성한다.
 	    예외는 데이터베이스 서버와 연결을 수행하는 과정에서 문제가 있을 경우
 	    발생하게 되며, SQLExeception 예외를 발생시키게된다.
 	    
 	3. 작업 객체 생성
 	   연결된 포트를 통해 질의문을 보낼 수 있도록 도와주는 객체를 생성한다.
 	   자바에서는 크게 세 가지 방법으로 질의를 처리한다.
 	   
 	   1) Statement 객체 생성   								상위클래스
 	   		정적 질의를 일반적인 방법으로 처리할 때 주로 사용
 	   
 	   2) PreparedStatement 객체 생성							Statement하위클래스
 	   		동적 질의를 보안성을 강화하여 처리할 때 주로 사용 (전처리)(시큐어코딩의 일부)
 	   
 	   3) CallableStatement 객체 생성							PreparedStatement하위클래스
 	   		(오라클안에) 프로시저나 함수를 호출할 수 있도록 사용 
 	   		(개발자들의 사용빈도는 적지만 할 수 있으면 좋음)

 */

// 이거 복습 꼭꼭 꼭 해보기~!~!~!~!~!~!~!~!~!~!~!~!안하면 최악
package com.test;

import java.sql.Connection;
import java.sql.Statement;
import java.util.Scanner;

import com.util.DBConn;

public class Test003
{
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		Connection conn = DBConn.getConnection();
		
		do
		{
			System.out.print("번호를 입력하세요(-1 종료) : ");  // "3"
			String sid = sc.next();
			
			if (sid.equals("-1"))
			{
				break;
			}
			
			System.out.print("이름을 입력하세요 : ");
			String name = sc.next();
			
			System.out.print("전화번호를 입력하세요 : ");
			String tel = sc.next();
			
			if (conn != null)
			{
				System.out.println("데이터베이스 연결 성공~!!!");
				
				try
				{
					// 작업 객체 생성
					Statement stmt = conn.createStatement();
					
					// 쿼리문 준비
					String sql = String.format("INSERT INTO TBL_MEMBER(SID, NAME, TEL) VALUES(%s, '%s', '%s')"
												, sid, name, tel);
					// "INSERT INTO TBL_MEMBER(SID, NAME, TEL) VALUES(2, '민찬우', '010-2222-2222')"
					
					// 쿼리문 수행
					// - 데이터베이스로 부터 질의 결과를 가져와야 하는 경우 (select 일 경우)
					// → 『excuteQuery()』 메소드 사용
					
					// - 특정 내용을 데이터베이스에 적용해야 하는 경우 (DML 일 경우)
					//→ 『excuteUpdate()』 메소드 사용
					int result = stmt.executeUpdate(sql);
					
					if (result > 0)
					{
						System.out.println("회원 데이터가 입력되었습니다.");
						
					}
					
				} catch (Exception e)
				{
					System.out.println(e.toString());
				}
			}
			
			
			
		} while (true);
		
		sc.close();
		DBConn.close();
		
		System.out.println("데이터베이스 연결 닫힘~!!!");
		System.out.println("프로그램 종료됨~!!!");
		
	
		
				
	}//end main()


}//end class Test003

/*
 번호를 입력하세요(-1 종료) : 3
이름을 입력하세요 : 최나윤
전화번호를 입력하세요 : 010-3333-3333
데이터베이스 연결 성공~!!!
회원 데이터가 입력되었습니다.
번호를 입력하세요(-1 종료) : 4
이름을 입력하세요 : 조현하
전화번호를 입력하세요 : 010-4444-4444
데이터베이스 연결 성공~!!!
회원 데이터가 입력되었습니다.
번호를 입력하세요(-1 종료) : 5
이름을 입력하세요 : 김유림
전화번호를 입력하세요 : 010-5555-5555
데이터베이스 연결 성공~!!!
회원 데이터가 입력되었습니다.
번호를 입력하세요(-1 종료) : -1
데이터베이스 연결 닫힘~!!!
프로그램 종료됨~!!!
  */











