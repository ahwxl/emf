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

import com.bplow.todo.sample.sevice.DataOperateService;



@RunWith(SpringJUnit4ClassRunner.class)
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = false)  
@TestExecutionListeners( { TransactionalTestExecutionListener.class,DependencyInjectionTestExecutionListener.class })

@ContextConfiguration(locations = {  
        "classpath*:applicationContext.xml"})
public class DataOperateServiceTest {
	
	@Autowired
	DataOperateService dataOperateService;
	
	@Test
	public void insertDataTest(){
		dataOperateService.insertData();
	}
	
	
	//@Test
	public void readExcelTest(){
		dataOperateService.readExcel();
	}

}
