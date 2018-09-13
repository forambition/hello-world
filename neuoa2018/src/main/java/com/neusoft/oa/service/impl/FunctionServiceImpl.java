package com.neusoft.oa.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.neusoft.oa.dao.IFunctionDao;
import com.neusoft.oa.model.FunctionModel;
import com.neusoft.oa.service.IFunctionService;
@Service
@Transactional
public class FunctionServiceImpl implements IFunctionService {

	private IFunctionDao functionDao=null;
	@Autowired
	public void setFunctionDao(IFunctionDao functionDao) {
		this.functionDao = functionDao;
	}

	@Override
	public List<FunctionModel> getListByAll() throws Exception {
		
		return functionDao.selectListByAll();
	}

	@Override
	public List<FunctionModel> getListByEmployee(String id) throws Exception {
		
		return functionDao.selectListByEmployee(id);
	}

}
