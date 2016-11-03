package com.daou.verification.service.suite;

import com.daou.verification.Transaction;
import com.daou.verification.config.Config;
import com.daou.verification.service.module.Common;
import com.daou.verification.service.module.Member;
import com.daou.verification.service.module.Message;

public class BasicSuite extends Suite {
	
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
		transactions.add( new Transaction("문자발송") {
			@Override
			public void transact() throws InterruptedException {
				Message.goToPage_message();
				Message.typeTitle(Config.get("title"));
				Message.typeMessage(Config.get("message"));
				Message.addReceiver_fromType(Config.get("receiver"));
				Message.clickSendButton();
			}
		});
	}
}