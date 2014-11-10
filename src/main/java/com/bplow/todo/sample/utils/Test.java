package com.bplow.todo.sample.utils;

/**
 * 这是一个递归调用的例子
 * @author www
 *
 */
public class Test {
	
	static int num = 10;
	
	public static int getnum(int i){
		if(i >1){
			num = num +2;
		}else{
			return num;
		}
		getnum(--i);
		
		return num;
	}
	
	public static void main(String[] args) {
		System.out.println(getnum(8));
	}
	

}
