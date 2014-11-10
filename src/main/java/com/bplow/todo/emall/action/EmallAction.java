package com.bplow.todo.emall.action;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;



@Controller
public class EmallAction {
	
	/**
	 * 显示首页
	 * @return
	 */
	@RequestMapping("/index")
	public String showMain(){
		
		
		return "emall/index";
	}
	@RequestMapping("/showproduct")
	public String showProduct() {

		
		
		
		return "emall/product_view";
	}

	@RequestMapping("/cashier")
	public String showCashier() {

		return "";
	}
	
	public String show() {

		return "";
	}

}
