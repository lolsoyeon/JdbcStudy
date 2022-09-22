/*
   	Process.java
 */
package com.test;

import java.util.ArrayList;
import java.util.Scanner;

public class Process
{
	// 주요 속성 구성
	private MemberDAO dao;

	// 사용자 정의 생성자
	public Process()
	{
		dao = new MemberDAO();
	}

	// 주요 기능 구성
	// 1. 직원 정보 입력기능
	public void JikwonInsert()
	{
		try
		{
			dao.connection();

			// int count = dao.count();
			Scanner sc = new Scanner(System.in);

			do
			{
				System.out.println();
				System.out.println("직원정보 입력--------------------------------------------");
				System.out.print(" 이름 : ");
				String emp_name = sc.next();
				if (emp_name.equals("."))
				{
					break;
				}
				System.out.print("주민등번호(yymmdd-nnnnnnn) : ");
				String ssn = sc.next();
				System.out.print("입사일(yyyy-mm-dd) : ");
				String ibsadate = sc.next();
				System.out.print("지역(강원/경기/경남/경북/부산/서울/인천/전남/전북/제주/충남/) : ");
				String city_loc = sc.next();
				int cr = dao.getCity(city_loc);
				if (cr == 0)
				{
					System.out.println("존재하지 않는 지역입니다.");
					return;

				}
				System.out.print("전화번호 : ");
				String tel = sc.next();
				System.out.print("부서(개발부/기획부/영업부/인사부/자재부/총무부/홍보부/) : ");
				String buseo_name = sc.next();
				int br = dao.getBuseo(buseo_name);
				if (br == 0)
				{
					System.out.println("존재하지 않는 부서입니다.");
					return;

				}
				System.out.print("직위(사장/전무/상무/이사/부장/차장/과장/대리/사원/) : ");
				String jikwi_name = sc.next();
				int jr = dao.getJikwi(jikwi_name);

				System.out.print("기본급 (최소 기본급 ) : ");
				int basicpay = sc.nextInt();
				System.out.print("수당 : ");
				int sudang = sc.nextInt();

				MemberDTO dto = new MemberDTO();
				dto.setEmp_name(emp_name);
				dto.setSsn(ssn);
				dto.setIbsadate(ibsadate);
				dto.setCity_id(cr);
				dto.setTel(tel);
				dto.setBuseo_id(br);
				dto.setJikwi_id(jr);
				dto.setBasicpay(basicpay);
				dto.setSudang(sudang);

				int result = dao.add(dto);
				if (result > 0)
				{
					System.out.println(">> 직원 정보 입력 완료~!!!!");
				}

			} while (true);

			dao.close();

		} catch (Exception e)
		{
			System.out.println(e.toString());
		}
	}

	////////// 2. 직원 전체 출력 기능

