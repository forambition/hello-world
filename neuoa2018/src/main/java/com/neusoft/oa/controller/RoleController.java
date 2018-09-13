package com.neusoft.oa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.neusoft.oa.model.FunctionModel;
import com.neusoft.oa.model.RoleModel;
import com.neusoft.oa.result.ControllerResult;
import com.neusoft.oa.result.GridResult;
import com.neusoft.oa.service.IRoleService;

/*
 * 角色控制器类
 */
@RestController
@RequestMapping("/role")
public class RoleController {

	private IRoleService roleService=null;
	@Autowired
	public void setRoleService(IRoleService roleService) {
		this.roleService = roleService;
	}
	//增加新角色
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public ControllerResult add(RoleModel role,@RequestParam(required=false) int[] rolefunctions) throws Exception{
		
		int roleNo=roleService.add(role);
		//
		if(rolefunctions!=null) {
			//为角色授权
			roleService.grantFunctionToRole(roleNo, rolefunctions);
		}
		//
		ControllerResult result=new ControllerResult();
		result.setStatus("Y");
		result.setMessage("增加角色成功");
		return result;
	}
	
	@RequestMapping("/list/all/page")
	public GridResult<RoleModel> getListByAllWithPage(@RequestParam(required=false,defaultValue="10") int rows,@RequestParam(required=false,defaultValue="1") int page) throws Exception{
	
		GridResult<RoleModel> result=new GridResult<RoleModel>();
		result.setRecords(roleService.getCountByAll());
		int pageCount=roleService.getPageCountByAll(rows);
		if(page>pageCount) {
			page=pageCount;
		}
		if(page<1) {
			page=1;
		}
		result.setPage(page);
		result.setTotal(pageCount);
		result.setRows(roleService.getListByAllWithPage(rows, page));
		return result;
	}
	
	@RequestMapping("/list/all/withoutemployees")
	public List<RoleModel> getListWithoutEmployeesByAll() throws Exception {
		
		return roleService.getListWithoutEmployeesByAll();
	}

	@RequestMapping("/list/all/withemployees")
	public List<RoleModel> getListWithEmployeesByAll() throws Exception {
		
		return roleService.getListWithEmployeesByAll();
	}

	@RequestMapping("/get")
	public RoleModel get(int roleNo) throws Exception {
		
		return roleService.getByNo(roleNo);
	}
	
	@RequestMapping("/functionListByRole")
	public List<FunctionModel> getFunctionListByRole(int roleNo) throws Exception {
		
		return roleService.getFunctionsByRole(roleNo);
	}
	
}
