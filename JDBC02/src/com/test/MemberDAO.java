/*=========================================
  MemberDAO.java
  - 데이터베이스 액션 처리 전용 객체 구성  (제일 복습해야할 클래스)
=========================================== */

package com.test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.util.DBConn;

public class MemberDAO
{
	// 주요 변수 선언(주요 속성 구성) → DB 연결 객체
	private Connection conn;

	// 생성자 정의 → 사용자 정의 생성자
	// 왜 여기서 예외발생하는지 공부하기
	public MemberDAO() throws ClassNotFoundException, SQLException
	{
		// DB 연결
		conn = DBConn.getConnection();
	}

	// 기능 → 메소드 정의 → 데이터를 입력하는 기능 → INSERT 쿼리문 수행
	public int add(MemberDTO dto) throws SQLException // name, tel
	{
		// 커넥션 작업 객체 sql excute update

		// 반환할 결과값을 담을 변수(적용된 행의 갯수)
		int result = 1;

		// 작업 객체 생성
		Statement stmt = conn.createStatement();

		// ※ Statement 종류
		// - Statement
		// 하나의 쿼리를 사용하고나면 더 이상 사용할 수 없다.
		// - PreparedStatement
		// 하나의 PreparedStatement 로 쿼리를 여러 번 처리할 수 있다.
		// - CallableStatement
		// 데이터베이스 내의 스토어드 프로시저나 함수 등을 호출할 수 있다.

		// ※ Statement 의 의미
		// 자바에서 사용되는 3가지 종류의 작업 객체들은
		// 데이터베이스로 쿼리를 담아서 보내는 그릇 정도로 생각하자.
		// 즉, 작업 객체에 쿼리를 실어 데이터베이스로 보내버리면
		// 그 내용이 데이터베이스 내에서 처리되는 것이다.
		// 이 때, 한 번 사용 하고 버리는 그릇은 statement 이며.
		// 재사용이 가능한 그릇은 PreparedStatment 이다.

		// 쿼리문 준비
		String sql = String.format("INSERT INTO TBL_MEMBER(SID, NAME, TEL)" + "VALUES(MEMBERSEQ.NEXTVAL, '%s','%s')",
				dto.getName(), dto.getTel());

		// 쿼리문 수행
		result = stmt.executeUpdate(sql);

		// 최종 결과값 반환

		return result;
	}// end add()

	// 기능 → 메소드 정의 → 인원수를 파악하는 기능 → select 쿼리문 수행
	// 이건 인원수를 얻어내는 기능이라서 int 자료형 executeQuary ResultSet
	public int count() throws SQLException
	{
		// 결과값으로 반환하게 될 변수 선언 및 초기화
		int result = 0;

		// 작업객체 생성
		Statement stmt = conn.createStatement();

		// 쿼리문 준비
		String sql = "SELECT COUNT(*) AS COUNT FROM TBL_MEMBER";

		// 쿼리문 실행 → select 쿼리문 → ResultSet 반환
		ResultSet rs = stmt.executeQuery(sql);

		// ResultSet 처리 → 반복문 구성

		while (rs.next()) // → if(rs.next())
							// 다중이 아니라 단일이다. 얻어온값이 있으면
		{
			result = rs.getInt("COUNT"); // → result = getInt(1); 컬럼인덱스 Test004
		}
		rs.close();
		stmt.close();

		// 최종 결과값 반환
		return result;

	}// end count()

	// 기능 → 메소드 정의 → 데이터 전체 조회하는 기능 → select 쿼리문 수행 DO!
	public ArrayList<MemberDTO> list() throws SQLException
	{
		// 결과 변수
		ArrayList<MemberDTO> result = new ArrayList<MemberDTO>();

		// 작업객체 생성
		Statement stmt = conn.createStatement();

		// 쿼리문 준비
		String sql = "SELECT SID, NAME, TEL FROM TBL_MEMBER ORDER BY SID";

		// 쿼리문 실행 → executeQuery() → ResultSet 반환
		ResultSet rs = stmt.executeQuery(sql);

		// ResultSet 처리 → 반복문 구성
		while (rs.next())
		{
			// MenberDTO 객체 생성
			MemberDTO dto = new MemberDTO();

			// 생성된 MemberDTO 객체에 속성들 채워 넣기
			dto.setSid(rs.getString("SID")); // dto.setSid(rs.getString(1));
			dto.setName(rs.getString("NAME")); // dto.setName(rs.getString(2));
			dto.setTel(rs.getString("TEL")); // dto.setTel(rs.getString(3));

			result.add(dto);

		}
		rs.close();
		stmt.close();

		// 최종 결과 값 반환
		return result;

	}

	// 반환 자료형에 대한 고찰
	/*
	 * SELECT NAME 
	 * FROM TBL_MEMBER WHERE SID = 1; → String
	 * 
	 * SELECT NAME 
	 * FROM TBL_MEMBER; → String 들 → String 배열이나 String을 요소로 취하는 자료구조
	 * 
	 * SELECT SID, NAME, TEL 
	 * FROM TBL_MEMBER WHERE SID = 1; → MenberDTO
	 * 
	 * SELECT SID, NAME, TEL 
	 * FROM TBL_MEMBER; → MenberDTO 들 → MenberDTO 배열이나
	 * MenberDTO를 요소로 취하는 자료구조
	 * 
	 */
	// 데이터 베이스 연결 종료
	public void close() throws SQLException
	{
		DBConn.close();
	}

}// end class Member
