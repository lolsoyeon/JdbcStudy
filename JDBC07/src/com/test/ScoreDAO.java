/*========================================
 	ScoreDAO
 	- 데이터베이스 액션 처리 전용 클래스
 ========================================*/
package com.test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.util.DBConn;

public class ScoreDAO
{
	// 주요 속성 구성
	// 공통적으로 가지고 있어야 하는거
	private static Connection conn;

	// 주요 기능 구성

	// 데이터베이스 연결 담당 메소드
	public Connection connection() throws ClassNotFoundException, SQLException
	{
		conn = DBConn.getConnection();
		return conn;

	}

	// 데이터베이스 입력 담당 메소드
	public int add(ScoreDTO dto) throws SQLException
	{
		int result = 0;
		// 쿼리문 준비
		String sql ="INSERT INTO TBL_SCORE(SID, NAME, KOR, ENG, MAT)" 
								+ " VALUES(SCORESEQ.NEXTVAL, ?, ?, ?, ?)";

		// 작업객체 생성
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, dto.getName());
		pstmt.setInt(2, dto.getKor());
		pstmt.setInt(3, dto.getEng());
		pstmt.setInt(4, dto.getMat());
		
		// - 작업객체 생성 과정에서 쿼리문 전달

		// 작업객체 실행
		result = pstmt.executeUpdate();

		pstmt.close();

