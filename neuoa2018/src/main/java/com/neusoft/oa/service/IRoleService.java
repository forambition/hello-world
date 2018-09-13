package com.neusoft.oa.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.neusoft.oa.model.FunctionModel;
import com.neusoft.oa.model.RoleModel;

/*
 * 角色业务接口
 */
public interface IRoleService {

	//增加角色
	public int add(RoleModel role) throws Exception;
	//修改角色
	public void modify(RoleModel role) throws Exception;
	//删除角色
	public void delete(RoleModel role) throws Exception;
	//取得指定的角色对象
	public RoleModel getByNo(int no) throws Exception;
	//取得所有角色列表，无关联的员工列表 无分页
	public List<RoleModel> getListWithoutEmployeesByAll() throws Exception;
	//取得所有角色列表，有关联的员工列表 无分页
	public List<RoleModel> getListWithEmployeesByAll() throws Exception;
		
	//取得所有角色列表， 有分页
	public List<RoleModel> getListByAllWithPage(int rows,int page) throws Exception;
	//取得角色的个数
	public int getCountByAll() throws Exception;
	//取得角色的页数
	public int getPageCountByAll(int rows) throws Exception;
	
	//为角色授予系统功能
	public void grantFunctionToRole(int roleNo,int[] functionNos) throws Exception;
	//取得指定角色的功能列表
	public List<FunctionModel> getFunctionsByRole(int roleNo) throws Exception;
}
