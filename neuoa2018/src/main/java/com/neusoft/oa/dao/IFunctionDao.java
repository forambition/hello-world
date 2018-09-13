package com.neusoft.oa.dao;

import java.util.List;

import com.neusoft.oa.model.FunctionModel;

//系统功能DAO接口
public interface IFunctionDao {
	//取得所有的系统功能的列表
	public List<FunctionModel> selectListByAll() throws Exception;
	//取值指定员工所授予的功能列表
	public List<FunctionModel> selectListByEmployee(String id) throws Exception;
		
	
	
}
