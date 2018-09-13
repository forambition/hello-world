package com.neusoft.oa.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.neusoft.oa.dao.IEmployeeDao;
import com.neusoft.oa.model.EmployeeModel;
import com.neusoft.oa.model.FunctionModel;
import com.neusoft.oa.service.IEmployeeSerivce;
/*
 * 员工业务实现类
 * @Author: 吕海东
 */
@Service
@Transactional
public class EmployeeServiceImpl implements IEmployeeSerivce {

	private IEmployeeDao employeeDao=null;
	@Autowired
	public void setEmployeeDao(IEmployeeDao employeeDao) {
		this.employeeDao = employeeDao;
	}
	//无图片时增加新员工
	@Override
	public void addWithoutPhoto(EmployeeModel employee) throws Exception {
		employeeDao.createWithoutPhoto(employee);
		
	}
	//有图片时增加新员工
	@Override
	public void addWithPhoto(EmployeeModel employee) throws Exception {
		employeeDao.createWithPhoto(employee);
		
	}
	//无图片时修改员工
	@Override
	public void modifyWithoutPhoto(EmployeeModel employee) throws Exception {
		employeeDao.updateWithoutPhoto(employee);
		
	}
	//有图片时修改员工
	@Override
	public void modifyWithPhoto(EmployeeModel employee) throws Exception {
		employeeDao.updateWithPhoto(employee);
		
	}
	//删除指定的员工
	@Override
	public void delete(EmployeeModel employee) throws Exception {
		//先撤销所有的角色授权
		employeeDao.revokeAllRoles(employee.getId());
		//
		employeeDao.delete(employee);
		
	}
	//取得单个员工对象，同时取得关联的部门对象
	@Override
	public EmployeeModel getById(String id) throws Exception {
		
		return employeeDao.selectById(id);
	}
	
	//取得所有员工列表，同时取得关联的部门对象
	@Override
	public List<EmployeeModel> getListWithoutDepartmentByAll() throws Exception {
		
		return employeeDao.selectListWithoutDepartmentByAll();
	}
	@Override
	public List<EmployeeModel> getListWithDepartmentByAll() throws Exception {
		
		return employeeDao.selectListWithDepartmentByAll();
	}
	@Override
	public List<EmployeeModel> getListWithRolesByAll() throws Exception {
		
		return employeeDao.selectListWithRolesByAll();
	}
	@Override
	public void grantRole(String id, int roleNo) throws Exception {
		employeeDao.grantRole(id, roleNo);
		
	}
	@Override
	public void grantRoles(String id, int[] rolesNos) throws Exception {
		//先清除原有的角色授权
		employeeDao.revokeAllRoles(id);
		//授予新的角色权限
		for(int roleNo:rolesNos) {
			employeeDao.grantRole(id, roleNo);
		}
		
	}
		
	
	@Override
	public List<EmployeeModel> getListByCondition(int departmentNo, String sex, Date startDate, Date endDate,
			String name, int[] roles) throws Exception {
		
		return employeeDao.selectListByCondition(departmentNo, sex, startDate, endDate, name, roles);
	}
	@Override
	public List<EmployeeModel> getListByConditionWithPage(int departmentNo, String sex, Date startDate, Date endDate,
			String name, int[] roles, int rows, int page) throws Exception {
		
		return employeeDao.selectListByConditionWithPage(departmentNo, sex, startDate, endDate, name, roles, rows*(page-1)+1, rows*page);
	}

	//根据检索条件取得员工个数
	public int getCountByCondition(int departmentNo,String sex,Date startDate,Date endDate, String name, int[] roles) throws Exception{
		return employeeDao.selectCountByCondition(departmentNo, sex, startDate, endDate, name, roles);
	}
	//根据检索条件取得员工显示页数
	public int getPageCountByCondition(int departmentNo,String sex,Date startDate,Date endDate, String name, int[] roles,int rows) throws Exception{
		
		int pageCount=0;
		int count=this.getCountByCondition(departmentNo, sex, startDate, endDate, name, roles);
		if(count%rows==0) {
			pageCount=count/rows;
		}
		else {
			pageCount=count/rows+1;
		}
		
		return pageCount;
	}
	//取得指定员工的功能列表
	@Override
	public List<FunctionModel> getFucntionListByEmployee(String id) throws Exception {
		
		return employeeDao.selectFucntionsByEmployee(id);
	}
	@Override
	public boolean validate(String id, String password) throws Exception {
		boolean result=false;
		if(employeeDao.selectCountByIdAndPassword(id, password)>0) {
			result=true;
		}
		
		return result;
	}
	
	

}
