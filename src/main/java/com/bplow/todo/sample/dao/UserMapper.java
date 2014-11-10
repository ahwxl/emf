package com.bplow.todo.sample.dao;

import java.util.List;

import com.bplow.todo.sample.dmo.User;

public interface UserMapper {
	
	
	void addUser(User user);
	
	int delUser(User user);
	
	User queryUser(User user);
	
	int updateUser(User user);
	
	/**
	 * 查询用户列表
	 * @param user
	 * @return
	 */
	List<User> queryUserList(User user);
	
	/**
	 * 分页查询用户信息
	 * @param user
	 * @return
	 */
	List<User> queryUserForPage(User user);

}
