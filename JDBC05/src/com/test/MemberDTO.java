/*
 	MemberDTO.java
 */
package com.test;

public class MemberDTO
{
	/*
	 * CITY_ID , CITY_LOC , BUSEO_ID , BUSEO_NAME
JIKWI_ID, JIKWI_NAME, MIN_BASICPAY
 EMP_ID, EMP_NAME  , SSN , IBSADATE, TEL , BASICPAY, SUDANG
	 */
	// 주요 속성 구성
	// E.EMP_ID, E.EMP_NAME, E.SSN, E.IBSADATE
    //, C.CITY_LOC, E.TEL, B.BUSEO_NAME, J.JIKWI_NAME
    // J.MIN_BASICPAY, E.BASICPAY, E.SUDANG
    // (E.BASICPAY + E.SUDANG) AS PAY
	private String city_loc, buseo_name, jikwi_name, emp_name, ssn, ibsadate, tel;
	

	private int city_id, buseo_id, jikwi_id, min_basicpay, emp_id, basicpay, sudang ,pay;


	public int getPay()
	{
		return pay;
	}


	public void setPay(int pay)
	{
		this.pay = pay;
	}


	public String getCity_loc()
	{
		return city_loc;
	}


	public void setCity_loc(String city_loc)
	{
		this.city_loc = city_loc;
	}


	public String getBuseo_name()
	{
		return buseo_name;
	}


	public void setBuseo_name(String buseo_name)
	{
		this.buseo_name = buseo_name;
	}


	public String getJikwi_name()
	{
		return jikwi_name;
	}


	public void setJikwi_name(String jikwi_name)
	{
		this.jikwi_name = jikwi_name;
	}


	public String getEmp_name()
	{
		return emp_name;
	}


	public void setEmp_name(String emp_name)
	{
		this.emp_name = emp_name;
	}


	public String getSsn()
	{
		return ssn;
	}


	public void setSsn(String ssn)
	{
		this.ssn = ssn;
	}


	public String getIbsadate()
	{
		return ibsadate;
	}


	public void setIbsadate(String ibsadate)
	{
		this.ibsadate = ibsadate;
	}


	public String getTel()
	{
		return tel;
	}


	public void setTel(String tel)
	{
		this.tel = tel;
	}


	public int getCity_id()
	{
		return city_id;
	}


	public void setCity_id(int city_id)
	{
		this.city_id = city_id;
	}


	public int getBuseo_id()
	{
		return buseo_id;
	}


	public void setBuseo_id(int buseo_id)
	{
		this.buseo_id = buseo_id;
	}


	public int getJikwi_id()
	{
		return jikwi_id;
	}


	public void setJikwi_id(int jikwi_id)
	{
		this.jikwi_id = jikwi_id;
	}


	public int getMin_basicpay()
	{
		return min_basicpay;
	}


	public void setMin_basicpay(int min_basicpay)
	{
		this.min_basicpay = min_basicpay;
	}


	public int getEmp_id()
	{
		return emp_id;
	}


	public void setEmp_id(int emp_id)
	{
		this.emp_id = emp_id;
	}


	public int getBasicpay()
	{
		return basicpay;
	}


	public void setBasicpay(int basicpay)
	{
		this.basicpay = basicpay;
	}


	public int getSudang()
	{
		return sudang;
	}


	public void setSudang(int sudang)
	{
		this.sudang = sudang;
	}




	
	
	
	
	

}
