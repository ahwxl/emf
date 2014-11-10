package com.bplow.todo.quartz.service;

import java.text.ParseException;
import java.util.Date;
import java.util.UUID;

import org.quartz.CronTrigger;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.UnableToInterruptJobException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Service;

import com.bplow.todo.quartz.job.SimpleJob;



@Service
public class QuartzService {
	
	Logger log = LoggerFactory.getLogger(QuartzService.class);
	
	@Autowired
	private SchedulerFactoryBean QuartzScheduler =null;
	
	public void addCronTriggerJob() throws SchedulerException, ParseException{
		String triggername = UUID.randomUUID().toString().replace("-","");
		Scheduler sched = QuartzScheduler.getScheduler();
		JobDetail job = new JobDetail("job1", "group1", SimpleJob.class);
		// job 1 will run every 20 seconds
		CronTrigger trigger = new CronTrigger(triggername, "group1", "job1",
                "group1", "0/20 * * * * ?");
		sched.addJob(job, true);
		Date ft = sched.scheduleJob(trigger);
		log.info(job.getFullName() + " has been scheduled to run at: " + ft
                + " and repeat based on expression: "
                + trigger.getCronExpression());
		
	}
	
	
	/**
	 * 暂停job
	 * @throws SchedulerException 
	 */
	public void interruptJob(String triggerName,String groupName) throws SchedulerException{
		Scheduler sched = QuartzScheduler.getScheduler();
		//sched.interrupt(jobName, groupName);
		
		sched.pauseTrigger(triggerName, groupName);
		
	}
	
	/**
	 * 启动trigger
	 * @throws SchedulerException 
	 */
	public void resumeTrigger(String triggerName,String groupName) throws SchedulerException{
		Scheduler sched = QuartzScheduler.getScheduler();
		
		sched.resumeTrigger(triggerName, groupName);
	}
	
	
	

}
