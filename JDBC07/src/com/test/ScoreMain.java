/*=====================
 	ScoreMain.java
 =====================*/
/*
 ○ 실습 문제
 
 성정 처리 → 데이터베이스 연동(데이터베이스 연결 및 액션 처리)
 			  ScoreDTO 활용 (속성만 존재하는 클래스, getter / setter 구성)
 			  ScoreDAO 활용 (데이터베이스 액션 처리 전용 클래스)
 			  ScorePrecess 활용 ( 단위 기능 구성)
 			  단, PreparedStatement 활용~!!! -----------check ~~~~~!!!
 			
 			여러명의 이름, 국어점수, 영어점수, 수학점수를 입력받아
 			총점, 평균, 석차 등을 연산하여 출력하는 프로그램을 구현한다.

 			  
 			※ 서브 메뉴 구성 → ScoreProcess 클래스 활용 클래스 총 5개
 			
실행 예)

===[성적 처리]===
1. 성적 입력
2. 성적 전체 출력
3. 이름 검색 출력
4. 성적 수정
5. 성적 삭제
==================
>> 선택(1~5, -1종료) : 1

4번 학생 성적 입력(이름 국어 영어 수학) : 정미경 50 60 70
>> 성적 입력이 완료외었습니다.
5번 학생 성적 입력(이름 국어 영어 수학) : 조영관 80 80 80
>> 성적 입력이 완료되었습니다.
6번 학생 성적 입력(이름 국어 영어 수학) : .
>> 성적 입력이 완료되었습니다.
===[성적 처리]===
1. 성적 입력
2. 성적 전체 출력
3. 이름 검색 출력
4. 성적 수정
5. 성적 삭제
==================
>> 선택(1~5, -1종료) : 2

전체 인원 : 5명
번호  이름  국어  영어  수학  총점  평균  석차
1
2
3			...
4
5


===[성적 처리]===
1. 성적 입력
2. 성적 전체 출력
3. 이름 검색 출력
4. 성적 수정
5. 성적 삭제
==================
>> 선택(1~5, -1종료) : -1

>> 프로그램이 종료되었습니다.
 			
 */

package com.test;

import java.util.Scanner;

public class ScoreMain
{
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);

		ScoreProcess prc = new ScoreProcess();

		do
		{
			System.out.println();
			System.out.println("===[성적 처리]===      ");
			System.out.println("1. 성적 입력           ");
			System.out.println("2. 성적 전체 출력        ");
			System.out.println("3. 이름 검색 출력        ");
			System.out.println("4. 성적 수정           ");
			System.out.println("5. 성적 삭제           ");
			System.out.println("================== ");
			System.out.println(">> 선택(1~5, -1종료) : ");

			String menus = sc.next();

			try
			{
				int menu = Integer.parseInt(menus);

				if (menu == -1)
				{
					System.out.println();
					System.out.println("프로그램이 종료되었습니다.");
					return;
				}
				switch (menu)
				{
				case 1:
					// 성적입력 메소드 호출
					prc.SungjukInsert();
					break;
				case 2:
					// 성적 전체 출력 메소드 호출
					prc.SungjukSelectAll();
					break;
				case 3:
					// 이름 검색 메소드 호출
					prc.SungjukSerchName();
					break;
				case 4:
					// 성적 수정 메소드 호출
					prc.SungjukUpdate();
					break;
				case 5:
					// 성적 삭제 메소드 호출
					prc.SungjukDelete();
					break;

				}

			} catch (Exception e)
			{
				System.out.println(e.toString());
			}

		} while (true);

	}

}
