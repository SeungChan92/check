package com.daou.check.service.suite;

import java.util.ArrayList;
import java.util.List;

import com.daou.check.Transaction;

public class Suite { // abstract

	public static List<Transaction> transactions = new ArrayList<Transaction>();
	
	public static void construct() { } // abstract
	public static void run() {
		for ( Transaction transaction : transactions )
		{
			try {
				transaction.transact();
				transaction.success();
			} catch (Exception e) {
				transaction.fail(e);
			}
		}
	}
}
