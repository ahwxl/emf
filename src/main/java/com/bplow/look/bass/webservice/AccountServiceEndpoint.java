package com.bplow.look.bass.webservice;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebService;


@WebService(serviceName = "AccountService")
public class AccountServiceEndpoint {
	

	@WebMethod
	public void insertAccount(String acc) {
		System.out.println(acc);
	}

	@WebMethod
	public List getAccounts(String name) {
		return null;
	}
}
