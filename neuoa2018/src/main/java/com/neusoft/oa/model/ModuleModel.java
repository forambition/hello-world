package com.neusoft.oa.model;

import org.apache.ibatis.type.Alias;

//系统功能模块Model类
@Alias("Module")
public class ModuleModel {

	private int no=0;
	private String name=null;
	//
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
