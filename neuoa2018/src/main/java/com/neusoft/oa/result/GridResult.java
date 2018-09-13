package com.neusoft.oa.result;

import java.util.List;

import com.neusoft.oa.model.EmployeeModel;

//用于JQGrid结果的类

public class GridResult<T> {
  private int page=0; //当前页号
  private int total=0;  //总页数
  private int records=0; //总个数
  private List<T> rows=null;
public int getPage() {
	return page;
}
public void setPage(int page) {
	this.page = page;
}
public int getTotal() {
	return total;
}
public void setTotal(int total) {
	this.total = total;
}
public int getRecords() {
	return records;
}
public void setRecords(int records) {
	this.records = records;
}
public List<T> getRows() {
	return rows;
}
public void setRows(List<T> rows) {
	this.rows = rows;
}
  
  
}
