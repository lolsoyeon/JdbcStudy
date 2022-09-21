/*
 	MemberDAO
 */
package com.test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.util.DBConn;

public class MemberDAO
{
	// 연결 객체
	private Connection conn;

	// 주요 기능 구성
	// 데이터 베이스 연결 담당 메소드 정의
	public Connection connection()
	{
		conn = DBConn.getConnection();
		return conn;
	}

	// 데이터 입력 담당 메소드 inert 쿼리문 수행 update 행의 갯수 반환
	public int add(MemberDTO dto) throws SQLException
	{
		int result = 0;
		Statement stmt = conn.createStatement();
		String sql = String.format("INSERT INTO TBL_EMP (EMP_ID, EMP_NAME, SSN, IBSADATE"
						+ ", CITY_ID, TEL, BUSEO_ID, JIKWI_ID, BASICPAY, SUDANG)"
						+ " VALUES (EMPSEQ.NEXTVAL, '%s', '%s', '%s'"
						+ ", %d, '%s', %d, %d, %d, %d)"
						, dto.getEmp_name(), dto.getSsn(), dto.getIbsadate(), dto.getCity_id(), dto.getTel()
						, dto.getBuseo_id(), dto.getJikwi_id(), dto.getBasicpay(), dto.getSudang());

		result = stmt.executeUpdate(sql);
		stmt.close();

		return result;
	}
	// 기본급 조회가능한 메소드
	
	// 전체 리스트 출력 메소드  
		public ArrayList<MemberDTO> list() throws SQLException
		{
			ArrayList<MemberDTO> result = new ArrayList<MemberDTO>();

			Statement stmt = conn.createStatement();
			String sql = "SELECT EMP_ID, EMP_NAME, SSN, IBSADATE, CITY_ID, TEL"
						+ ", BUSEO_ID, JIKWI_ID, BASICPAY, SUDANG, BASICPAY+SUDANG AS PAY"
						+ " FROM TBL_EMP";

			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next())
			{
				MemberDTO dto = new MemberDTO();

				dto.setEmp_id(rs.getInt("EMP_ID"));
				dto.setEmp_name(rs.getNString("EMP_NAME"));
				dto.setSsn(rs.getNString("SSN"));
				dto.setIbsadate(rs.getString("IBSADATE"));
				dto.setCity_id(rs.getInt("CITY_ID"));
				dto.setTel(rs.getString("TEL"));
				dto.setBuseo_id(rs.getInt("BUSEO_ID"));
				dto.setJikwi_id(rs.getInt("JIKWI_ID"));
				dto.setBasicpay(rs.getInt("BASICPAY"));
				dto.setSudang(rs.getInt("SUDANG"));
				dto.setPay(rs.getInt("PAY"));

				result.add(dto);

			}
			rs.close();
			stmt.close();

