package com.daou.check.service.suite;

import java.util.ArrayList;
import java.util.List;

import com.daou.check.Transaction;

public interface Suite {

	public static List<Transaction> transactions = new ArrayList<Transaction>();
	
	public static void construct() { }
	public static void run() { }
}