	// 직원 전체 출력기능 타고들어가기
	public void MemberSelectAll()
	{
		// System.out.println("테스트");
		try
		{
			// 데이터베이스 연결
			dao.connection();

			int count = dao.count();

			Scanner sc = new Scanner(System.in);

			System.out.println();
			System.out.println("원하는 정렬 방식을 선택해 주세요 (1~5)");
			System.out.println("(1) 사번 정렬	");
			System.out.println("(2) 이름 정렬   ");
			System.out.println("(3) 부서 정렬   ");
			System.out.println("(4) 직위 정렬   ");
			System.out.println("(5) 급여 내림차순 정렬");
			System.out.print(">> 번호 선택 :   ");

			int num = sc.nextInt();

			if (num <= 0 || num >= 6)
			{
				System.out.println("잘못된 입력입니다.");
				return;
			}
			switch (num)
			{
			case 1:
				// 사번 정렬 메소드 호출
//				dao.listSabun();
				System.out.println();
				System.out.printf("전체인원 : %d명\n", count);
				System.out.println("사번  이름   주민번호          입사일    지역       전화번호   부서   직위   기본급   수당   급여");

				for (MemberDTO dto : dao.listSabun())
				{
					System.out.printf("%4d %4s %15s %10s %4s %11s %4s %4s %7d %7d %10d\n", dto.getEmp_id(),
							dto.getEmp_name(), dto.getSsn(), dto.getIbsadate(), dto.getCity_loc(), dto.getTel(),
							dto.getBuseo_name(), dto.getJikwi_name(), dto.getBasicpay(), dto.getSudang(), dto.getPay());
				}
				dao.close();

				break;
			case 2:
				// 이름 정렬 메소드 호출
//				dao.listName();
				System.out.println();
				System.out.printf("전체인원 : %d명\n", count);
				System.out.println("사번  이름      주민번호     입사일      지역       전화번호   부서   직위   기본급   수당   급여");

				for (MemberDTO dto : dao.listName())
				{
					System.out.printf("%4d %4s %15s %10s %4s %11s %4s %4s %7d %7d %10d\n", dto.getEmp_id(),
							dto.getEmp_name(), dto.getSsn(), dto.getIbsadate(), dto.getCity_loc(), dto.getTel(),
							dto.getBuseo_name(), dto.getJikwi_name(), dto.getBasicpay(), dto.getSudang(), dto.getPay());
				}
				dao.close();
				break;
			case 3:
				// 부서 정렬 메소드 호출
//				dao.listBuseo();
				System.out.println();
				System.out.printf("전체인원 : %d명\n", count);
				System.out.println("사번  이름      주민번호     입사일      지역       전화번호   부서   직위   기본급   수당   급여");

				for (MemberDTO dto : dao.listBuseo())
				{
					System.out.printf("%4d %4s %15s %10s %4s %11s %4s %4s %7d %7d %10d\n", dto.getEmp_id(),
							dto.getEmp_name(), dto.getSsn(), dto.getIbsadate(), dto.getCity_loc(), dto.getTel(),
							dto.getBuseo_name(), dto.getJikwi_name(), dto.getBasicpay(), dto.getSudang(), dto.getPay());
				}
				dao.close();
				break;

			case 4:
				// 직위 정렬 메소드 호출
//				dao.listJikwi();
				System.out.println();
				System.out.printf("전체인원 : %d명\n", count);
				System.out.println("사번  이름      주민번호     입사일      지역       전화번호   부서   직위   기본급   수당   급여");

				for (MemberDTO dto : dao.listJikwi())
				{
					System.out.printf("%4d %4s %15s %10s %4s %11s %4s %4s %7d %7d %10d\n", dto.getEmp_id(),
							dto.getEmp_name(), dto.getSsn(), dto.getIbsadate(), dto.getCity_loc(), dto.getTel(),
							dto.getBuseo_name(), dto.getJikwi_name(), dto.getBasicpay(), dto.getSudang(), dto.getPay());
				}
				break;
			case 5:
				// 급여 내림차순 메소드 호출
//				dao.listPay();
				System.out.println();
				System.out.printf("전체인원 : %d명\n", count);
				System.out.println("사번  이름      주민번호     입사일      지역       전화번호   부서   직위   기본급   수당   급여");
				for (MemberDTO dto : dao.listPay())
				{
					System.out.printf("%4d %4s %15s %10s %4s %11s %4s %4s %7d %7d %10d\n", dto.getEmp_id(),
							dto.getEmp_name(), dto.getSsn(), dto.getIbsadate(), dto.getCity_loc(), dto.getTel(),
							dto.getBuseo_name(), dto.getJikwi_name(), dto.getBasicpay(), dto.getSudang(), dto.getPay());
				}
				break;

			}

		} catch (Exception e)
		{
			System.out.println(e.toString());
		}

	}

