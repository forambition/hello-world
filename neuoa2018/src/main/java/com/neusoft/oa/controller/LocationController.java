package com.neusoft.oa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.neusoft.oa.model.LocationModel;
import com.neusoft.oa.service.ILocationService;
/*
 * 位置控制器类
 */
@RestController
@RequestMapping("/location")
public class LocationController {

	public ILocationService locationService=null;
	@Autowired
	public void setLocationService(ILocationService locationService) {
		this.locationService = locationService;
	}
	@RequestMapping("/list/all")
	public List<LocationModel> getListByAll() throws Exception
	{
		return locationService.getListByAll();
	}
	
}