		return result;

	}

	// ScoreDTO를 담아둘수 있는 자료구조
	// 전체 리스트 출력 담당 메소드
	public ArrayList<ScoreDTO> list() throws SQLException
	{
		ArrayList<ScoreDTO> result = new ArrayList<ScoreDTO>();

		// SELECT 쿼리문 준비
		String sql = "SELECT SID, NAME, KOR, ENG, MAT, (KOR + ENG + MAT) AS TOT"
				+ ", (KOR + ENG + MAT) / 3 AS AVG, RANK() OVER(ORDER BY (KOR + ENG + MAT) DESC) AS RANK"
				+ " FROM TBL_SCORE ORDER BY SID ASC";
		// 작업 객체 생성
		PreparedStatement pstmt = conn.prepareStatement(sql);

		// ResultSet 반환 한다 Q
		ResultSet rs = pstmt.executeQuery();

		// ResultSet 처리 → 반복문
		while (rs.next())
		{
			ScoreDTO dto = new ScoreDTO();

			dto.setSid(rs.getString("SID"));
			dto.setName(rs.getString("NAME"));
			dto.setKor(rs.getInt("KOR"));
			dto.setEng(rs.getInt("ENG"));
			dto.setMat(rs.getInt("MAT"));
			dto.setTot(rs.getInt("TOT"));
			dto.setRank(rs.getInt("RANK"));
			dto.setAvg(rs.getDouble("AVG"));

			result.add(dto);
		}
		rs.close();
		pstmt.close();
		return result;

	}

	// 인원수 확인 메소드
	public int count() throws SQLException
	{
		int result = 0;

		// 쿼리문 준비
		String sql = "SELECT COUNT(*) AS COUNT FROM TBL_SCORE";
		// 작업 객체 생성
		PreparedStatement pstmt = conn.prepareStatement(sql);

		// ResultSet
		ResultSet rs = pstmt.executeQuery();

		// ResultSet 처리 → 반복문
		while (rs.next())
		{
			result = rs.getInt("COUNT");

		}
		rs.close();
		pstmt.close();

		return result;

	}

	// 이름 검색 담당 메소드
	public ArrayList<ScoreDTO> list(String name) throws SQLException
	{
		ArrayList<ScoreDTO> result = new ArrayList<ScoreDTO>();

		// 쿼리문 준비 SELECT 쿼리 RESULTSET
		String sql = "SELECT SID, NAME, KOR, ENG, MAT, TOT, AVG, RANK"
				+ " FROM"
				+ " (SELECT SID, NAME, KOR, ENG, MAT,"
				+ " (KOR + ENG + MAT) AS TOT"
				+ ", (KOR + ENG + MAT) / 3 AS AVG"
				+ ", RANK() OVER(ORDER BY (KOR + ENG + MAT) DESC) AS RANK"
				+ " FROM TBL_SCORE)"
				+ "WHERE NAME LIKE ? ";
		// 작업 객체 생성
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		pstmt.setString(1, name);

		ResultSet rs = pstmt.executeQuery();
		

//		pstmt.setString(1, name);


		// ResultSet 처리 반복문
		while (rs.next())
		{
			// dto 객체 생성 깡통에 채워 넣기
			ScoreDTO dto = new ScoreDTO();

			dto.setSid(rs.getString("SID"));
			dto.setName(rs.getString("NAME"));
			dto.setKor(rs.getInt("KOR"));
			dto.setEng(rs.getInt("ENG"));
			dto.setMat(rs.getInt("MAT"));
			dto.setTot(rs.getInt("TOT"));
			dto.setRank(rs.getInt("RANK"));
			dto.setAvg(rs.getDouble("AVG"));
			
			result.add(dto);

		}
		rs.close();
		pstmt.close();
		return result;

	}

	// 번호 검색 담당 메소드
	public ArrayList<ScoreDTO> list(int sid) throws SQLException
	{
		ArrayList<ScoreDTO> result = new ArrayList<ScoreDTO>();
		
		// 쿼리문 준비 SELECE executequery ResultSet
		String sql = "SELECT SID, NAME, KOR, ENG, MAT, TOT, AVG, RANK"
				+ " FROM"
				+ " (SELECT SID, NAME, KOR, ENG, MAT"
				+ ", (KOR + ENG + MAT) AS TOT"
				+ ", (KOR + ENG + MAT) / 3 AS AVG"
				+ ", RANK() OVER(ORDER BY (KOR + ENG + MAT) DESC) AS RANK FROM TBL_SCORE)"
				+ " WHERE SID = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		pstmt.setInt(1, sid);

		
		ResultSet rs = pstmt.executeQuery();
		
		while (rs.next())
		{
			// ScoreDTO 객체 생성 먼저 해야한다. 깡통
			ScoreDTO dto = new ScoreDTO();
			
			dto.setSid(rs.getString("SID"));
			dto.setName(rs.getString("NAME"));
			dto.setKor(rs.getInt("KOR"));
			dto.setEng(rs.getInt("ENG"));
			dto.setMat(rs.getInt("MAT"));
			dto.setTot(rs.getInt("TOT"));
			dto.setAvg(rs.getDouble("AVG"));
			dto.setRank(rs.getInt("RANK"));
			
			result.add(dto);
		}
		
		rs.close();
		pstmt.close();
		return result;
		
		
	}
	// 데이터 수정 담당 메소드
	public int modify(ScoreDTO dto) throws SQLException
	{
		int result = 0;
		
		
		String sql = "UPDATE TBL_SCORE"
				+ " SET NAME = ?"
				+ ", KOR = ?, ENG = ?, MAT = ?"
				+ " WHERE SID = ?";
	
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, dto.getName());
		pstmt.setInt(2, dto.getKor());
		pstmt.setInt(3, dto.getEng());
		pstmt.setInt(4, dto.getMat());
		pstmt.setString(5, dto.getSid());
		
		result = pstmt.executeUpdate();
		
		pstmt.close();
		
		return result;
		
	}
	// - 데이터 삭제 담당 메소드
	public int remove(int sid) throws SQLException
	{
		int result = 0;
		
		String sql = "DELETE FROM TBL_SCORE WHERE SID = ?";
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, sid);
		
		result = pstmt.executeUpdate();
		
		pstmt.close();
		
		
		return result;
	}
	

	// - 데이터 연결 종료 담당 메소드
	public void close() throws SQLException
	{
		DBConn.close();
	}
	

}
