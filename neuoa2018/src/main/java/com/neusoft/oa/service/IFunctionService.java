package com.neusoft.oa.service;

import java.util.List;

import com.neusoft.oa.model.FunctionModel;

//系统功能业务接口
public interface IFunctionService {
	
	//取得所有的系统功能的列表
	public List<FunctionModel> getListByAll() throws Exception;
	//取值指定员工所授予的功能列表
	public List<FunctionModel> getListByEmployee(String id) throws Exception;

}
