package com.bplow.todo.quartz.action;

import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

import org.quartz.SchedulerException;
import org.quartz.UnableToInterruptJobException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bplow.todo.quartz.service.QuartzService;


@Controller
public class QuartzControler {
	
	@Autowired
	QuartzService quartzService;
	
	@RequestMapping("/showAddConTrigger")
	public String goAddPage(){
		return "user/list";
	}
	
	@RequestMapping("/addConTrigger")
	@ResponseBody
	public String addConTrigger(){
		try {
			quartzService.addCronTriggerJob();
		} catch (SchedulerException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return "";
	}
	
	@RequestMapping("/intConTrigger")
	@ResponseBody
	public String itrConTrigger() throws Exception{
		quartzService.interruptJob("job1", "group1");

		return "";
	}
	
	@RequestMapping("/resumeConTrigger")
	@ResponseBody
	public Map<String, String> resumeConTrigger() throws Exception{
		quartzService.resumeTrigger("718a7628f57040be8428aeb44a586671", "group1");

		Map<String, String> map = new HashMap<String, String>();
		map.put("name", "123");
		map.put("info", "成功");
		return map;
	}
	

}
