package com.neusoft.oa.model;

import java.util.List;

import org.apache.ibatis.type.Alias;

//员工角色Model类
@Alias("Role")
public class RoleModel {

	private int no=0; //角色编号
	private String name=null; //角色名称
	//角色包含的员工列表
	private List<EmployeeModel> employees=null;
	//角色授予的功能列表
	private List<FunctionModel> functions=null;
	
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
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
	public List<FunctionModel> getFunctions() {
		return functions;
	}
	public void setFunctions(List<FunctionModel> functions) {
		this.functions = functions;
	}
	
	
	
	
}
