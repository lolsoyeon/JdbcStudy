/*===============================================
 	MemberMain.java
    - main() 메소드를 포함하고 있는 테스트 클래스
 =============================================== */

// ○ TBL_MEMBER 테이블을 활용하여
//    이름과 전화번호를 여러 건 입력받고
//    결과를 전체 출력하는 기능을 가진 프로그램을 구현한다.
//    MemberDAO, MemberDTO 클래스를 활용해야 한다.

/*
실행 예)

이름 전화번호 입력(?) : 장현성 010-1111-1111
>> 회원 정보 입력 완료~!!!
이름 전화번호 입력(2) : 정미경 010-2222-2222
>> 회원 정보 입력 완료~!!!
이름 전화번호 입력(3) : 엄소연 010-3333-3333
>> 회원 정보 입력 완료~!!!
이름 전화번호 입력(4) : .
----------------------------------------
전체 회원 수 : 3명
----------------------------------------
번호 이름  전화번호 
1    장현성 010-1111-111
2	 정미경 010-2222-2222
3	 엄소연 010-3333-3333
----------------------------------------

- 프로그램 종료 

 */

package com.test;

import java.util.Scanner;

import com.util.DBConn;

public class MemberMain
{
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);

		try
		{
			MemberDAO dao = new MemberDAO();

			// 테스트 구문
			// System.out.println("데이터베이스 연결 성공~!!!");
			
			// 이구문 무르겠음
			int count = dao.count();

			do
			{
				System.out.printf("이름 전화번호 입력(%d) : ", (++count));
				// ???? 
				String name = sc.next();

				if (name.equals("."))
				{
					break;
				}

				String tel = sc.next();
				// 이제부터 DTO객체 생성하고 세터로 값넣고 add()호출해야 할 것 같다는 의견
				
				
				// MemberDTO 객체 생성 및 멤버 구성
				MemberDTO dto = new MemberDTO();
				dto.setName(name);
				dto.setTel(tel);
				
				//  데이터베이스에 데이터를 입력하는 메소드 호출 → dao.add()
				int result = dao.add(dto);
				if (result > 0)
				{
					System.out.println(">> 회원 정보 입력 완료~!!!!");
					
				}
				

			} while (true);
			
			/*
			 ----------------------------------------
			전체 회원 수 : 3명
			----------------------------------------
			번호 이름  전화번호 
			1    장현성 010-1111-111
			2	 정미경 010-2222-2222
			3	 엄소연 010-3333-3333
			----------------------------------------
			*/
			System.out.println();
			System.out.println("----------------------------------------");
			System.out.printf("전체 회원 수 : %d명\n", dao.count());
			System.out.println("----------------------------------------");
			System.out.println("번호 이름  전화번호 ");
			
			
			for (MemberDTO obj : dao.list())
			{
				System.out.printf("%3s %6s %12s\n"
						, obj.getSid(), obj.getName(), obj.getTel());
				
			}
			System.out.println("----------------------------------------");
			
			

		} catch (Exception e)
		{
			System.out.println(e.toString());
		}
		
		finally
		{
			try
			{
				DBConn.close();
				System.out.println(">> 데이터베이스 연결 닫힘~!!");
				System.out.println(">> 프로그램 종료됨~!!!");
			} catch (Exception e2)
			{
				System.out.println(e2.toString());
			}
		}//end finally 
		
	}//end main()

}//end class MemberMain


/*
 * 이름 전화번호 입력(1) : 장현성
 010-1111-1111
>> 회원 정보 입력 완료~!!!!
이름 전화번호 입력(2) : 정미경 
010-2222-2222
>> 회원 정보 입력 완료~!!!!
이름 전화번호 입력(3) : 엄소연 010-3333-3333
>> 회원 정보 입력 완료~!!!!
이름 전화번호 입력(4) : 박원석 010-4444-4444
>> 회원 정보 입력 완료~!!!!
이름 전화번호 입력(5) : 김유림 010-5555-5555
>> 회원 정보 입력 완료~!!!!
이름 전화번호 입력(6) : .

 ----------------------------------------
전체 회원 수 : 5명
----------------------------------------
번호 이름  전화번호 
  1    장현성 010-1111-1111
  2    정미경 010-2222-2222
  3    엄소연 010-3333-3333
  4    박원석 010-4444-4444
  5    김유림 010-5555-5555
----------------------------------------
>> 데이터베이스 연결 닫힘~!!
>> 프로그램 종료됨~!!!

 */
