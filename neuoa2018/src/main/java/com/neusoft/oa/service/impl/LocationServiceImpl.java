package com.neusoft.oa.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.neusoft.oa.dao.ILocationDao;
import com.neusoft.oa.model.LocationModel;
import com.neusoft.oa.service.ILocationService;
/*
 * 位置业务实现类
 */
@Service
@Transactional
public class LocationServiceImpl implements ILocationService {

	private ILocationDao locationDao=null;
	@Autowired
	public void setLocationDao(ILocationDao locationDao) {
		this.locationDao = locationDao;
	}

	@Override
	public void add(LocationModel location) throws Exception {
		locationDao.create(location);

	}

	@Override
	public void modify(LocationModel location) throws Exception {
		locationDao.update(location);

	}

	@Override
	public void delete(LocationModel location) throws Exception {
		locationDao.delete(location);

	}

	@Override
	public List<LocationModel> getListByAll() throws Exception {
		
		return locationDao.selectListByAll();
	}

	@Override
	public List<LocationModel> getListByAllWithPage(int rows, int page) throws Exception {
		
		return locationDao.selectListByAllWithPage(rows*(page-1), rows*page);
	}

	@Override
	public int getCountByAll() throws Exception {
		
		return locationDao.selectCountByAll();
	}

	@Override
	public LocationModel getByNo(int locationNo) throws Exception {
		
		return locationDao.selectByNo(locationNo);
	}

}
