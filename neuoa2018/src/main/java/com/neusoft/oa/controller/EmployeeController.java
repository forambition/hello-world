package com.neusoft.oa.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.neusoft.oa.dao.IEmployeeDao;
import com.neusoft.oa.model.EmployeeModel;
import com.neusoft.oa.model.FunctionModel;
import com.neusoft.oa.result.ControllerResult;
import com.neusoft.oa.result.GridResult;
import com.neusoft.oa.service.IEmployeeSerivce;

/*
 * 员工控制器类
 * 吕海东
 */
@RestController
@RequestMapping(value="/employee")
public class EmployeeController {
	
	private IEmployeeSerivce employeeService=null;
	@Autowired
	public void setEmployeeService(IEmployeeSerivce employeeService) {
		this.employeeService = employeeService;
	}
	//增加员工
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public ControllerResult  add(EmployeeModel employee,@RequestParam(required=false) MultipartFile empphoto,@RequestParam(required=false) int[] emproles) throws Exception{
		if(empphoto==null || empphoto.isEmpty()) {
			//无图片提交
			employeeService.addWithoutPhoto(employee);
		}
		else {
			//有图片提交
			String fileName=empphoto.getOriginalFilename();
			String contentType=empphoto.getContentType();
			employee.setPhoto(empphoto.getBytes());
			employee.setPhotoFileName(fileName);
			employee.setPhotoContentType(contentType);
			employeeService.addWithPhoto(employee);
		}
		//为员工授予角色
		if(emproles!=null) {
			
			employeeService.grantRoles(employee.getId(), emproles);
		}
		ControllerResult result=new ControllerResult();
		result.setStatus("OK");
		result.setMessage("增加员工成功!");
		
		return result;
		
		
	}
	//增加员工
	@RequestMapping(value="/modify",method=RequestMethod.POST)
	public ControllerResult modify(EmployeeModel employee,@RequestParam(required=false) MultipartFile empphoto,@RequestParam(required=false) int[] emproles) throws Exception{
		if(empphoto==null || empphoto.isEmpty()) {
			//无图片提交
			employeeService.modifyWithoutPhoto(employee);
		}
		else {
			//有图片提交
			String fileName=empphoto.getOriginalFilename();
			String contentType=empphoto.getContentType();
			employee.setPhoto(empphoto.getBytes());
			employee.setPhotoFileName(fileName);
			employee.setPhotoContentType(contentType);
			employeeService.modifyWithPhoto(employee);
		}
		//为员工授予角色
		if(emproles!=null) {
			
			employeeService.grantRoles(employee.getId(), emproles);
		}
		ControllerResult result=new ControllerResult();
		result.setStatus("OK");
		result.setMessage("修改员工成功!");
		
		return result;
		
		
	}
		
	//取得单个的员工对象
	@RequestMapping(value="/get",method=RequestMethod.GET)
	public EmployeeModel getById(String id) throws Exception {
		
		return employeeService.getById(id);
	}

	//取得所有员工列表，无分页，无关联的部门对象
	@RequestMapping(value="/list/all/withoutdepartment")
	public List<EmployeeModel> getListWithoutDepartmentByAll() throws Exception
	{
		return employeeService.getListWithoutDepartmentByAll();
	}
	//取得所有员工列表，无分页，无关联的部门对象
	@RequestMapping(value="/list/all/withdepartment")
	public List<EmployeeModel> getListWithDepartmentByAll() throws Exception
	{
		return employeeService.getListWithDepartmentByAll();
	}
	//取得所有员工列表，无分页，取得关联的角色列表
	@RequestMapping(value="/list/all/withroles")
	public List<EmployeeModel> getListWithRolesByAll() throws Exception {
		
		return employeeService.getListWithRolesByAll();
	}
	//为员工授予指定的角色
	@RequestMapping(value="/grant/role")
	public String grantRole(String id, int roleNo) throws Exception {
		employeeService.grantRole(id, roleNo);
		return "ok";
		
	}
	
