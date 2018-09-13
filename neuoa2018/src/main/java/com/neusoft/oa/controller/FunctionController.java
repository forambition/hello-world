package com.neusoft.oa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.neusoft.oa.model.FunctionModel;
import com.neusoft.oa.service.IFunctionService;

//系统功能控制器
@RestController
@RequestMapping("/function")
public class FunctionController {
	private IFunctionService functionService=null;
	@Autowired
	public void setFunctionService(IFunctionService functionService) {
		this.functionService = functionService;
	}
	@RequestMapping("/list/all")
	public List<FunctionModel> getListByAll() throws Exception
	{
		return functionService.getListByAll();
	}
	

}
