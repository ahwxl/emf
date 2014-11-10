package com.bplow.todo.sample.dmo;

import java.io.Serializable;

public class User implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private int id;
	private String userName;
	private String userid;
	private String userpsw;
	
	
	
	public User() {
		super();
	}



	public User(int id, String userName, String userid, String userpsw) {
		super();
		this.id = id;
		this.userName = userName;
		this.userid = userid;
		this.userpsw = userpsw;
	}



	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public String getUserName() {
		return userName;
	}



	public void setUserName(String userName) {
		this.userName = userName;
	}



	public String getUserid() {
		return userid;
	}



	public void setUserid(String userid) {
		this.userid = userid;
	}



	public String getUserpsw() {
		return userpsw;
	}



	public void setUserpsw(String userpsw) {
		this.userpsw = userpsw;
	}



	@Override
	public String toString() {
		return "User [id=" + id + ", userName=" + userName + ", userid="
				+ userid + ", userpsw=" + userpsw + "]";
	}
	
	
	
	

}
