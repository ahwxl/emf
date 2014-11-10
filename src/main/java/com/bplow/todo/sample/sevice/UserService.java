package com.bplow.todo.sample.sevice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bplow.look.bass.paging.Page;
import com.bplow.todo.sample.dao.UserMapper;
import com.bplow.todo.sample.dmo.User;


@Service
public class UserService {
	
	@Autowired
	private UserMapper userDao;
	
	/**
	 * 添加用户
	 * @param user
	 */
	public void addUser(User user){
		userDao.addUser(user);
	}
	
	/**
	 * 查询用户列表
	 * @param user
	 * @return
	 */
	public List<User> queryUserList(User user){
		return userDao.queryUserList(user);
	}
	
	public List<User> queryUserList(User user,Page page){
		Map map = new HashMap();
		map.put("user_id",user.getUserid());
		map.put("page", page);
		return userDao.queryUserForPage(map);
	}
	
	/**
	 * 删除用户
	 * @param user
	 */
	public void delUser(User user){
		userDao.delUser(user);
	}
	
	public void updateUser(User user){
		userDao.updateUser(user);
	}
	

}
