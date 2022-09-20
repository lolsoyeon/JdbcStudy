/*=======================
   ScoreDAO.java
   - 데이터 액션 처리 전용 클래스
 =======================*/
package com.test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.util.DBConn;

public class ScoreDAO
{
	// 주요 변수 선언
	private Connection conn;
	
	// 사용자 정의 생성자
	public ScoreDAO() throws ClassNotFoundException, SQLException
	{
		conn = DBConn.getConnection();
	}
	
	// 기능 → 메소드 정의 → 데이터를 입력 하는 기능 → insert 쿼리문 수행
	public int add(ScoreDTO dto) throws SQLException
	{
		// 커넥션 작업 객체
		
		// 결과 값을 반환할 변수 (적용된 행의 갯수)
		int result = 0;
		
		//  작업 객체 생성
		Statement stmt = conn.createStatement();
		
		// insert 쿼리문 준비
		// 시퀀스 생성 sid
		String sql = String.format("INSERT INTO TBL_SCORE(SID, NAME, KOR, ENG, MAT) " +
								   "VALUES (SCORESEQ.NEXTVAL, '%s', %d, %d, %d) "				   
								   	, dto.getName(), dto.getKor(), dto.getEng(), dto.getMat() );
		// INSERT INTO(SID, NAME, KOR, ENG, MAT)  + VALUES (SCORESEQ.NEXTVAL,'%s', %d, %d, %d)
		// 쿼리문 수행
		result = stmt.executeUpdate(sql);
		
		// 결과 값 반환
		return result;
		
	}
	
	// 기능 → 메소드 정의 → 총점을 연산하는 기능 x
	
	 
	// 기능 → 메소드 정의 → 인원수를 파악하는 메소드 → select 쿼리문 수행
	public int count() throws SQLException
	{
		// 결과값으로 반환하게 될 변수 선언 및 초기화
		int result  = 0;
		
		// 작업객체 생성
		Statement stmt = conn.createStatement();
		
		// 쿼리문 준비
		String sql = "SELECT COUNT(*) AS COUNT FROM TBL_SCORE";
		
		// 쿼리문 실행 → select쿼리문 → ResultSet 숫자 반환
		ResultSet rs = stmt.executeQuery(sql);
		
		// ResultSet 처리
		while (rs.next())
		{
			result = rs.getInt("COUNT");
		}	
		rs.close();
		stmt.close();
		
		// 최종 결과 값 반환
		return result;
		
	}//end count
	
	
	// 기능 → 메소드 정의 → 데이터 전체를 조회하는 기능 → select 쿼리문 수행
	public ArrayList<ScoreDTO> list() throws SQLException
	{
		// 결과 변수
		ArrayList<ScoreDTO> result = new ArrayList<ScoreDTO>();
		
		// 작업객체 생성
		Statement stmt = conn.createStatement();
		
		// select 쿼리문 준비
		String sql = "SELECT SID , NAME, KOR , ENG, MAT , KOR+ENG+MAT AS SUM, (KOR+ENG+MAT)/ 3 AS AVG FROM TBL_SCORE ORDER BY 1 ";
		
		// 쿼리문 실행 executeQuery() → ResultSet 반환 (숫자)
		ResultSet rs = stmt.executeQuery(sql); 
	
		// ResultSet 처리 → 반복분 수행
		while (rs.next())
		{
			// ScoreDTO 객체 생성
			ScoreDTO dto = new ScoreDTO();
			
			// 생성된 Score DTO에 속성들 채워 넣기
			dto.setSid(rs.getInt("SID"));
			dto.setName(rs.getString("NAME"));
			dto.setKor(rs.getInt("KOR"));  		// dto.setKor(rs.getInt(3));
			dto.setEng(rs.getInt("ENG"));		// dto.setKor(rs.getInt(4));
			dto.setMat(rs.getInt("MAT"));		// dto.setKor(rs.getInt(5));
			dto.setSum(rs.getInt(6));
			dto.setAvg(rs.getDouble(7));
			
			
			result.add(dto);
		}
		
		rs.close();
		stmt.close();
		
		// 최종 결과값 반환
		return result;
		
	}//end list()
	
	// 데이터베이스 연결 종료 메소드
	public void close() throws SQLException
	{
		DBConn.close();
	}
	

}//end ScoreDAO
