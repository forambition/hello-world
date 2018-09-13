package com.neusoft.oa.model;

import java.util.List;

import org.apache.ibatis.type.Alias;

/*
 * 吕海东
 */
//部门Model类
@Alias("Department")
public class DepartmentModel {

	private int no=0;  //序号
	private String code=null; //编码
	private String name=null; //名称
	//部门的位置
	private LocationModel location=null;
	
	//关联的员工列表
	private List<EmployeeModel> employees=null;
	
	
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<EmployeeModel> getEmployees() {
		return employees;
	}
	public void setEmployees(List<EmployeeModel> employees) {
		this.employees = employees;
	}
	public LocationModel getLocation() {
		return location;
	}
	public void setLocation(LocationModel location) {
		this.location = location;
	}
	
	
}