	@RequestMapping(value="/list/condition")
	public List<EmployeeModel> getListByCondition(@RequestParam(required=false,defaultValue="0") int departmentNo, 
			@RequestParam(required=false,defaultValue="") String sex, 
			@RequestParam(required=false) @DateTimeFormat(pattern="yyyy-MM-dd") Date startDate, 
			@RequestParam(required=false) @DateTimeFormat(pattern="yyyy-MM-dd") Date endDate,
			@RequestParam(required=false,defaultValue="") String name, 
			@RequestParam(required=false) int[] roles) throws Exception {
		if(name!=null&&name.trim().length()>0) {
			name="%"+name+"%";
			
		}
		return employeeService.getListByCondition(departmentNo, sex, startDate, endDate, name, roles);
	}
	@RequestMapping(value="/list/condition/page",method=RequestMethod.POST)
	public GridResult<EmployeeModel>  getByConditionWithPage(@RequestParam(required=false,defaultValue="0") int departmentNo, 
			@RequestParam(required=false,defaultValue="") String sex, 
			@RequestParam(required=false) @DateTimeFormat(pattern="yyyy-MM-dd") Date startDate, 
			@RequestParam(required=false) @DateTimeFormat(pattern="yyyy-MM-dd") Date endDate,
			@RequestParam(required=false,defaultValue="") String name, 
			@RequestParam(required=false) int[] roles, 
			@RequestParam(required=false,defaultValue="10") int rows, 
			@RequestParam(required=false,defaultValue="1") int page) throws Exception {
		//
	
		
		if(name!=null&&name.trim().length()>0) {
			name="%"+name+"%";
		}
		GridResult<EmployeeModel>  result=new GridResult<EmployeeModel>();
		
		result.setRecords(employeeService.getCountByCondition(departmentNo, sex, startDate, endDate, name, roles));
		int pageCount=employeeService.getPageCountByCondition(departmentNo, sex, startDate, endDate, name, roles, rows);
		if(page>pageCount) {
			page=pageCount;
		}
		if(page<1) {
			page=1;
		}
		result.setPage(page);
		result.setTotal(pageCount);
		result.setRows(employeeService.getListByConditionWithPage(departmentNo, sex, startDate, endDate, name, roles, rows, page));
		
		return result;
	}
	//检查员工ID是否可用于新员工，返回true 表示不存在，可以使用；false：ID已经存在，不能使用
	//用于jQuery Validate remote的验证规则
	@RequestMapping(value="checkIDCanBeUsed",method=RequestMethod.POST)
	public boolean checkIDCanBeUsed(String employeeId) throws Exception{
		System.out.println("ID:"+employeeId);
		boolean result=true;
		if(employeeService.getById(employeeId)!=null) {
			result=false;
		}
		
		return result;
	}
	
	//取得指定员工被授予的功能列表，用于生成员工登录后 左面的功能菜单选择列表
	@RequestMapping(value="/getGrantFunctions",method=RequestMethod.GET)
	public List<FunctionModel> getFucntionListByEmployee(String id) throws Exception {
		
		return employeeService.getFucntionListByEmployee(id);
	}
	//员工的登录验证方法
	@RequestMapping(value="/validate",method=RequestMethod.POST)
	public ControllerResult validate(String id,String password,HttpSession session) throws Exception{
		
		ControllerResult result=new ControllerResult();
		if(employeeService.validate(id, password)) {
			EmployeeModel em=employeeService.getById(id);
			session.setAttribute("employeeInfo", em);
			
			result.setStatus("Y");
			result.setMessage("员工验证通过");
		}
		else {
			result.setStatus("N");
			result.setMessage("员工验证失败");
		}
		
		return result;
	}
	
	//检查员工是否登录
	@RequestMapping(value="/checkLogin",method=RequestMethod.GET)
	public ControllerResult checkLogin(HttpSession session) throws Exception{
		
		ControllerResult result=new ControllerResult();
		if(session.getAttribute("employeeInfo")!=null) {
			
			result.setStatus("Y");
			result.setMessage("员工已登录");
		}
		else {
			result.setStatus("N");
			result.setMessage("员工未登录");
		}
		
		return result;
	}
	//取得已经登录员工的对象
	@RequestMapping(value="/getLoginEmployee",method=RequestMethod.GET)
	public EmployeeModel getEmployeeInfoFromSession(HttpSession session) throws Exception{
		return (EmployeeModel)session.getAttribute("employeeInfo");
	}
	//登录注销方法
	@RequestMapping(value="/logout",method=RequestMethod.GET)
	public ControllerResult logout(HttpSession session) throws Exception{
		
		session.invalidate(); //销毁session对象
		ControllerResult result=new ControllerResult();
		
		result.setStatus("Y");
		result.setMessage("员工注销成功");
		
		
		
		return result;
		
	}
	
	@RequestMapping(value="/showphoto",method=RequestMethod.GET)
	public 	ResponseEntity<byte[]> showPhoto02(String id) throws Exception{
		EmployeeModel em=employeeService.getById(id);
		if(em!=null&&em.getPhotoFileName()!=null) {
			byte[] photo=em.getPhoto();
			String fileName=em.getPhotoFileName();
			String contentType=em.getPhotoContentType();
			String mainType=contentType.substring(0,contentType.indexOf("/"));
			String subType=contentType.substring(contentType.indexOf("/")+1);
			String dfileName = new String(fileName.getBytes("UTF-8"), "iso8859-1");
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(new MediaType(mainType,subType));
			headers.setContentDispositionFormData("attachment", dfileName); 
			
			return new ResponseEntity<byte[]>(photo, headers, HttpStatus.CREATED); 
			
		}
		return null;
	}
	

}
