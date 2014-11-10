package com.bplow.todo.sample.sevice;

import java.util.List;

import com.bplow.todo.sample.dao.DataOperateJdbcDao;


public class DataOperateService {
	
	DataOperateJdbcDao dataOperateJdbcDao;

	public void insertData(){
		dataOperateJdbcDao.insertData();
	}
	
	
	public void readExcel(){
		
		List list = dataOperateJdbcDao.queryData();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public DataOperateJdbcDao getDataOperateJdbcDao() {
		return dataOperateJdbcDao;
	}

	public void setDataOperateJdbcDao(DataOperateJdbcDao dataOperateJdbcDao) {
		this.dataOperateJdbcDao = dataOperateJdbcDao;
	}
	
	

}
