package com.daou.verification.service.suite;

import java.util.ArrayList;
import java.util.List;

import com.daou.verification.Transaction;

public class Suite { // abstract

	public static List<Transaction> transactions = new ArrayList<Transaction>();
	
	public static void construct() { } // abstract
	public static void run() {
		for ( Transaction transaction : transactions )
		{
			try {
				transaction.logStart();
				transaction.transact();
				transaction.logSuccess();
				
			} catch (Exception e) {
				transaction.logException(e);
			}
		}
	}
}
