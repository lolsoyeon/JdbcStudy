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
				System.out.print("전화번호 : ");
				String tel = sc.next();
				System.out.print("부서(개발부/기획부/영업부/인사부/자재부/총무부/홍보부/) : ");
				String buseo_name = sc.next();
				System.out.print("직위(사장/전무/상무/이사/부장/차장/과장/대리/사원/) : ");
				String jikwi_name = sc.next();
				System.out.print("기본급 (최소 기본급 ) : ");
				int basicpay = sc.nextInt();
				System.out.print("수당 : ");
				int sudang = sc.nextInt();

				MemberDTO dto = new MemberDTO();
				dto.setEmp_name(emp_name);
				dto.setSsn(ssn);
				dto.setIbsadate(ibsadate);
				dto.setCity_loc(city_loc);
				dto.setTel(tel);
				dto.setBuseo_name(buseo_name);
				dto.setJikwi_name(jikwi_name);
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
		try
		{
			// 데이터베이스 연결
			dao.connection();
			
			int count = dao.count();
			
			Scanner sc = new Scanner(System.in);
			
			System.out.println("원하는 정렬 방식을 선택해 주세요 (1~5) : ");
			System.out.println("(1) 사번 정렬	");
			System.out.println("(2) 이름 정렬   ");
			System.out.println("(3) 부서 정렬   ");
			System.out.println("(4) 직위 정렬   ");
			System.out.print("(5) 급여 내림차순 정렬");
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
				dao.listSabun();
				break;
			case 2:
				// 이름 정렬 메소드 호출
				dao.listName();
				break;
			case 3:
				// 부서 정렬 메소드 호출
				dao.listBuseo();
				break;
				
			case 4:
				// 직위 정렬 메소드 호출
				dao.listJikwi();
				break;
			case 5:
				// 급여 내림차순 메소드 호출
				dao.listPay();
				break;
				
			}
			System.out.println();
			System.out.printf("전체인원 : %d명", count);
			System.out.println("사번  이름  주민번호  입사일  지역  전화번호  부서  직위  기본급  수당  급여");

			for (MemberDTO dto : dao.list())
			{
				System.out.printf("%4d %4s %15s %10s %4s %11s %4s %4s %7d %7d %10d\n", dto.getEmp_id(),
						dto.getEmp_name(), dto.getSsn(), dto.getIbsadate(), dto.getCity_loc(), dto.getTel(),
						dto.getBuseo_name(), dto.getJikwi_name(), dto.getBasicpay(), dto.getSudang(), dto.getPay());
			}
			dao.close();
			
			
			
			
		} catch (Exception e)
		{
			System.out.println(e.toString());
		}

		
		
	}
	// 직원 검색 출력 기능 
	public void jikwonSerch()
	{
		try
		{
		
		// 데이터베이스 연결
			dao.connection();
			
			int count = dao.count();
			
			Scanner sc = new Scanner(System.in);
			
			System.out.println("원하는 검색 방법을 택해주세요(1~5) : ");
			System.out.println("(1) 사번 검색");
			System.out.println("(2) 이름 검색");
			System.out.println("(3) 부서 검색");
			System.out.print("(4) 직위 검색");
			System.out.print(">> 번호 선택 : ");
			
			int num = sc.nextInt();
			
			if (num >= 0 ||  num<= 5 )
			{
				System.out.println("잘못된 입력입이다.");
				return;
			}
			switch (num)
			{
			case 1:
				// 사번검색 메소드 호출 후 입력받기
				try
				{
					// 검색할 사번 입력 받기
					Scanner sc1 = new Scanner(System.in);

					System.out.print("검색할 사번 입력 : ");
					int emp_id = sc1.nextInt();

					dao.connection();

					ArrayList<MemberDTO> arrayList = dao.empList(emp_id);

					if (arrayList.size() > 0)
					{
						// 리스트 출력
						System.out.println("사번  이름  주민번호  입사일  지역  전화번호  부서  직위  기본급  수당  급여");
						for (MemberDTO dto : arrayList)
						{
							System.out.printf("%4d %4s %15s %10s %4s %11s %4s %4s %7d %7d %10d\n", dto.getEmp_id(),
									dto.getEmp_name(), dto.getSsn(), dto.getIbsadate(), dto.getCity_loc(), dto.getTel(),
									dto.getBuseo_name(), dto.getJikwi_name(), dto.getBasicpay(), dto.getSudang(), dto.getPay());
						}
					} else
					{
						System.out.println("검색 결과 존재하지 않는 사번입니다.");
					}
					dao.close();

				} catch (Exception e)
				{
					System.out.println(e.toString());
				}
				
				break;

			case 2:
				// 이름 검색 메소드 호출 후 입력 받기
				try
				{
					// 검색할 이름 입력 받기
					Scanner sc1 = new Scanner(System.in);

					System.out.print("검색할 이름 입력 : ");
					String emp_name = sc1.next();

					dao.connection();

					ArrayList<MemberDTO> arrayList = dao.nameList(emp_name);

					if (arrayList.size() > 0)
					{
						// 리스트 출력
						System.out.println("사번  이름  주민번호  입사일  지역  전화번호  부서  직위  기본급  수당  급여");
						for (MemberDTO dto : arrayList)
						{
							System.out.printf("%4d %4s %15s %10s %4s %11s %4s %4s %7d %7d %10d\n", dto.getEmp_id(),
									dto.getEmp_name(), dto.getSsn(), dto.getIbsadate(), dto.getCity_loc(), dto.getTel(),
									dto.getBuseo_name(), dto.getJikwi_name(), dto.getBasicpay(), dto.getSudang(), dto.getPay());
						}
					} else
					{
						System.out.println("검색 결과 존재하지 않는 이름입니다.");
					}
					dao.close();

				} catch (Exception e)
				{
					System.out.println(e.toString());
				}
				break;
			case 3:
				// 부서 검색 메소드 호출 후 입력 받기
				try
				{
					// 검색할 부서 입력 받기
					Scanner sc1 = new Scanner(System.in);

					System.out.print("검색할 부서 입력 : ");
					String buseo_name = sc1.next();

					dao.connection();

					ArrayList<MemberDTO> arrayList = dao.buseoList(buseo_name);

					if (arrayList.size() > 0)
					{
						// 리스트 출력
						System.out.println("사번  이름  주민번호  입사일  지역  전화번호  부서  직위  기본급  수당  급여");
						for (MemberDTO dto : arrayList)
						{
							System.out.printf("%4d %4s %15s %10s %4s %11s %4s %4s %7d %7d %10d\n", dto.getEmp_id(),
									dto.getEmp_name(), dto.getSsn(), dto.getIbsadate(), dto.getCity_loc(), dto.getTel(),
									dto.getBuseo_name(), dto.getJikwi_name(), dto.getBasicpay(), dto.getSudang(), dto.getPay());
						}
					} else
					{
						System.out.println("검색 결과 존재하지 않는 부서입니다.");
					}
					dao.close();

				} catch (Exception e)
				{
					System.out.println(e.toString());
				}
				
				
				break;
				
			case 4:
				// 직위 검색 메소드 호출 후 입력 받기
				try
				{
					// 검색할 직위 입력 받기
					Scanner sc1 = new Scanner(System.in);

					System.out.print("검색할 직위 입력 : ");
					String jikwi_name = sc1.next();

					dao.connection();

					ArrayList<MemberDTO> arrayList = dao.jikwiList(jikwi_name);

					if (arrayList.size() > 0)
					{
						// 리스트 출력
						System.out.println("사번  이름  주민번호  입사일  지역  전화번호  부서  직위  기본급  수당  급여");
						for (MemberDTO dto : arrayList)
						{
							System.out.printf("%4d %4s %15s %10s %4s %11s %4s %4s %7d %7d %10d\n", dto.getEmp_id(),
									dto.getEmp_name(), dto.getSsn(), dto.getIbsadate(), dto.getCity_loc(), dto.getTel(),
									dto.getBuseo_name(), dto.getJikwi_name(), dto.getBasicpay(), dto.getSudang(), dto.getPay());
						}
					} else
					{
						System.out.println("검색 결과 존재하지 않는 직위입니다.");
					}
					dao.close();

				} catch (Exception e)
				{
					System.out.println(e.toString());
				}
				break;
				
			}


			
		} catch (Exception e)
		{
			System.out.println(e.toString());
		}
		
	}
	

	// 4. 직원정보 수정 기능
	public void JiwonUpdate()
	{
		try
		{
			Scanner sc = new Scanner(System.in);
			System.out.println("수정 할 직원의 사번을 입력하세요 : ");
			int emp_id = sc.nextInt();

			dao.connection();
			ArrayList<MemberDTO> arrayList = new ArrayList<MemberDTO>();

			if (arrayList.size() > 0)
			{
				// 대상확인
				System.out.println("사번  이름  주민번호  입사일  지역  전화번호  부서  직위  기본급  수당  급여");
				for (MemberDTO dto : arrayList)
				{
					System.out.printf("%4d %4s %15s %10s %4s %11s %4s %4s %7d %7d %10d\n", dto.getEmp_id(),
							dto.getEmp_name(), dto.getSsn(), dto.getIbsadate(), dto.getCity_loc(), dto.getTel(),
							dto.getBuseo_name(), dto.getJikwi_name(), dto.getBasicpay(), dto.getSudang(), dto.getPay());
				}
				System.out.println("수정 할 데이터를 입력하세요 ");
				System.out.print("이름 : ");
				String emp_name = sc.next();
				System.out.print("주민등번호(yymmdd-nnnnnnn) : ");
				String ssn = sc.next();
				System.out.print("입사일(yyyy-mm-dd) : ");
				String ibsadate = sc.next();
				System.out.print("지역(강원/경기/경남/경북/부산/서울/인천/전남/전북/제주/충남/) : ");
				String city_loc = sc.next();
				System.out.print("전화번호 : ");
				String tel = sc.next();
				System.out.print("부서(개발부/기획부/영업부/인사부/자재부/총무부/홍보부/) : ");
				String buseo_name = sc.next();
				System.out.print("직위(사장/전무/상무/이사/부장/차장/과장/대리/사원/) : ");
				String jikwi_name = sc.next();
				System.out.print("기본급 (최소 기본급 )");
				int basicpay = sc.nextInt();
				System.out.print("수당");
				int sudang = sc.nextInt();
				System.out.print("급여");
				int pay = sc.nextInt();

				MemberDTO dto = new MemberDTO();
				dto.setEmp_name(emp_name);
				dto.setSsn(ssn);
				dto.setIbsadate(ibsadate);
				dto.setCity_loc(city_loc);
				dto.setTel(tel);
				dto.setBuseo_name(buseo_name);
				dto.setJikwi_name(jikwi_name);
				dto.setBasicpay(basicpay);
				dto.setSudang(sudang);
				dto.setPay(pay);

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
				for (MemberDTO dto : arrayList)
				{
					System.out.printf("%4d %4s %15s %10s %4s %11s %4s %4s %7d %7d %10d\n", dto.getEmp_id(),
							dto.getEmp_name(), dto.getSsn(), dto.getIbsadate(), dto.getCity_loc(), dto.getTel(),
							dto.getBuseo_name(), dto.getJikwi_name(), dto.getBasicpay(), dto.getSudang(), dto.getPay());
				}
				System.out.println("정말 삭제 하시겠습니까?(Y/N) : ");

				String response = sc.next();
				if (response.equalsIgnoreCase("y"))
				{
					int result = dao.remove(emp_id);
					if (result > 0)
					{
						System.out.println("삭제가 완료 되었습니다.");
					}
				} 
				else
				{
					System.out.println("취소되었습니다.");
				}

			} 
			else
			{
				// 삭제할 데이터를 찾지못했다.
				System.out.println("삭제할 데이터가 없습니다.");

			}
			dao.close();

		} catch (Exception e)
		{
			System.out.println(e.toString());
		}

	}//end JikwonDelete()

}// end class Process
