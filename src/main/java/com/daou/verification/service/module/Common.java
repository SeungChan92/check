package com.daou.verification.service.module;

import com.daou.verification.config.Config;

public class Common extends Module {
	public static void goToPage_home() {
		driver.get(Config.get("baseUrl"));
	}
}
