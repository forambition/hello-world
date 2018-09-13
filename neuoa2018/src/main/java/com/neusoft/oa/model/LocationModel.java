package com.neusoft.oa.model;

import java.io.Serializable;
import java.util.List;

import org.apache.ibatis.type.Alias;
/*
 * 部门的位置Model类
 */
@Alias("Location")
public class LocationModel implements Serializable {
	private int no=0; //序号
	private String room=null; //房间
	private String bulding=null; //楼宇
	private String address=null; //地址
	//关联的部门
	private List<DepartmentModel> departments=null;
		
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getRoom() {
		return room;
	}
	public void setRoom(String room) {
		this.room = room;
	}
	public String getBulding() {
		return bulding;
	}
	public void setBulding(String bulding) {
		this.bulding = bulding;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public List<DepartmentModel> getDepartments() {
		return departments;
	}
	public void setDepartments(List<DepartmentModel> departments) {
		this.departments = departments;
	}
	
	
}
