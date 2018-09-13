package com.neusoft.oa.service.impl;

import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.stereotype.Service;

import com.neusoft.oa.dao.IDepartmentDao;
import com.neusoft.oa.model.DepartmentModel;
import com.neusoft.oa.service.IDepartmentService;

//@Service
public class DepartmentServiceImpl implements IDepartmentService {

	@Override
	public void add(DepartmentModel department) throws Exception {
		
		String resource = "lhd-config.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		SqlSession session = sqlSessionFactory.openSession();
		//session.insert("insert into OA_Department (DEPTNO,DEPTCODE,DEPTNAME) values (OA_DEPT_NEXTNO.nextval,#{code},#{name})",department);
		
		IDepartmentDao departmentDao=session.getMapper(IDepartmentDao.class);
		departmentDao.create(department);
		session.commit();
		session.close();
			
		
	}

	@Override
	public void modify(DepartmentModel department) throws Exception {
		String resource = "lhd-config.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		SqlSession session = sqlSessionFactory.openSession();
		IDepartmentDao departmentDao=session.getMapper(IDepartmentDao.class);
		departmentDao.update(department);
		session.commit();
		session.close();

	}

	@Override
	public void delete(DepartmentModel department) throws Exception {
		String resource = "lhd-config.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		SqlSession session = sqlSessionFactory.openSession();
		IDepartmentDao departmentDao=session.getMapper(IDepartmentDao.class);
		departmentDao.delete(department);
		session.commit();
		session.close();

	}

	@Override
	public List<DepartmentModel> getListByAll() throws Exception {
		String resource = "lhd-config.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		SqlSession session = sqlSessionFactory.openSession();
		IDepartmentDao departmentDao=session.getMapper(IDepartmentDao.class);
		List<DepartmentModel> list=departmentDao.selectListByAll();
		session.commit();
		session.close();
		
		return list;
	}

	@Override
	public DepartmentModel getByNo(int departmentNo) throws Exception {
		
		String resource = "lhd-config.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		SqlSession session = sqlSessionFactory.openSession();
		IDepartmentDao departmentDao=session.getMapper(IDepartmentDao.class);
		DepartmentModel dm=departmentDao.selectByNo(departmentNo);
		session.commit();
		session.close();
		
		return dm;
	}

	@Override
	public List<DepartmentModel> getListWithEmployeesByAll() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean checkCanDelete(int departmentNo) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

}
