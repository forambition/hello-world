package com.neusoft.oa.service;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.neusoft.oa.model.EmployeeModel;
import com.neusoft.oa.model.FunctionModel;
/*
 * 员工业务接口
 */
public interface IEmployeeSerivce {

	//增加员工，无图片
	public void addWithoutPhoto(EmployeeModel employee) throws Exception;
	//增加员工，有图片
	public void addWithPhoto(EmployeeModel employee) throws Exception;
	//修改员工，无图片
	public void modifyWithoutPhoto(EmployeeModel employee) throws Exception;
	//修改员工，有图片
	public void modifyWithPhoto(EmployeeModel employee) throws Exception;
	//删除员工
	public void delete(EmployeeModel employe) throws Exception;
	//
	//取得指定的员工信息 （取得单个对象），同时取得关联的部门对象
	public EmployeeModel getById(String id) throws Exception;
	//取得所有员工列表，无分页，无关联的部门对象
	public List<EmployeeModel> getListWithoutDepartmentByAll() throws Exception;
	//取得所有员工列表，无分页，有关联的部门对象
	public List<EmployeeModel> getListWithDepartmentByAll() throws Exception;
	
	//取得所有员工列表,有关联的角色列表，无分页
	public List<EmployeeModel> getListWithRolesByAll() throws Exception;
	
	//为员工授予指定的角色，单个授权
	public void grantRole(String id,int roleNo) throws Exception;
	//为员工授权多个角色，批量授权
	public void grantRoles(String id,int[] rolesNos) throws Exception;
	
	//根据检索条件取得员工的列表,无分页
	public List<EmployeeModel> getListByCondition(int departmentNo,String sex,Date startDate,Date endDate, String name, int[] roles) throws Exception;
	//根据检索条件取得员工的列表,有分页
	public List<EmployeeModel> getListByConditionWithPage(int departmentNo,String sex,Date startDate,Date endDate, String name, int[] roles,int start,int end) throws Exception;
		
	//根据检索条件取得员工个数
	public int getCountByCondition(int departmentNo,String sex,Date startDate,Date endDate, String name, int[] roles) throws Exception;
	//根据检索条件取得员工显示页数
	public int getPageCountByCondition(int departmentNo,String sex,Date startDate,Date endDate, String name, int[] roles,int rows) throws Exception;
	//========================================================================================
	//取得指定员工的功能列表
	public List<FunctionModel> getFucntionListByEmployee(String id) throws Exception;
	//验证员工登录合法
	public boolean validate(String id, String password) throws Exception;
	
}
