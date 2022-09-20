/*=======================
   ScoreMain.java
 =======================*/

// ○ 실습 문제
//    성적 처리 프로그램을 구현한다 → 데이터베이스 연동 → ScoreDAO, ScoreDTO 활용

// 		여러 명의 이름. 국어점수, 영어점수, 수학점수를 입력받아
// 		총점, 평균을 연산하여 출력하는 프로그램을 구현한다.
// 		출력 시 번호(이름, 총점 등) 오름차순 정렬하여 출력한다.

// 실행 예)
/*
 1번 학생 성적 입력(이름 국어 영어 수학) : 박원석 80 75 60
 2번 학생 성적 입력(이름 국어 영어 수학) : 조영관 100 90 80
 3번 학생 성적 입력(이름 국어 영어 수학) : 김보경 80 85 80
 4번 학생 성적 입력(이름 국어 영어 수학) : .
 ---------------------------------------------------------------
 번호 	이름	국어	영어	수학	총점	평균
 ---------------------------------------------------------------
  1		박원석	 80		75		60		xxx		xx.x
  2		조영관	 80		95		80		xxx		xx.x
  3		김보경   80		85		80		xxx		xx.x
---------------------------------------------------------------
 */
package com.test;

import java.sql.SQLException;
import java.util.Scanner;

import com.util.DBConn;

public class ScoreMain
{
	public static void main(String[] args) throws SQLException
	{
		 Scanner sc = new Scanner(System.in);
		  
		  try 
		  {
			  // ScoreDAO 객체 생성 
			  ScoreDAO dao = new ScoreDAO();
			  
			  // 테스트
			  //System.out.println("데이터베이스 연결 성공~!!");
		  
			  int count = dao.count();
		  
			  // 여기서도 갑자기 안내 없이 정수형태의 입력값을 요구하고 있고...
			  // 가령~ 이렇게 작성이라도 하면... sid 를 입력받아 올 수는 있겠지...
			  // System.out.print("sid 입력하세요 : ");
			  // int sid = sc.nextInt();
			  
			  // 실행했을 때 아무 일도 생기지 않는 이유는... 문번호 50번 때문...
			  // 소연아~
			  // 위에 작성한 이 코드는... 사용자 입력값을 아무 안내 없이... 정수 형태로 받아와라~ 라는 코드야~
			  
			  do
			  {
				  System.out.printf("%d번 학생 성적 입력(이름 국어 영어 수학) : ", (++count));
				  
				  String name = sc.next();
				  //if (name.equals('.'))		// 이건 또... 문자열이 아니라 문자....
				  if (name.equals("."))		// equals() 는 문자열 관련한 함수.... 문자 비교가 아니고...
				  {
					  break;
				  }
				  
	
				  int kor = sc.nextInt();
				  int eng = sc.nextInt();
				  int mat = sc.nextInt();
		  
				  // ScoreDTO 객체 생성하고 세터로 값 넣고 add()호출
				  
				  // ScoreDTO 객체 생성
				  ScoreDTO dto = new ScoreDTO();
				  
				  // dto.setSid(sid);	// 그래서 이 코드도 불가능~!!!
				  dto.setName(name);
				  dto.setKor(kor);
				  dto.setEng(eng);
				  dto.setMat(mat);
				  dao.add(dto);
				  // dto.setSum(sum);
				  // dto.setAvg(avg);
				  
				  // 데이터베이스에 입력하는 메소드add() 호출
				  // int result = 0;
				  // result = dao.add(dto);	//-- 그래서 이 메소드도 정상 호출 불가~!!!
				  // if (result > 0 )
//				  {
//					  System.out.println(">> 학생 데이터 입력 완료");
//				  }

		  
			  } while (true);
		  
		  
			  //--------------------------------------------------------------- 
			  // 번호 이름 국어 영어 수학 총점 평균 //
			  //--------------------------------------------------------------- 
			  // 1 박원석 80 75 60 xxx xx.x 
			  // 2 조영관 80 95 80 xxx xx.x 
			  // 3 김보경 80 85 80 xxx xx.x 
			  //---------------------------------------------------------------
		  
		  
			  System.out.println("---------------------------------------------------------------");
			  System.out.println("번호 	이름	국어	영어	수학	총점	평균"); 
			  System.out.println("---------------------------------------------------------------");
		  
			  for (ScoreDTO obj: dao.list())
			  {
				  System.out.printf("%3d %5s %7d %7d %7d %6d %9.2f\n" 
						  	, obj.getSid(), obj.getName(), obj.getKor(), obj.getEng(), obj.getMat(), obj.getSum(), obj.getAvg() );
		  
			  }
			  
			  System.out.println("---------------------------------------------------------------");
		  
		  
		  }
		  catch(Exception e)
		  {
			  System.out.println(e.toString());
		  }
		  finally
		  {
			  try
			  {
				  DBConn.close();
				  System.out.println("데이터베이스 연결 닫힘");
				  System.out.println("연결 종료~!!!");
			  }
			  catch (Exception e2)
			  {
				  System.out.println(e2.toString());
			  }
		  }
		  
	}// end main()

}// end ScoreMain

/*
 4번 학생 성적 입력(이름 국어 영어 수학) : .

---------------------------------------------------------------
번호 	이름	국어	영어	수학	총점	평균
---------------------------------------------------------------
  1   박원석      90      80      70    240     80.00
  2   김보경      79      69      80    228     76.00
  3   김태민      90      80      70    240     80.00
---------------------------------------------------------------
데이터베이스 연결 닫힘
연결 종료~!!!
*/
// 