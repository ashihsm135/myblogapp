package com.javasampleapproach.twitterbootstrap.utility.xls;

import java.util.Date;

	public class Employee {
	    private String m_name;
	    private Integer m_id;
	    private Date m_hireDate;
	    private Double m_salary;
	 
	    public Employee() {}
	 
	    public Employee(Integer id, String name, Date hiredate, Double salary) {
	        this.m_id = id;
	        this.m_name = name;
	        this.m_hireDate = hiredate;
	        this.m_salary = salary;
	    }
	    public String getName() {
	        return m_name;
	    }
	    public void setName(String name) {
	        this.m_name = name;
	    }
	    public Integer getId() {
	        return m_id;
	    }
	    public void setId(Integer id) {
	        this.m_id = id;
	    }
	    public Date getHireDate() {
	        return m_hireDate;
	    }
	    public void setHireDate(Date hireDate) {
	        this.m_hireDate = hireDate;
	    }
	    public Double getSalary() {
	        return m_salary;
	    }
	    public void setSalary(Double salary) {
	        this.m_salary = salary;
	    }
}
