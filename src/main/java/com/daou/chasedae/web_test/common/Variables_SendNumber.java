package com.daou.chasedae.web_test.common;

public class Variables_SendNumber {

	public Variables_SendNumber() {	}
	
	private static boolean already_made = false;
	
	public static String no_1 = "1000000000";
	public static String no_10 = "";
	public static String no_100 = "";
	
	public static void make() {
		if (already_made) return;
		
		for (int i=0 ;i<10; i++)
		{
			no_10 += '0' + Integer.toString(1000000000 + i) + '\n';
		}
		
		for (int i=0 ;i<100; i++)
		{
			no_100 += '0' + Integer.toString(1000000000 + i) + '\n';
		}
		
		already_made = true;
	}

}
