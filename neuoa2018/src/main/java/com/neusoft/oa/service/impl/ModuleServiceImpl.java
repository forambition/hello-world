package com.neusoft.oa.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.neusoft.oa.dao.IModuleDao;
import com.neusoft.oa.model.ModuleModel;
import com.neusoft.oa.service.IModuleService;
@Service
@Transactional
public class ModuleServiceImpl implements IModuleService {

	private IModuleDao moduleDao=null;
	@Autowired
	public void setModuleDao(IModuleDao moduleDao) {
		this.moduleDao = moduleDao;
	}

	@Override
	public List<ModuleModel> getListByAll() throws Exception {
		
		return moduleDao.selectListByAll();
	}

	@Override
	public ModuleModel getByNo(int no) throws Exception {
		
		return moduleDao.selectByNo(no);
	}

}
