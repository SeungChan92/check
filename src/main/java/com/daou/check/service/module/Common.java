package com.daou.check.service.module;

import com.daou.check.dto.Dto;

public class Common extends Module {
	public static void goToPage_home() {
		driver.get(Dto.baseUrl);
	}
}
