package com.daou.chasedae.web_test.common;

public class Fail extends Throwable{
	private String category;
	private String method;
	private Parameter[] parameters;
	public Exception e;
	

	public Fail(String category, String method, Parameter[] parameters, Exception e) {
		this.category = category;
		this.method = method;
		this.parameters = parameters;
		this.e = e;
	}
	
	public String buildMessage() {
		String message = "Fail\n";
		message += "\n";
		message += "\t\tCategory : " + category + "\n";
		message += "\t\tMethod : " + method + "\n";
		for (int i=0; i<parameters.length; i++)
		{
			message += "\t\t" + parameters[i].key + "\t: " + parameters[i].value + "\n";
		}
		message += "\n\t\tException : " + e.getClass() + "\n";
		
		return message;
	}
}
