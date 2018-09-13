package com.neusoft.oa.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.neusoft.oa.model.EmployeeModel;
import com.neusoft.oa.model.FunctionModel;

/*
 * 员工DAO层接口
 * 开发者：吕海东
 */
public interface IEmployeeDao {
	//增加员工，无图片
	public void createWithoutPhoto(EmployeeModel employee) throws Exception;
	//增加员工，有图片
	public void createWithPhoto(EmployeeModel employee) throws Exception;
	//修改员工，无图片
	public void updateWithoutPhoto(EmployeeModel employee) throws Exception;
	//修改员工，有图片
	public void updateWithPhoto(EmployeeModel employee) throws Exception;
	//删除员工
	public void delete(EmployeeModel employe) throws Exception;
	
	
	//取得指定的员工信息 （取得单个对象）
	public EmployeeModel selectById(String id) throws Exception;
	//====================================员工查询===========================================================
	
	//取得所有员工列表,无关联的部门，无分页
	public List<EmployeeModel> selectListWithoutDepartmentByAll() throws Exception;
	//取得所有员工列表,有关联的部门，无分页
	public List<EmployeeModel> selectListWithDepartmentByAll() throws Exception;
	//取得所有员工列表,有关联的角色列表，无分页
	public List<EmployeeModel> selectListWithRolesByAll() throws Exception;
	
	//取得所有员工列表，有分页
	public List<EmployeeModel> selectListByAllWithPage(int rows,int page) throws Exception;
	//取得指定部门的员工列表，无分页
	public List<EmployeeModel> selectListByDepartment(int departmentNo) throws Exception;
	//取得指定部门的员工列表，有分页
	public List<EmployeeModel> selectListByDepartmentWithPage(int departmentNo,int rows,int page) throws Exception;
	//根据检索条件取得员工的列表,无分页
	public List<EmployeeModel> selectListByCondition(@Param("departmentNo") int departmentNo, @Param("sex") String sex,@Param("startDate")  Date startDate,@Param("endDate")  Date endDate,@Param("name")  String name,@Param("roles")  int[] roles) throws Exception;
	//根据检索条件取得员工的列表,有分页
	public List<EmployeeModel> selectListByConditionWithPage(@Param("departmentNo") int departmentNo, @Param("sex") String sex,@Param("startDate")  Date startDate,@Param("endDate")  Date endDate,@Param("name")  String name,@Param("roles")  int[] roles,@Param("start")  int start,@Param("end")  int end) throws Exception;
	//根据检索条件取得员工个数
	public int selectCountByCondition(@Param("departmentNo") int departmentNo, @Param("sex") String sex,@Param("startDate")  Date startDate,@Param("endDate")  Date endDate,@Param("name")  String name,@Param("roles")  int[] roles) throws Exception;
	//根据员工账号和密码取得员工个数
	public int selectCountByIdAndPassword(@Param("id") String id,@Param("password") String password) throws Exception;
	//================================与角色相关的方法==============================================
	
	//取得指定角色的员工列表，无分页,不取关联的角色集合
	public List<EmployeeModel> selectListWithoutRolesByRole(int roleNo) throws Exception;
	//为员工授予指定的角色
	public void grantRole(@Param("id") String id,@Param("roleNo") int roleNo) throws Exception;
	//清除指定员工的所有授予的角色
	public void revokeAllRoles(@Param("id") String id) throws Exception;
	
	//取得指定员工的授予的功能列表
	public List<FunctionModel> selectFucntionsByEmployee(@Param("id") String id) throws Exception;
	
	
	
	
	

}
