package org.springframework.samples.mvc.simple;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class ShowInfo {
	
	@RequestMapping("/showinfo")
	@ResponseBody
	public String showinfo(){
		return "hello 123";
	}
	
	
	@RequestMapping("/abc")
	public String showpage(){
		return "showinfo";
	}
	
	/**
	 * Ìí¼Ó
	 */

	/**
	 * 
	 */
}
