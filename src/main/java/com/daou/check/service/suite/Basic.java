package com.daou.check.service.suite;

import com.daou.check.Transaction;
import com.daou.check.dto.Dto;
import com.daou.check.service.module.Common;
import com.daou.check.service.module.Member;
import com.daou.check.service.module.Message;

public class Basic implements Suite {
	
	public static void construct() {
		transactions.add( new Transaction("홈페이지로 이동") {
			@Override
			public void transact() {
				Common.goToPage_home();
			}
		});
		transactions.add( new Transaction("로그인") {
			@Override
			public void transact() {
				Member.login();
			}
		});
	}
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