			return result;

		}
	
	
	
	


	// 전체 리스트 출력 메소드 - 사번 정렬  listSabun()
	public ArrayList<MemberDTO> listSabun() throws SQLException
	{
		ArrayList<MemberDTO> result = new ArrayList<MemberDTO>();

		Statement stmt = conn.createStatement();
		String sql = "SELECT EMP_ID, EMP_NAME, SSN, IBSADATE, CITY_ID, TEL"
					+ ", BUSEO_ID, JIKWI_ID, BASICPAY, SUDANG, BASICPAY+SUDANG AS PAY"
					+ " FROM TBL_EMP"
					+ " ORDER BY EMP_ID ASC";

		ResultSet rs = stmt.executeQuery(sql);
		while (rs.next())
		{
			MemberDTO dto = new MemberDTO();

			dto.setEmp_id(rs.getInt("EMP_ID"));
			dto.setEmp_name(rs.getNString("EMP_NAME"));
			dto.setSsn(rs.getNString("SSN"));
			dto.setIbsadate(rs.getString("IBSADATE"));
			dto.setCity_id(rs.getInt("CITY_ID"));
			dto.setTel(rs.getString("TEL"));
			dto.setBuseo_id(rs.getInt("BUSEO_ID"));
			dto.setJikwi_id(rs.getInt("JIKWI_ID"));
			dto.setBasicpay(rs.getInt("BASICPAY"));
			dto.setSudang(rs.getInt("SUDANG"));
			dto.setPay(rs.getInt("PAY"));

			result.add(dto);

		}
		rs.close();
		stmt.close();

		return result;

	}

	// 전체 리스트 출력 메소드 - 이름 정렬 
	public ArrayList<MemberDTO> listName() throws SQLException
	{
		ArrayList<MemberDTO> result = new ArrayList<MemberDTO>();

		Statement stmt = conn.createStatement();
		String sql = "SELECT EMP_ID, EMP_NAME, SSN, IBSADATE, CITY_ID, TEL"
					+ ", BUSEO_ID, JIKWI_ID, BASICPAY, SUDANG, BASICPAY+SUDANG AS PAY"
					+ " FROM TBL_EMP"
					+ " ORDER BY EMP_NAME ASC";

		ResultSet rs = stmt.executeQuery(sql);
		while (rs.next())
		{
			MemberDTO dto = new MemberDTO();

			dto.setEmp_id(rs.getInt("EMP_ID"));
			dto.setEmp_name(rs.getNString("EMP_NAME"));
			dto.setSsn(rs.getNString("SSN"));
			dto.setIbsadate(rs.getString("IBSADATE"));
			dto.setCity_id(rs.getInt("CITY_ID"));
			dto.setTel(rs.getString("TEL"));
			dto.setBuseo_id(rs.getInt("BUSEO_ID"));
			dto.setJikwi_id(rs.getInt("JIKWI_ID"));
			dto.setBasicpay(rs.getInt("BASICPAY"));
			dto.setSudang(rs.getInt("SUDANG"));
			dto.setPay(rs.getInt("PAY"));

			result.add(dto);

		}
		rs.close();
		stmt.close();

		return result;

	}
	// 전체 리스트 출력 메소드 - 부서 정렬  
	public ArrayList<MemberDTO> listBuseo() throws SQLException
	{
		ArrayList<MemberDTO> result = new ArrayList<MemberDTO>();

		Statement stmt = conn.createStatement();
		String sql = "SELECT EMP_ID, EMP_NAME, SSN, IBSADATE, CITY_ID, TEL"
					+ ", BUSEO_ID, JIKWI_ID, BASICPAY, SUDANG, BASICPAY+SUDANG AS PAY"
					+ " FROM TBL_EMP"
					+ " ORDER BY BUSEO_ID ASC";

		ResultSet rs = stmt.executeQuery(sql);
		while (rs.next())
		{
			MemberDTO dto = new MemberDTO();

			dto.setEmp_id(rs.getInt("EMP_ID"));
			dto.setEmp_name(rs.getNString("EMP_NAME"));
			dto.setSsn(rs.getNString("SSN"));
			dto.setIbsadate(rs.getString("IBSADATE"));
			dto.setCity_id(rs.getInt("CITY_ID"));
			dto.setTel(rs.getString("TEL"));
			dto.setBuseo_id(rs.getInt("BUSEO_ID"));
			dto.setJikwi_id(rs.getInt("JIKWI_ID"));
			dto.setBasicpay(rs.getInt("BASICPAY"));
			dto.setSudang(rs.getInt("SUDANG"));
			dto.setPay(rs.getInt("PAY"));

			result.add(dto);

		}
		rs.close();
		stmt.close();

		return result;

	}
	// 전체 리스트 출력 메소드 - 직위 정렬
	public ArrayList<MemberDTO> listJikwi() throws SQLException
	{
		ArrayList<MemberDTO> result = new ArrayList<MemberDTO>();

		Statement stmt = conn.createStatement();
		String sql = "SELECT EMP_ID, EMP_NAME, SSN, IBSADATE, CITY_ID, TEL"
					+ ", BUSEO_ID, JIKWI_ID, BASICPAY, SUDANG, BASICPAY+SUDANG AS PAY"
					+ " FROM TBL_EMP"
					+ " ORDER BY JIKWI_ID ASC";

		ResultSet rs = stmt.executeQuery(sql);
		while (rs.next())
		{
			MemberDTO dto = new MemberDTO();

			dto.setEmp_id(rs.getInt("EMP_ID"));
			dto.setEmp_name(rs.getNString("EMP_NAME"));
			dto.setSsn(rs.getNString("SSN"));
			dto.setIbsadate(rs.getString("IBSADATE"));
			dto.setCity_id(rs.getInt("CITY_ID"));
			dto.setTel(rs.getString("TEL"));
			dto.setBuseo_id(rs.getInt("BUSEO_ID"));
			dto.setJikwi_id(rs.getInt("JIKWI_ID"));
			dto.setBasicpay(rs.getInt("BASICPAY"));
			dto.setSudang(rs.getInt("SUDANG"));
			dto.setPay(rs.getInt("PAY"));

			result.add(dto);

		}
		rs.close();
		stmt.close();

		return result;

	}
	// 전체 리스트 출력 메소드 - 급여내림차순 정렬
	public ArrayList<MemberDTO> listPay() throws SQLException
	{
		ArrayList<MemberDTO> result = new ArrayList<MemberDTO>();

		Statement stmt = conn.createStatement();
		String sql = "SELECT EMP_ID, EMP_NAME, SSN, IBSADATE, CITY_ID, TEL"
					+ ", BUSEO_ID, JIKWI_ID, BASICPAY, SUDANG, BASICPAY+SUDANG AS PAY"
					+ " FROM TBL_EMP"
					+ " ORDER BY BUSEO_NAME DSC";

		ResultSet rs = stmt.executeQuery(sql);
		while (rs.next())
		{
			MemberDTO dto = new MemberDTO();

			dto.setEmp_id(rs.getInt("EMP_ID"));
			dto.setEmp_name(rs.getNString("EMP_NAME"));
			dto.setSsn(rs.getNString("SSN"));
			dto.setIbsadate(rs.getString("IBSADATE"));
			dto.setCity_id(rs.getInt("CITY_ID"));
			dto.setTel(rs.getString("TEL"));
			dto.setBuseo_id(rs.getInt("BUSEO_ID"));
			dto.setJikwi_id(rs.getInt("JIKWI_ID"));
			dto.setBasicpay(rs.getInt("BASICPAY"));
			dto.setSudang(rs.getInt("SUDANG"));
			dto.setPay(rs.getInt("PAY"));

			result.add(dto);

		}
		rs.close();
		stmt.close();

		return result;

	}
	
	
	// 인원 수 확인(전체) 담당 메소드
	public int count() throws SQLException
	{
		int result = 0;
		Statement stmt = conn.createStatement();
		String sql = "SELECT COUNT(*) AS COUNT FROM TLB_EMP";

		ResultSet rs = stmt.executeQuery(sql);
		while (rs.next())
			result = rs.getInt("COUNT");
		rs.close();
		stmt.close();

		return result;
	}

	// - 직윈 이름 검색 담당 메소드 nameList
	public ArrayList<MemberDTO> nameList(String emp_name) throws SQLException
	{
		ArrayList<MemberDTO> result = new ArrayList<MemberDTO>();

		Statement stmt = conn.createStatement();
		String sql = String.format("SELECT EMP_ID, EMP_NAME, SSN, IBSADATE, CITY_ID"
				+ ", TEL, BUSEO_ID, JIKWI_ID, BASICPAY, SUDANG, BASICPAY+SUDANG AS PAY" 
				+ " FROM TBL_EMP" 
				+ " WHERE NAME LIKE '%%%s%%'", emp_name);
		ResultSet rs = stmt.executeQuery(sql);
		while (rs.next())
		{
			// 사번 이름 주민번호 입사일 지역 전화번호 부서 직위 기본급 수당 급여
			MemberDTO dto = new MemberDTO();

			dto.setEmp_id(rs.getInt("EMP_ID"));
			dto.setEmp_name(rs.getNString("EMP_NAME"));
			dto.setSsn(rs.getNString("SSN"));
			dto.setIbsadate(rs.getString("IBSADATE"));
			dto.setCity_id(rs.getInt("CITY_ID"));
			dto.setTel(rs.getString("TEL"));
			dto.setBuseo_id(rs.getInt("BUSEO_ID"));
			dto.setJikwi_id(rs.getInt("JIKWI_ID"));
			dto.setBasicpay(rs.getInt("BASICPAY"));
			dto.setSudang(rs.getInt("SUDANG"));
			dto.setPay(rs.getInt("PAY"));

			result.add(dto);

		}
		rs.close();
		stmt.close();

		return result;

	}

	// - 사번 검색 담당 메소드 empList
	public ArrayList<MemberDTO> empList(int emp_id) throws SQLException
	{
		ArrayList<MemberDTO> result = new ArrayList<MemberDTO>();

		Statement stmt = conn.createStatement();
		String sql = String.format("SELECT EMP_ID, EMP_NAME, SSN, IBSADATE, CITY_ID"
				+ ", TEL, BUSEO_ID, JIKWI_ID, BASICPAY, SUDANG, BASICPAY+SUDANG AS PAY" 
				+ "  FROM TBL_EMP" 
				+ " WHERE EMP_ID = %d", emp_id);
		ResultSet rs = stmt.executeQuery(sql);
		while (rs.next())
		{
			// 사번 이름 주민번호 입사일 지역 전화번호 부서 직위 기본급 수당 급여
			MemberDTO dto = new MemberDTO();

			dto.setEmp_id(rs.getInt("EMP_ID"));
			dto.setEmp_name(rs.getNString("EMP_NAME"));
			dto.setSsn(rs.getNString("SSN"));
			dto.setIbsadate(rs.getString("IBSADATE"));
			dto.setCity_id(rs.getInt("CITY_ID"));
			dto.setTel(rs.getString("TEL"));
			dto.setBuseo_id(rs.getInt("BUSEO_ID"));
			dto.setJikwi_id(rs.getInt("JIKWI_ID"));
			dto.setBasicpay(rs.getInt("BASICPAY"));
			dto.setSudang(rs.getInt("SUDANG"));
			dto.setPay(rs.getInt("PAY"));
			

			result.add(dto);

		}
		rs.close();
		stmt.close();

		return result;

	}// end EmpList(int emp_id)

	// 부서 이름으로 검색 담당 메소드 buseoList
	public ArrayList<MemberDTO> buseoList(String buseo_name) throws SQLException
	{
		ArrayList<MemberDTO> result = new ArrayList<MemberDTO>();

		Statement stmt = conn.createStatement();
		String sql = String.format("SELECT EMP_ID, EMP_NAME, SSN, IBSADATE, CITY_ID"
				+ ", TEL, BUSEO_ID, JIKWI_ID, BASICPAY, SUDANG, BASICPAY+SUDANG AS PAY" 
				+ "  FROM TBL_EMP" 
				+ " WHERE BUSEO_NAME = '%%%s%%'", buseo_name);
		ResultSet rs = stmt.executeQuery(sql);
		while (rs.next())
		{
			// 사번 이름 주민번호 입사일 지역 전화번호 부서 직위 기본급 수당 급여
			MemberDTO dto = new MemberDTO();

			dto.setEmp_id(rs.getInt("EMP_ID"));
			dto.setEmp_name(rs.getNString("EMP_NAME"));
			dto.setSsn(rs.getNString("SSN"));
			dto.setIbsadate(rs.getString("IBSADATE"));
			dto.setCity_id(rs.getInt("CITY_ID"));
			dto.setTel(rs.getString("TEL"));
			dto.setBuseo_id(rs.getInt("BUSEO_ID"));
			dto.setJikwi_id(rs.getInt("JIKWI_ID"));
			dto.setBasicpay(rs.getInt("BASICPAY"));
			dto.setSudang(rs.getInt("SUDANG"));
			dto.setPay(rs.getInt("PAY"));

			result.add(dto);

		}
		rs.close();
		stmt.close();

		return result;

	}// end Buseolist(String buseo_name)

	// 직위 이름으로 검색 담당 메소드 jikwiList
	public ArrayList<MemberDTO> jikwiList(String jikwi_name) throws SQLException
	{
		ArrayList<MemberDTO> result = new ArrayList<MemberDTO>();

		Statement stmt = conn.createStatement();
		String sql = String.format("SELECT EMP_ID, EMP_NAME, SSN, IBSADATE, CITY_ID"
				+ ", TEL, BUSEO_ID, JIKWI_ID, BASICPAY, SUDANG, BASICPAY+SUDANG AS PAY" 
				+ "  FROM TBL_EMP" 
				+ " WHERE JIKWI_NAME = '%%%s%%'", jikwi_name);
		ResultSet rs = stmt.executeQuery(sql);
		while (rs.next())
		{
			// 사번 이름 주민번호 입사일 지역 전화번호 부서 직위 기본급 수당 급여
			MemberDTO dto = new MemberDTO();

			dto.setEmp_id(rs.getInt("EMP_ID"));
			dto.setEmp_name(rs.getNString("EMP_NAME"));
			dto.setSsn(rs.getNString("SSN"));
			dto.setIbsadate(rs.getString("IBSADATE"));
			dto.setCity_id(rs.getInt("CITY_ID"));
			dto.setTel(rs.getString("TEL"));
			dto.setBuseo_id(rs.getInt("BUSEO_ID"));
			dto.setJikwi_id(rs.getInt("JIKWI_ID"));
			dto.setBasicpay(rs.getInt("BASICPAY"));
			dto.setSudang(rs.getInt("SUDANG"));
			dto.setPay(rs.getInt("PAY"));

			result.add(dto);

		}
		rs.close();
		stmt.close();

		return result;

	}// end jikwiList(String jikwi_name)
	
	
	

	// - 데이터 수정 담당 메소드 ★기억하기 ScoreDTO dto update 행의 갯수
	public int modify(MemberDTO dto) throws SQLException
	{
		int result = 0;
		Statement stmt = conn.createStatement();
		String sql = String.format( "UPDATE TBL_EMP" 
						+ " SET EMP_NAME = '%s'" 
						+ ", SSN = '%s', IBSADATE = '%s'" 
						+ ", TEL = '%s'"
						+ " WHERE EMP_ID = %d",
				dto.getEmp_name(), dto.getSsn(), dto.getIbsadate(), dto.getTel(), dto.getEmp_id());

		result = stmt.executeUpdate(sql);
		stmt.close();

		return result;
	}

	// - 데이터 삭제 담당 메소드
	public int remove(int emp_id) throws SQLException
	{
		int result = 0;
		
		Statement stmt = conn.createStatement();
		String sql = String.format("DELETE FROM TBL_EMP WHERE EMP_ID = %d", emp_id);
		stmt.close();
		
		
		return result;
	}
	
	
	// - 데이터 연결 종료 담당 메소드 왜 보이드였지?????
	public void close()
	{
		DBConn.close();
	}


}//end MemberDAO
