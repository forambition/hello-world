package com.neusoft.oa.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;

import com.neusoft.oa.model.LocationModel;

/*
 * 位置DAO接口
 *  @Author 作者：吕海东
 */
//位置的DAO接口
public interface ILocationDao {
	//增加位置
	
	public void create(LocationModel location) throws Exception;
	//修改位置
	public void update(LocationModel location) throws Exception;
	//删除位置
	public void delete(LocationModel location) throws Exception;
	//取得所有的位置列表，无分页模式
	public List<LocationModel> selectListByAll() throws Exception;
	//取得所有的位置列表，有分页模式
	public List<LocationModel> selectListByAllWithPage(int start,int end) throws Exception;
	
	//取得指定的位置,参数：位置的序号
	public LocationModel selectByNo(int locationNo) throws Exception;
	//取得所有位置个数
	public int selectCountByAll() throws Exception;
	
	
	

}
