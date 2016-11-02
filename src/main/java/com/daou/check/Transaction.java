package com.daou.check;

public abstract class Transaction {
	
	private String name;
	
	public Transaction(String name) {
		this.name = name;
	}
	
	public abstract void transact() throws Exception;
	
	public void logStart() {
		System.out.println(this.name + " : start");
	}
	public void logException(Exception e) {
		System.out.println(this.name + " : exception occur");
		e.printStackTrace();
	}
	public void logSuccess() {
		System.out.println(this.name + " : success");
	}
}
