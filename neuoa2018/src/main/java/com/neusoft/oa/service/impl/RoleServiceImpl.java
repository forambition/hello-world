package com.neusoft.oa.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.neusoft.oa.dao.IRoleDao;
import com.neusoft.oa.model.FunctionModel;
import com.neusoft.oa.model.RoleModel;
import com.neusoft.oa.service.IRoleService;
/*
 * 角色业务实现类
 */
@Service
@Transactional
public class RoleServiceImpl implements IRoleService {
	private IRoleDao roleDao=null;
	@Autowired
	public void setRoleDao(IRoleDao roleDao) {
		this.roleDao = roleDao;
	}

	@Override
	public int add(RoleModel role) throws Exception {
		roleDao.create(role);
		return role.getNo();
	}

	@Override
	public void modify(RoleModel role) throws Exception {
		roleDao.update(role);
	}

	@Override
	public void delete(RoleModel role) throws Exception {
		roleDao.delete(role);
	}

	@Override
	public RoleModel getByNo(int no) throws Exception {
		return roleDao.selectByNo(no);
	}

	
	@Override
	public List<RoleModel> getListByAllWithPage(int rows, int page) throws Exception {
		return roleDao.selectListByAllWithPage(rows*(page-1), rows*page);
	}

	@Override
	public int getCountByAll() throws Exception {
		
		return roleDao.selectCountByAll();
	}

	@Override
	public List<RoleModel> getListWithoutEmployeesByAll() throws Exception {
		
		return roleDao.selectListWithoutEmployeesByAll();
	}

	@Override
	public List<RoleModel> getListWithEmployeesByAll() throws Exception {
		
		return roleDao.selectListWithEmployeesByAll();
	}

	@Override
	public void grantFunctionToRole(int roleNo, int[] functionNos) throws Exception {
		//删除所有功能的授权
		roleDao.revokeAllFunctions(roleNo);
		//对角色进行功能授权
		for(int functionNo:functionNos) {
			roleDao.grantFunction(roleNo, functionNo);
		}
	}

	@Override
	public List<FunctionModel> getFunctionsByRole(int roleNo) throws Exception {
		
		return roleDao.selectFunctionListByRole(roleNo);
	}

	@Override
	public int getPageCountByAll(int rows) throws Exception {
		int pageCount=0;
		int count=this.getCountByAll();
		if(count%rows==0) {
			pageCount=count/rows;
		}
		else {
			pageCount=count/rows+1;
		}
		return pageCount;
	}

}
