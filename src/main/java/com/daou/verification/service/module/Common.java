package com.daou.verification.service.module;

import com.daou.verification.dto.Dto;

public class Common extends Module {
	public static void goToPage_home() {
		driver.get(Dto.baseUrl);
	}
}
