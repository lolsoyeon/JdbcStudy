/*=========================
 	ScoreProcess.java
  - 단위 기능 구성
 =========================*/
package com.test;

import java.util.ArrayList;
import java.util.Scanner;

public class ScoreProcess
{
	// 주요 속성 구성 → 데이터베이스 액션 처리 전담 객체 → ScoreDAO
		private ScoreDAO dao;

		// 생성자 정의 → 사용자 정의 생성자
		public ScoreProcess()
		{
			dao = new ScoreDAO();
		}

		// 주요 기능 구성
		// - 성적 입력 기능
		public void SungjukInsert()
		{
			try
			{
				// 데이터 베이스 연결
				dao.connection();
				
				int count = dao.count();
				Scanner sc = new Scanner(System.in);
				
				do
				{
					System.out.println();
					System.out.printf("%d번 학생 성적 입력(이름 국어 영어 수학) : ", (++count));
					String name = sc.next();
					if (name.equals("."))
						break;
					int kor = sc.nextInt();
					int eng = sc.nextInt();
					int mat = sc.nextInt();

					// 꺼내줄걸 지금 채워주는 과정이다
					ScoreDTO dto = new ScoreDTO();
					dto.setName(name);
					dto.setKor(kor);
					dto.setEng(eng);
					dto.setMat(mat);

					int result = dao.add(dto);
					if (result > 0)
						System.out.println(">> 성적 입력이 완료되었습니다.");

				} while (true);

				dao.close();

				
				
			} catch (Exception e)
			{
				System.out.println(e.toString());
			}
			
		}
		
		// 성적 전체 출력 기능
		public void SungjukSelectAll()
		{
			
			try
			{
				dao.connection();
				
				int count = dao.count();
				
				System.out.println();
				System.out.printf("전체 인원 : %d명\n", count);
				System.out.println("번호  이름   국어   영어   수학  총점  평균  석차");
				
				for (ScoreDTO dto : dao.list())
				{
					System.out.printf("%3s %4s %5d %4d %5d %6d %5.1f %5d\n", dto.getSid(), dto.getName(), dto.getKor(),
							dto.getEng(), dto.getMat(), dto.getTot(), dto.getAvg(), dto.getRank());
					
				}
				dao.close();
				
			} catch (Exception e)
			{
				System.out.println(e.toString());
			}
			
		}
		
		// 이름 검색 출력
		public void SungjukSerchName()
		{
			try
			{
				Scanner sc = new Scanner(System.in);
				
				System.out.println("검색 할 이름 입력 : ");

				String name = sc.next();
				
				// 데이터베이스 연결
				dao.connection();
				
				// dao의 list()호출 → 매개변수로 검색할 이름 넘겨주기
				ArrayList<ScoreDTO> arrayList = dao.list(name);
				
				if (arrayList.size() > 0)
				{
					// 리스트를 출력하게 된다.
					System.out.println("번호  이름  국어  영어  수학  총점  평균  석차");
					for (ScoreDTO dto : arrayList)
					{
						System.out.printf("%3s %4s %5d %4d %5d %5d %5.1f %5d\n", dto.getSid(), dto.getName(), dto.getKor(),
								dto.getEng(), dto.getMat(), dto.getTot(), dto.getAvg(), dto.getRank());
					}
					
				}
				else
				{
					System.out.println("검색 대상이 존재하지 않습니다.");
				}
				dao.close();
				
			} catch (Exception e)
			{
				System.out.println(e.toString());
			}
		}
		
		// 성적 수정 기능
		public void SungjukUpdate()
		{
			try
			{
				Scanner sc = new Scanner(System.in);
				System.out.println("수정 할 학생의 번호를 입력하세요 ");
				
				int sid = sc.nextInt();
				
				dao.connection();
				
				// dao의 list() 호출
				ArrayList<ScoreDTO> arrayList = dao.list(sid);
				
				if (arrayList.size() > 0)
				{
					// 출력해 주면 된다.
					System.out.println("번호  이름  국어  영어  수학  총점  평균  석차");
					for (ScoreDTO dto : arrayList)
					{
						System.out.printf("%3s %4s %5d %4d %5d %5d %5.1f %5d\n", dto.getSid(), dto.getName(), dto.getKor(),
								dto.getEng(), dto.getMat(), dto.getTot(), dto.getAvg(), dto.getRank());
					}
					System.out.print("수정 데이터 입력(이름 국어 영어 수학) : ");
					String name = sc.next();
					int kor = sc.nextInt();
					int eng = sc.nextInt();
					int mat = sc.nextInt();
					
					ScoreDTO dto = new ScoreDTO();
					dto.setName(name);
					dto.setKor(kor);
					dto.setEng(eng);
					dto.setMat(mat);
					dto.setSid(String.valueOf(sid));
					
					int result = dao.modify(dto);
					if (result > 0 )
					{
						System.out.println("수정이 완료 되었습니다.");
						
					}
					
				} else
				{
					System.out.println("검색 대상이 존재하지 않습니다.");
				}
				dao.close();
				
			} catch (Exception e)
			{
				System.out.println(e.toString());
			}
		}
		
		// 성적 삭제 기능
		public void SungjukDelete()
		{
			try
			{
				Scanner sc = new Scanner(System.in);
				System.out.println("삭제할 학생의 번호를 입력 해 주세요");
				int sid = sc.nextInt();
				
				// 데이터베이스 연결
				dao.connection();
				
				// dao의 list() 메소드 호출
				ArrayList<ScoreDTO> arrayList = dao.list(sid);
				
				if (arrayList.size() > 0)
				{
					// 출력해준다.
					System.out.println("번호  이름  국어  영어   수학   총점   평균  석차");
					for (ScoreDTO dto : arrayList)
					{
						System.out.printf("%3s %4s %5d %4d %5d %5d %5.1f %5d\n", dto.getSid(), dto.getName(), dto.getKor(),
								dto.getEng(), dto.getMat(), dto.getTot(), dto.getAvg(), dto.getRank());
					}
					System.out.println("정말 삭제하시겠습니까? (Y/N) : ");
					String response = sc.next();
					if (response.equals("y")||response.equals("Y"))
					{
						// 입력받은 result 가 y면 remove() 호출
						int result = dao.remove(sid);
						if (result > 0)
						{
							System.out.println("데이터가 삭제 되었습니다.");
						}
						
					}
					else
					{
						System.out.println("Y 또는 Y를 누르지 않아 돌아가겠습니다.");
					}
					
				}
				else
				{
					System.out.println("입력하신 학생의 번호가 존재하지 않습니다. ");
				}
				dao.close();	
				
				
			} catch (Exception e)
			{
				System.out.println(e.toString());
			}
			
		}

}
