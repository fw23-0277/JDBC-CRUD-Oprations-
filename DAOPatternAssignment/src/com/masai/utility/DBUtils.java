package com.masai.utility;

import java.util.ResourceBundle;

public class DBUtils {
	
	public static void main(String[] args) {
		ResourceBundle rb = ResourceBundle.getBundle("dbdetails");
		
	
	
		System.out.println(rb.getString("URL"));
		System.out.println(rb.getString("password"));
	}
}
