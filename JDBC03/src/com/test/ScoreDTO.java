/*=======================
   ScoreDTO.java
 =======================*/
package com.test;

public class ScoreDTO
{
	// 주요 속성 구성
	private int sid, kor, eng, mat, sum; 
	
	private double avg;
	
	private String name;

	// getter / setter 구성
	
	public int getSid()
	{
		return sid;
	}

	public void setSid(int sid)
	{
		this.sid = sid;
	}

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

	public int getSum()
	{
		return sum;
	}

	public void setSum(int sum)
	{
		this.sum = sum;
	}

	public double getAvg()
	{
		return avg;
	}

	public void setAvg(double avg)
	{
		this.avg = avg;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}
	
	
	
	
	

}
