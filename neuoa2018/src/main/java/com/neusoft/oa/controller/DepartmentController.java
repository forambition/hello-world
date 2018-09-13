package com.neusoft.oa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.neusoft.oa.model.DepartmentModel;
import com.neusoft.oa.result.ControllerResult;
import com.neusoft.oa.service.IDepartmentService;
/*
 * 部门控制器类
 * 
 */
@RestController
@RequestMapping("/department")
public class DepartmentController {
	private IDepartmentService departmentService=null;
	@Autowired
	public void setDepartmentService(IDepartmentService departmentService) {
		this.departmentService = departmentService;
	}
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public String add(DepartmentModel department) throws Exception{
		
		departmentService.add(department);
		return "ok";
		
	}
	@RequestMapping(value="/modify",method=RequestMethod.POST)
	public String modify(DepartmentModel department) throws Exception{
		departmentService.modify(department);
		return "ok";
	}
	@RequestMapping(value="/delete",method=RequestMethod.POST)
	public String delete(DepartmentModel department) throws Exception{
		departmentService.delete(department);
		return "ok";
	}
	
	@RequestMapping(value="/list/all",method=RequestMethod.GET)
	public List<DepartmentModel> getListByAll() throws Exception{
		return departmentService.getListByAll();
	}
	
	@RequestMapping(value="/list/all/withemloyees",method=RequestMethod.GET)
	public List<DepartmentModel> getListWithEmployeesByAll() throws Exception{
		return departmentService.getListWithEmployeesByAll();
	}
	
	@RequestMapping(value="/get",method=RequestMethod.GET)
	public DepartmentModel getByNo(int no) throws Exception{
		return departmentService.getByNo(no);
		
	}
	//检查部门能否被删除
	@RequestMapping(value="/checkcandelete",method=RequestMethod.GET)
	public ControllerResult checkCanDelete(int departmentNo) throws Exception{
		ControllerResult result=new ControllerResult();
		if(departmentService.checkCanDelete(departmentNo)) {
			result.setStatus("Y");
		}
		else {
			result.setStatus("N");
			result.setMessage("此部门不能被删除，有关联的员工");;
		}
		
		return result;
	}
	
	

}
