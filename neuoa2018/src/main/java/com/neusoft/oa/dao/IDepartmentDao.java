package com.neusoft.oa.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;

import com.neusoft.oa.model.DepartmentModel;

/*
 *  @Author 作者：吕海东
 */
//部门的DAO接口
public interface IDepartmentDao {
	//增加部门
	//@Insert("insert into OA_Department (DEPTNO,DEPTCODE,DEPTNAME) values (OA_DEPT_NEXTNO.nextval,#{code},#{name})")
	public void create(DepartmentModel department) throws Exception;
	//修改部门
	public void update(DepartmentModel department) throws Exception;
	//删除部门
	public void delete(DepartmentModel department) throws Exception;
	//取得所有的部门列表，无分页模式，无关联的员工列表
	public List<DepartmentModel> selectListByAll() throws Exception;
	//取得所有的部门列表，无分页模式，取得关联的员工列表
	public List<DepartmentModel> selectListWithEmployeesByAll() throws Exception;
	
	//取得所有的部门列表，有分页模式
	public List<DepartmentModel> selectListByAllWithPage(int rows,int page) throws Exception;
	
	//取得指定的部门,参数：部门的序号
	public DepartmentModel selectByNo(int departmentNo) throws Exception;
	//取得指定的部门,参数：部门的序号,不取关联的员工集合
	public DepartmentModel selectByNoWithoutEmployee(int departmentNo) throws Exception;
	//取得所有部门个数
	public int selectCountByAll() throws Exception;
	
	
	
	

}
