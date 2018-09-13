package com.neusoft.oa.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.neusoft.oa.model.FunctionModel;
import com.neusoft.oa.model.RoleModel;

/*
 * 角色DAO接口
 * 
 */
public interface IRoleDao {
	//增加角色
	public void create(RoleModel role) throws Exception;
	//修改角色
	public void update(RoleModel role) throws Exception;
	//删除角色
	public void delete(RoleModel role) throws Exception;
	//取得指定的角色对象
	public RoleModel selectByNo(int no) throws Exception;
	//取得所有角色列表，无关联的员工列表 无分页
	public List<RoleModel> selectListWithoutEmployeesByAll() throws Exception;
	//取得所有角色列表，有关联的员工列表 无分页
	public List<RoleModel> selectListWithEmployeesByAll() throws Exception;
	//取得所有角色列表， 有分页
	public List<RoleModel> selectListByAllWithPage(@Param("start") int start,@Param("end") int end) throws Exception;
	//取得角色的个数
	public int selectCountByAll() throws Exception;
	
	//为角色授权系统功能, 单个授权功能,参数：角色号，系统功能编号
	public void grantFunction(@Param("roleNo") int roleNo,@Param("functionNo") int functionNo) throws Exception;
	//取得指定角色的系统功能列表
	public List<FunctionModel> selectFunctionListByRole(int roleNo) throws Exception;
	//撤销所有的功能授权,参数：角色编号
	public void revokeAllFunctions(int roleNo) throws Exception;
	
	
	
	

}
