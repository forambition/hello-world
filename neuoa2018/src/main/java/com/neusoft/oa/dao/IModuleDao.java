package com.neusoft.oa.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.neusoft.oa.model.ModuleModel;

//系统模块DAO接口
@Mapper
public interface IModuleDao {
	//取得所有的模块列表
	public List<ModuleModel> selectListByAll() throws Exception;
	//取得指定的模块对象
	public ModuleModel selectByNo(int no) throws Exception;

}
