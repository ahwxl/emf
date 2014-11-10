package com.bplow.todo.sample.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.bplow.look.bass.BaseJdbcDaoSupport;
import com.bplow.todo.sample.dmo.User;


public class DataOperateJdbcDao extends BaseJdbcDaoSupport{
	
	/**
	 * 插入数据
	 */
	public void insertData(){
		this.getJdbcTemplate().update("insert into user(user_id,user_name) values(?,?) ","网络信任","zhangsan");
		System.out.println("insert into data");
	}
	
	public List<User> queryData(){
		return this.getJdbcTemplate().query("select user_id from user", new RowMapper() {
			 public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
				 User user=new User();
				 user.setUserid(rs.getString("user_id"));
			     return user;
			 }
			 });
	}

}
