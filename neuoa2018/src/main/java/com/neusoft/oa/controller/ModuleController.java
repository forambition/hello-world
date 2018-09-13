package com.neusoft.oa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.neusoft.oa.model.FunctionModel;
import com.neusoft.oa.model.ModuleModel;
import com.neusoft.oa.service.IModuleService;

@RestController
@RequestMapping("/module")
public class ModuleController {
	private IModuleService moduleService=null;
	@Autowired
	public void setModuleService(IModuleService moduleService) {
		this.moduleService = moduleService;
	}
	@RequestMapping("/list/all")
	public List<ModuleModel> getListByAll() throws Exception {
		
		return moduleService.getListByAll();
	}

	
}