	// 직원 검색 출력 기능 SY 내꺼~!!~!~!
	public void jikwonSerch()
	{

		// 데이터베이스 연결
		dao.connection();

//			int count = dao.count();

		Scanner sc = new Scanner(System.in);

		System.out.println();
		System.out.println("원하는 검색 방법을 택해주세요(1~4)");
		System.out.println("(1) 사번 검색");
		System.out.println("(2) 이름 검색");
		System.out.println("(3) 부서 검색");
		System.out.println("(4) 직위 검색");
		System.out.print(">> 번호 선택 : ");

		String menus = sc.next();
		try
		{
			int menu = Integer.parseInt(menus);
			if (menu == -1)
			{
				System.out.println();
				System.out.println("검색을 종료하였습니다.");
				return;
			}

			switch (menu)
			{
			case 1:
				// 사번검색 메소드 호출 후 입력받기
				System.out.print("검색할 사원 번호 입력 : ");
				int emp_id = sc.nextInt();
				ArrayList<MemberDTO> empList = dao.empList(emp_id);
				if (empList.size() > 0)
				{
					System.out.println("사번  이름      주민번호     입사일      지역       전화번호   부서   직위   기본급   수당   급여");
					for (MemberDTO dto : dao.empList(emp_id))
					{
						System.out.printf("%4d %4s %15s %10s %4s %11s %4s %4s %7d %7d %10d\n", dto.getEmp_id(),
								dto.getEmp_name(), dto.getSsn(), dto.getIbsadate(), dto.getCity_loc(), dto.getTel(),
								dto.getBuseo_name(), dto.getJikwi_name(), dto.getBasicpay(), dto.getSudang(),
								dto.getPay());

					}
				} else
				{
					System.out.println("검색한 사원이 존재하지 않습니다 ");
				}
				break;

			case 2:
				// 이름검색 메소드 호출후 입력받기
				System.out.print("검색할 이름 입력 : ");
				String emp_name = sc.next();
				ArrayList<MemberDTO> nameList = dao.nameList(emp_name);
				if (nameList.size() > 0)
				{
					System.out.println("사번  이름      주민번호     입사일      지역       전화번호   부서   직위   기본급   수당   급여");
					for (MemberDTO dto : dao.nameList(emp_name))
					{
						System.out.printf("%4d %4s %15s %10s %4s %11s %4s %4s %7d %7d %10d\n", dto.getEmp_id(),
								dto.getEmp_name(), dto.getSsn(), dto.getIbsadate(), dto.getCity_loc(), dto.getTel(),
								dto.getBuseo_name(), dto.getJikwi_name(), dto.getBasicpay(), dto.getSudang(),
								dto.getPay());
					}
				} else
				{
					System.out.println("검색한 이름이 존재하지 않습니다.");
				}
				break;
			case 3:
				// 부서검색 메소드 호출 후 입력받기
				System.out.println("검색할 부서명 입력 : ");
				String buseo_name = sc.next();
				ArrayList<MemberDTO> buseoList = dao.buseoList(buseo_name);
				if (buseoList.size() > 0)
				{
					System.out.println("사번  이름      주민번호     입사일      지역       전화번호   부서   직위   기본급   수당   급여");
					for (MemberDTO dto : dao.buseoList(buseo_name))
					{
						System.out.printf("%4d %4s %15s %10s %4s %11s %4s %4s %7d %7d %10d\n", dto.getEmp_id(),
								dto.getEmp_name(), dto.getSsn(), dto.getIbsadate(), dto.getCity_loc(), dto.getTel(),
								dto.getBuseo_name(), dto.getJikwi_name(), dto.getBasicpay(), dto.getSudang(),
								dto.getPay());
					}
				} else
				{
					System.out.println("검색한 부서명이 존재하지 않습니다.");
				}

				break;
			case 4:
				// 직위검색 메소드 호출 후 입력받기
				System.out.println("검색할 직위 입력: ");
				String jikwi_name = sc.next();
				ArrayList<MemberDTO> jikwiList = dao.jikwiList(jikwi_name);
				if (jikwiList.size() > 0)
				{
					System.out.println("사번  이름      주민번호     입사일      지역       전화번호   부서   직위   기본급   수당   급여");
					for (MemberDTO dto : dao.jikwiList(jikwi_name))
					{
						System.out.printf("%4d %4s %15s %10s %4s %11s %4s %4s %7d %7d %10d\n", dto.getEmp_id(),
								dto.getEmp_name(), dto.getSsn(), dto.getIbsadate(), dto.getCity_loc(), dto.getTel(),
								dto.getBuseo_name(), dto.getJikwi_name(), dto.getBasicpay(), dto.getSudang(),
								dto.getPay());
					}
				} else
				{
					System.out.println("검색 결과 존재하지 않는 직위입니다.");

				}

				break;
			}

		} 
		catch (Exception e)
		{
			System.out.println(e.toString());
		}

	}

