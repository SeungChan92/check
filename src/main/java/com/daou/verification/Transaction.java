package com.daou.verification;

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
		System.out.print(this.name + " : exception occur");
		System.out.println(" - " + e.getClass().getName());
	}
	public void logSuccess() {
		System.out.println(this.name + " : success");
	}
}
