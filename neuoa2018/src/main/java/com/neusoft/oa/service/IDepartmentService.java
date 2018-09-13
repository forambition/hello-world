package com.neusoft.oa.service;

import java.util.List;

import com.neusoft.oa.model.DepartmentModel;

//部门业务Service接口
public interface IDepartmentService {

	//增加部门
	public void add(DepartmentModel department) throws Exception;
	//修改部门
	public void modify(DepartmentModel department) throws Exception;
	//删除部门
	public void delete(DepartmentModel department) throws Exception;
	//取得所有的部门列表
	public List<DepartmentModel> getListByAll() throws Exception;
	//取得所有的部门列表,有关联的员工集合
	public List<DepartmentModel> getListWithEmployeesByAll() throws Exception;
		
	
	//取得指定的部门,参数：部门的序号
	public DepartmentModel getByNo(int departmentNo) throws Exception;
	//检查指定的部门是否可以被删除
	public boolean checkCanDelete(int departmentNo) throws Exception;
	

}
