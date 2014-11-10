package com.bplow.todo.sample.action;

import java.util.List;

import javax.servlet.ServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bplow.look.bass.paging.Page;
import com.bplow.todo.sample.dmo.User;
import com.bplow.todo.sample.sevice.UserService;


/**
 * 
 * 这个是对user表的增删改查的例子
 * @author www
 *
 */

@Controller
public class UserAction {
	
	private int pageSize = 3;
	
	@Autowired
	UserService userService;
	
	
	
	/**
	 * 查询用户列表
	 * @param user
	 * @param modelMap
	 * @return
	 */
	@RequestMapping("/showlist")
	public String showUserPage(User user,Page page,ModelMap modelMap,ServletRequest request){
		List<User> userlist = userService.queryUserList(user,page);
		
		modelMap.addAttribute("userlist", userlist);
		modelMap.put("page", page);
		request.setAttribute("page", page);
		return "user/list";
	}
	
	
	@RequestMapping("/addUserPage")
	public String addUserPage(User user){
		return "user/add";
	}
	/**
	 * 添加用户
	 * @return
	 */
	@RequestMapping("/add")
	public String addUser(User user,ModelMap modelMap,ServletRequest request){
		/*user.setUserid("wangxioalei");
		user.setUserName("");
		user.setUserpsw("");*/
		userService.addUser(user);
		user.setUserid(null);
		return showUserPage(user,new Page(),modelMap,request);
	}
	
	/**
	 * 添加用户 给ajax 使用
	 * @param user
	 * @param modelMap
	 * @return
	 */
	@RequestMapping("/addByAjax")
	@ResponseBody
	public String addUserForAjax(User user,ModelMap modelMap){
		/*user.setUserid("wangxioalei");
		user.setUserName("");
		user.setUserpsw("");*/
		userService.addUser(user);
		user.setUserid(null);
		return "恭喜你，添加成功";
	}
	
	/**
	 * 修改用户
	 */
	@RequestMapping("/update")
	public String updateUser(User user,ModelMap modelMap,ServletRequest request){
		
        userService.updateUser(user);
		
		return showUserPage(user,new Page(),modelMap,request);
	}
	
	
	
	/**
	 * 删除用户
	 */
	@RequestMapping("/del")
	public String delUser(User user,ModelMap modelMap,ServletRequest request){
		
		userService.delUser(user);
		
		return showUserPage(user,new Page(),modelMap,request);
	}

}
