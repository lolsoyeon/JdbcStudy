/*=================================
 	ScoreDTO.java
 	-- 속성만 존재하는 클래스
 =================================*/
package com.test;

public class ScoreDTO
{
	// SID, NAME, KOR, ENG, MAT, TOT, AVG, RANK

	private int kor, eng, mat, tot, rank;

	private String sid, name;

	private Double avg;

	public int getKor()
	{
		return kor;
	}

	public void setKor(int kor)
	{
		this.kor = kor;
	}

	public int getEng()
	{
		return eng;
	}

	public void setEng(int eng)
	{
		this.eng = eng;
	}

	public int getMat()
	{
		return mat;
	}

	public void setMat(int mat)
	{
		this.mat = mat;
	}

	public int getTot()
	{
		return tot;
	}

	public void setTot(int tot)
	{
		this.tot = tot;
	}

	public int getRank()
	{
		return rank;
	}

	public void setRank(int rank)
	{
		this.rank = rank;
	}

	public String getSid()
	{
		return sid;
	}

	public void setSid(String sid)
	{
		this.sid = sid;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public Double getAvg()
	{
		return avg;
	}

	public void setAvg(Double avg)
	{
		this.avg = avg;
	}




	// getter/ setter 구성
	

}
