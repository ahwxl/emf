package com.bplow.look.bass;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

public class BaseActionSupport extends ActionSupport
implements  SessionAware, ServletRequestAware,ServletResponseAware{
	
	/**
	 * 
	 */
	public Map<String, Object> session;
	public HttpServletRequest request;
	public HttpServletResponse response;
	protected Integer start;//ext起始页编号
	protected Integer limit;//ext每页显示条数
	
	@Override
	public void setSession(Map<String, Object> arg_session) {
		this.session = arg_session;
	}
	@Override
	public void setServletRequest(HttpServletRequest arg_servletRequest) {
		this.request = arg_servletRequest;		
	}
	@Override
	public void setServletResponse(HttpServletResponse arg_serletResponse) {
		this.response = arg_serletResponse;		
	}
	public HttpServletRequest getRequest() {
		return request;
	}
	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}
	public HttpServletResponse getResponse() {
		return response;
	}
	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}
	public Integer getStart() {
		return start;
	}
	public void setStart(Integer start) {
		this.start = start;
	}
	public Integer getLimit() {
		return limit;
	}
	public void setLimit(Integer limit) {
		this.limit = limit;
	}
	public Map<String, Object> getSession() {
		return session;
	}
	

}
