package com.neusoft.oa.service;

import java.util.List;

import com.neusoft.oa.model.ModuleModel;

public interface IModuleService {

	//取得所有的模块列表
	public List<ModuleModel> getListByAll() throws Exception;
	//取得指定的模块对象
	public ModuleModel getByNo(int no) throws Exception;
}
