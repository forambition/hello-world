package com.neusoft.oa.service;

import java.util.List;

import com.neusoft.oa.model.LocationModel;

//位置业务Service接口
public interface ILocationService {

	//增加位置
	public void add(LocationModel location) throws Exception;
	//修改位置
	public void modify(LocationModel location) throws Exception;
	//删除位置
	public void delete(LocationModel location) throws Exception;
	//取得所有的位置列表
	public List<LocationModel> getListByAll() throws Exception;
	//取得所有的位置列表,分页模式
	public List<LocationModel> getListByAllWithPage(int rows,int page) throws Exception;
	//取得所有位置的个数
	public int getCountByAll() throws Exception;
	//取得指定的位置,参数：位置的序号
	public LocationModel getByNo(int locationNo) throws Exception;

}