	// 4. 직원정보 수정 기능
	public void jikwonUpdate()
	{
		try
		{
			Scanner sc = new Scanner(System.in);
			System.out.println("수정 할 직원의 사번을 입력하세요 : ");
			int emp_id = sc.nextInt();

			dao.connection();
//			ArrayList<MemberDTO> arrayList = new ArrayList<MemberDTO>();
			ArrayList<MemberDTO> arrayList = dao.empList(emp_id);
			if (arrayList.size() > 0)
			{
				// 대상확인
				System.out.println("사번  이름      주민번호     입사일      지역       전화번호   부서   직위   기본급   수당   급여");
				for (MemberDTO dto : arrayList)
				{
					System.out.printf("%4d %4s %15s %10s %4s %11s %4s %4s %7d %7d %10d\n", dto.getEmp_id(),
							dto.getEmp_name(), dto.getSsn(), dto.getIbsadate(), dto.getCity_loc(), dto.getTel(),
							dto.getBuseo_name(), dto.getJikwi_name(), dto.getBasicpay(), dto.getSudang(), dto.getPay());
				}
				System.out.println("수정 할 데이터를 입력하세요 ");
				System.out.print("이름 : ");
				String emp_name = sc.next();
				System.out.print("주민등록번호(yymmdd-nnnnnnn) : ");
				String ssn = sc.next();
				System.out.print("입사일(yyyy-mm-dd) : ");
				String ibsadate = sc.next();
				System.out.print("지역(강원/경기/경남/경북/부산/서울/인천/전남/전북/제주/충남/) : ");
				String city_loc = sc.next();
				int cr = dao.getCity(city_loc);
				if (cr == 0)
				{
					System.out.println("존재하지 않는 지역입니다.");
					return;

				}
				System.out.print("전화번호 : ");
				String tel = sc.next();
				System.out.print("부서(개발부/기획부/영업부/인사부/자재부/총무부/홍보부/) : ");
				String buseo_name = sc.next();
				int br = dao.getBuseo(buseo_name);
				if (br == 0)
				{
					System.out.println("존재하지 않는 부서입니다.");
					return;

				}
				System.out.print("직위(사장/전무/상무/이사/부장/차장/과장/대리/사원/) : ");
				String jikwi_name = sc.next();
				int jr = dao.getJikwi(jikwi_name);
				if (jr == 0)
				{
					System.out.println("존재하지 않는 직위입니다.");
					return;

				}
				System.out.print("기본급 (최소 기본급 ) : ");
				int basicpay = sc.nextInt();
				System.out.print("수당 : ");
				int sudang = sc.nextInt();
//				System.out.print("급여 : ");
//				int pay = sc.nextInt();

				MemberDTO dto = new MemberDTO();
				dto.setEmp_id(emp_id);
				dto.setEmp_name(emp_name);
				dto.setSsn(ssn);
				dto.setIbsadate(ibsadate);
				dto.setCity_loc(city_loc);
				dto.setTel(tel);
				dto.setBuseo_name(buseo_name);
				dto.setJikwi_name(jikwi_name);
				dto.setBasicpay(basicpay);
				dto.setSudang(sudang);
//				dto.setPay(pay);

				int result = dao.modify(dto);
				if (result > 0)
				{
					System.out.println(">> 수정이 완료되었습니다.");

				}

			} else
			{
				System.out.println("수정 할 대상이 존재하지 않습니다.");

			}
			dao.close();

		} catch (Exception e)
		{
			System.out.println(e.toString());
		}
//		dao.close();

	}

	// 5. 직원정보 삭제 기능
	public void JikwonDelete()
	{
		try
		{
			Scanner sc = new Scanner(System.in);
			System.out.print("삭제할 직원의 사번을 입력하세요 : ");
			int emp_id = sc.nextInt();

			dao.connection();

			ArrayList<MemberDTO> arrayList = dao.empList(emp_id);

			if (arrayList.size() > 0)
			{
				// 삭제할 데이터를 찾았다
				System.out.println("사번  이름      주민번호     입사일      지역       전화번호   부서   직위   기본급   수당   급여");
				for (MemberDTO dto : dao.empList(emp_id))
				{
					System.out.printf("%4d %4s %15s %10s %4s %11s %4s %4s %7d %7d %10d\n", dto.getEmp_id(),
							dto.getEmp_name(), dto.getSsn(), dto.getIbsadate(), dto.getCity_loc(), dto.getTel(),
							dto.getBuseo_name(), dto.getJikwi_name(), dto.getBasicpay(), dto.getSudang(), dto.getPay());
				}
				System.out.println("정말 삭제 하시겠습니까?(Y/N) : ");

				String response = sc.next();
				if (response.equals("Y") || response.equals("y"))
				{
					int result = dao.remove(emp_id);
					if (result > 0)
					{
						System.out.println("삭제가 완료 되었습니다.");
					}
				} else
				{
					System.out.println("취소되었습니다.");
				}

			} else
			{
				// 삭제할 데이터를 찾지못했다.
				System.out.println("삭제할 데이터가 없습니다.");

			}
			dao.close();

		} catch (Exception e)
		{
			System.out.println(e.toString());
		}

	}// end JikwonDelete()

}// end class Process
