package com.neusoft.oa.model;

import org.apache.ibatis.type.Alias;

//系统功能Model类
@Alias("Function")
public class FunctionModel {
	private int no=0;
	private String name=null; //功能名称
	private String url=null; //功能主页的请求地址
	private ModuleModel module=null; //功能所在的模块
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
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public ModuleModel getModule() {
		return module;
	}
	public void setModule(ModuleModel module) {
		this.module = module;
	}
	
	

}
