package com.bplow.todo.quartz.dmo;

public class quartzTrigger {
	
	private String triggerName;//触发器名称
	private String triggerGroup;//出发器组
	private String jobName;
	private String jobGroup;
	private String desc;
	private String triggerState;
	private String triggerType;
	private String misfireInstr;
	
	public quartzTrigger() {
		super();
	}

	public quartzTrigger(String triggerName, String triggerGroup,
			String jobName, String jobGroup, String desc, String triggerState,
			String triggerType, String misfireInstr) {
		super();
		this.triggerName = triggerName;
		this.triggerGroup = triggerGroup;
		this.jobName = jobName;
		this.jobGroup = jobGroup;
		this.desc = desc;
		this.triggerState = triggerState;
		this.triggerType = triggerType;
		this.misfireInstr = misfireInstr;
	}

	public String getTriggerName() {
		return triggerName;
	}

	public void setTriggerName(String triggerName) {
		this.triggerName = triggerName;
	}

	public String getTriggerGroup() {
		return triggerGroup;
	}

	public void setTriggerGroup(String triggerGroup) {
		this.triggerGroup = triggerGroup;
	}

	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	public String getJobGroup() {
		return jobGroup;
	}

	public void setJobGroup(String jobGroup) {
		this.jobGroup = jobGroup;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getTriggerState() {
		return triggerState;
	}

	public void setTriggerState(String triggerState) {
		this.triggerState = triggerState;
	}

	public String getTriggerType() {
		return triggerType;
	}

	public void setTriggerType(String triggerType) {
		this.triggerType = triggerType;
	}

	public String getMisfireInstr() {
		return misfireInstr;
	}

	public void setMisfireInstr(String misfireInstr) {
		this.misfireInstr = misfireInstr;
	}
	
}
