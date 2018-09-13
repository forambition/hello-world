package com.neusoft.oa.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.neusoft.oa.dao.IDepartmentDao;
import com.neusoft.oa.dao.IEmployeeDao;
import com.neusoft.oa.model.DepartmentModel;
import com.neusoft.oa.service.IDepartmentService;

@Service
@Transactional
public class DepartmentServiceImplWithSpring implements IDepartmentService {
	private IDepartmentDao departmentDao=null;
	private IEmployeeDao employeeDao=null;
	
	@Autowired
	public void setDepartmentDao(IDepartmentDao departmentDao) {
		this.departmentDao = departmentDao;
	}
	
	@Autowired
	public void setEmployeeDao(IEmployeeDao employeeDao) {
		this.employeeDao = employeeDao;
	}


	@Override
	public void add(DepartmentModel department) throws Exception {
		departmentDao.create(department);
		int no=department.getNo();
		System.out.println("NO="+no);
		

	}

	@Override
	public void modify(DepartmentModel department) throws Exception {
		departmentDao.update(department);

	}

	@Override
	public void delete(DepartmentModel department) throws Exception {
		departmentDao.delete(department);

	}

	@Override
	public List<DepartmentModel> getListByAll() throws Exception {
		
		return departmentDao.selectListByAll();
	}

	@Override
	public DepartmentModel getByNo(int departmentNo) throws Exception {
		
		return departmentDao.selectByNo(departmentNo);
	}

	@Override
	public List<DepartmentModel> getListWithEmployeesByAll() throws Exception {
		
		return departmentDao.selectListWithEmployeesByAll();
		
	}

	@Override
	public boolean checkCanDelete(int departmentNo) throws Exception {
		boolean result=true;
		if(employeeDao.selectCountByCondition(departmentNo, "", null, null, null, null)>0) {
			result=false;
		}
		
		return result;
	}

}
