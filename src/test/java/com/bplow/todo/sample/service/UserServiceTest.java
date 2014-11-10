package com.bplow.todo.sample.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

import com.bplow.todo.sample.dmo.User;
import com.bplow.todo.sample.sevice.UserService;


@RunWith(SpringJUnit4ClassRunner.class)
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = false)  
@TestExecutionListeners( { TransactionalTestExecutionListener.class,DependencyInjectionTestExecutionListener.class })

@ContextConfiguration(locations = {  
        "classpath*:framework/spring-config/workflow/activiti-context.xml",
        "classpath*:framework/applicationContext.xml"})
public class UserServiceTest {
	
	@Autowired
	UserService userService;

	@Test
	public void addUserTest(){
		
		User user = new User();
		user.setUserid("test");
		userService.addUser(user);
	}
	
}
