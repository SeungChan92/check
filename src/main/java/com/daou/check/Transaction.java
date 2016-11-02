package com.daou.check;

public abstract class Transaction {
	
	private String name;
	
	public Transaction(String name) {
		this.name = name;
	}
	
	public abstract void transact() throws Exception;
	public void fail(Exception e) {
		System.out.println(this.name + " : fail");
		e.printStackTrace();
	}
	public void success() {
		System.out.println(this.name + " : success");
	}
}
